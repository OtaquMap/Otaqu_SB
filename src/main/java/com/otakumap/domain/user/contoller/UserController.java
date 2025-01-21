package com.otakumap.domain.user.contoller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.domain.user.dto.UserRequestDTO;
import com.otakumap.domain.user.dto.UserResponseDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.service.UserCommandService;
import com.otakumap.domain.user.service.UserQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @GetMapping
    @Operation(summary = "회원 정보 조회 API", description = "회원 정보를 조회합니다.")
    public ApiResponse<UserResponseDTO.UserInfoResponseDTO> getUserInfo(@CurrentUser User user) {
        User userInfo = userQueryService.getUserInfo(user.getId());
        return ApiResponse.onSuccess(UserConverter.toUserInfoResponseDTO(userInfo));
    }

    @PatchMapping("/nickname")
    @Operation(summary = "닉네임 변경 API", description = "회원의 닉네임을 변경합니다.")
    public ApiResponse<String> updateNickname(
            @CurrentUser User user,
            @Valid @RequestBody UserRequestDTO.UpdateNicknameDTO request) {
        userCommandService.updateNickname(user, request);
        return ApiResponse.onSuccess("닉네임이 성공적으로 수정되었습니다.");
    }

    @PostMapping("/report-event")
    @Operation(summary = "이벤트 제보 API", description = "이벤트를 제보합니다.")
    public ApiResponse<String> reportEvent(
            @Valid @RequestBody UserRequestDTO.UserReportRequestDTO request) {
        userCommandService.reportEvent(request);
        return ApiResponse.onSuccess("이벤트 제보가 성공적으로 전송되었습니다.");
    }

    @PatchMapping("/notification-settings")
    @Operation(summary = "알림 설정 변경 API", description = "회원의 알림 설정을 변경합니다.")
    public ApiResponse<String> updateNotificationSettings(
            @CurrentUser User user,
            @Valid @RequestBody UserRequestDTO.NotificationSettingsRequestDTO request) {
        userCommandService.updateNotificationSettings(user, request);
        return ApiResponse.onSuccess("알림 설정이 성공적으로 업데이트되었습니다.");
    }

    @GetMapping("/my-reviews")
    @Operation(summary = "내가 작성한 리뷰 조회 API", description = "내가 작성한 리뷰를 조회합니다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 쿼리스트링입니다! (1~)"),
            @Parameter(name = "sort", description = "정렬 기준 쿼리스트링입니다! (createdAt, views)")
    })
    public ApiResponse<UserResponseDTO.UserReviewListDTO> getMyReviews(
            @CurrentUser User user, @CheckPage @RequestParam(name = "page") Integer page,
            @RequestParam(name = "sort", defaultValue = "createdAt") String sort) {
        Page<PlaceReview> reviews = userQueryService.getMyReviews(user, page, sort);
        return ApiResponse.onSuccess(UserConverter.reviewListDTO(reviews));
    }
}
