package com.otakumap.domain.event_like.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class EventLikeRequestDTO {
    @Getter
    public static class BookmarkDTO {
        @NotNull(message = "북마크 여부 입력은 필수입니다.")
        Boolean isBookmarked;
    }
}
