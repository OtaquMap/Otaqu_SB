package com.otakumap.global.validation.validator;

import com.otakumap.domain.place_like.service.PlaceLikeQueryService;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.validation.annotation.ExistPlaceLike;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceLikeExistValidator implements ConstraintValidator<ExistPlaceLike, List<Long>> {
    private final PlaceLikeQueryService placeLikeQueryService;

    @Override
    public void initialize(ExistPlaceLike constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> placeIds, ConstraintValidatorContext context) {
        if (placeIds == null || placeIds.isEmpty()) {
            return false;
        }

        boolean isValid = placeIds.stream()
                .allMatch(placeId -> placeLikeQueryService.isPlaceLikeExist(placeId));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PLACE_LIKE_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
