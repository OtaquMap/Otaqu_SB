package com.otakumap.domain.event_review.converter;

import com.otakumap.domain.event_review.dto.EventReviewResponseDTO;
import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class EventReviewConverter {

    public static EventReviewResponseDTO.EventReviewPreViewDTO eventReviewPreViewDTO(EventReview eventReview) {
        ImageResponseDTO.ImageDTO image = ImageResponseDTO.ImageDTO.builder()
                .id(eventReview.getImage().getId())
                .uuid(eventReview.getImage().getUuid())
                .fileUrl(eventReview.getImage().getFileUrl())
                .fileName(eventReview.getImage().getFileName())
                .build();

        return EventReviewResponseDTO.EventReviewPreViewDTO.builder()
                .id(eventReview.getId())
                .title(eventReview.getTitle())
                .reviewPhotoUrl(image)
                .build();
    }

    public static EventReviewResponseDTO.EventReviewPreViewListDTO eventReviewPreViewListDTO(Page<EventReview> eventReviewList) {
        if(eventReviewList == null || eventReviewList.isEmpty()) return null;

        List<EventReviewResponseDTO.EventReviewPreViewDTO> eventReviewDTOList = eventReviewList.stream()
                .map(EventReviewConverter::eventReviewPreViewDTO).collect(Collectors.toList());

        return EventReviewResponseDTO.EventReviewPreViewListDTO.builder()
                .eventReviews(eventReviewDTOList)
                .totalPages(eventReviewList.getTotalPages())
                .totalElements(eventReviewList.getNumber())
                .isFirst(eventReviewList.isFirst())
                .isLast(eventReviewList.isLast())
                .build();


    }
}