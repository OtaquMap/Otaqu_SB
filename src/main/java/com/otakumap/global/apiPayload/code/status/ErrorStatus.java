package com.otakumap.global.apiPayload.code.status;

import com.otakumap.global.apiPayload.code.BaseErrorCode;
import com.otakumap.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 인증 관련 에러
    PASSWORD_NOT_EQUAL(HttpStatus.BAD_REQUEST, "AUTH4001", "비밀번호가 일치하지 않습니다."),
    EMAIL_CODE_EXPIRED(HttpStatus.BAD_REQUEST, "AUTH4002", "인증 코드가 만료되었습니다. 다시 요청해주세요."),
    CODE_NOT_EQUAL(HttpStatus.BAD_REQUEST, "AUTH4003", "인증 코드가 올바르지 않습니다."),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "AUTH5001", "메일 발송 중 오류가 발생했습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "AUTH4004", "이미 사용 중인 이메일입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH4005", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH4006", "토큰이 만료되었습니다."),
    TOKEN_LOGGED_OUT(HttpStatus.UNAUTHORIZED, "AUTH4007", "이 토큰은 로그아웃되어 더 이상 유효하지 않습니다."),
    UNSUPPORTED_PROVIDER(HttpStatus.BAD_REQUEST, "AUTH4008", "지원하지 않는 provider입니다."),

    // 멤버 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),

    // 명소 관련 에러
    PLACE_NOT_FOUND(HttpStatus.BAD_REQUEST, "PLACE4001", "존재하지 않는 명소입니다."),

    // 이벤트 좋아요 관련 에러
    EVENT_LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "EVENT4001", "저장되지 않은 이벤트입니다."),

    // 이벤트 상세 정보 관련 에러
    EVENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "EVENT4002", "존재하지 않는 이벤트입니다."),

    // 명소 좋아요 관련 에러
    PLACE_LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "PLACE4002", "저장되지 않은 명소입니다."),

    // 후기 검색 관련 에러
    REVIEW_SEARCH_NOT_FOUND(HttpStatus.NOT_FOUND, "SEARCH4001", "검색된 후기가 없습니다."),

    // 애니메이션 관련 에러
    ANIMATION_NOT_FOUND(HttpStatus.NOT_FOUND, "ANIMATION4001", "존재하지 않는 애니메이션입니다"),
    PLACE_ANIMATION_NOT_FOUND(HttpStatus.NOT_FOUND, "ANIMATION4001", "존재하지 않는 애니메이션입니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
