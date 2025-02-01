package com.otakumap.domain.reviews.service;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.animation.repository.AnimationRepository;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.repository.EventRepository;
import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.event_review.repository.EventReviewRepository;
import com.otakumap.domain.image.service.ImageCommandService;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.repository.PlaceReviewRepository;
import com.otakumap.domain.reviews.converter.ReviewConverter;
import com.otakumap.domain.reviews.dto.ReviewRequestDTO;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.route.repository.RouteRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.AnimationHandler;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final PlaceReviewRepository placeReviewRepository;
    private final EventReviewRepository eventReviewRepository;
    private final AnimationRepository animationRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    private final PlaceAnimationRepository placeAnimationRepository;
    private final RouteRepository routeRepository;
    private final ImageCommandService imageCommandService;

    @Override
    @Transactional
    public ReviewResponseDTO.createdReviewDTO createReview(ReviewRequestDTO.CreateDTO request, User user, MultipartFile[] images) {
        if (request.getPlaceId() != null && request.getEventId() != null) {
            throw new IllegalArgumentException("이벤트 후기와 장소 후기 중 하나만 선택해주세요.");
        } else if (request.getPlaceId() == null && request.getEventId() == null) {
            throw new IllegalArgumentException("이벤트 후기 또는 장소 후기 중 하나를 선택해주세요.");
        }

        animationRepository.findById(request.getAnimeId())
                .orElseThrow(() -> new AnimationHandler(ErrorStatus.ANIMATION_NOT_FOUND));

        List<MultipartFile> reviewImages = List.of(images);

        if (request.getPlaceId() != null) { // place review
            Place place = placeRepository.findById(request.getPlaceId())
                    .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

            PlaceReview placeReview = placeReviewRepository.save(ReviewConverter.toPlaceReview(request, user, place));
            imageCommandService.uploadReviewImages(reviewImages, placeReview.getId());
            return ReviewConverter.toCreatedReviewDTO(placeReview.getId(), placeReview.getTitle());
        }

        else { // event review
            Event event = eventRepository.findById(request.getEventId())
                    .orElseThrow(() -> new EventHandler(ErrorStatus.EVENT_NOT_FOUND));

            EventReview eventReview = eventReviewRepository.save(ReviewConverter.toEventReview(request, user, event));
            imageCommandService.uploadReviewImages(reviewImages, eventReview.getId());
            return ReviewConverter.toCreatedReviewDTO(eventReview.getId(), eventReview.getTitle());
        }
    }
}
