package com.otakumap.global.validation.validator;

import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.validation.annotation.ExistPlace;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceExistValidator implements ConstraintValidator<ExistPlace, Long> {

    private final PlaceRepository placeRepository;

    @Override
    public void initialize(ExistPlace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long placeId, ConstraintValidatorContext context) {
        boolean isValid = placeRepository.existsById(placeId);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PLACE_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
