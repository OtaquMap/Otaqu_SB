package com.otakumap.domain.route_like.service;

import com.otakumap.domain.event_like.converter.EventLikeConverter;
import com.otakumap.domain.event_like.dto.EventLikeResponseDTO;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.route_like.converter.RouteLikeConverter;
import com.otakumap.domain.route_like.dto.RouteLikeResponseDTO;
import com.otakumap.domain.route_like.entity.QRouteLike;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.domain.route_like.repository.RouteLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteLikeQueryServiceImpl implements RouteLikeQueryService {
    private final RouteLikeRepository routeLikeRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public RouteLikeResponseDTO.RouteLikePreViewListDTO getRouteLikeList(User user, Boolean isFavorite, Long lastId, int limit) {
        QRouteLike qRouteLike = QRouteLike.routeLike;
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(qRouteLike.user.eq(user));

        // isFavorite이 true일 때만 검색 조건에 추가
        if (isFavorite != null && isFavorite) {
            predicate.and(qRouteLike .isFavorite.eq(isFavorite));
        }

        if (lastId != null && lastId > 0) {
            predicate.and(qRouteLike.id.lt(lastId));
        }

        List<RouteLike> result = jpaQueryFactory
                .selectFrom(qRouteLike)
                .leftJoin(qRouteLike.route).fetchJoin()
                .leftJoin(qRouteLike.user).fetchJoin()
                .where(predicate)
                .orderBy(qRouteLike.createdAt.desc())
                .limit(limit + 1)
                .fetch();

        return createRouteLikePreviewListDTO(result, limit);
    }

    private RouteLikeResponseDTO.RouteLikePreViewListDTO createRouteLikePreviewListDTO(List<RouteLike> routeLikes, int limit) {
        boolean hasNext = routeLikes.size() > limit;
        Long lastId = null;
        if (hasNext) {
            routeLikes = routeLikes.subList(0, routeLikes.size() - 1);
            lastId = routeLikes.get(routeLikes.size() - 1).getId();
        }
        List<RouteLikeResponseDTO.RouteLikePreViewDTO> list = routeLikes
                .stream()
                .map(RouteLikeConverter::routeLikePreViewDTO)
                .collect(Collectors.toList());

        return RouteLikeConverter.routeLikePreViewListDTO(list, hasNext, lastId);
    }

    @Override
    public boolean isRouteLikeExist(Long id) {
        return routeLikeRepository.existsById(id);
    }
}
