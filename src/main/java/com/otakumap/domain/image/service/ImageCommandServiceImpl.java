package com.otakumap.domain.image.service;

import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.image.entity.Image;
import com.otakumap.domain.image.repository.ImageRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.ImageHandler;
import com.otakumap.global.util.AmazonS3Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageCommandServiceImpl implements ImageCommandService {
    private final ImageRepository imageRepository;
    private final AmazonS3Util amazonS3Util;

    @Override
    @Transactional
    public Image uploadProfileImage(MultipartFile file, Long userId) {
        String keyName = amazonS3Util.generateProfileKeyName();
        String fileUrl = amazonS3Util.uploadFile(keyName, file);

        return imageRepository.save(ImageConverter.toImage((keyName.split("/")[1]), keyName, fileUrl));
    }

    @Override
    @Transactional
    public List<Image> uploadReviewImages(List<MultipartFile> files, Long reviewId) {
        return files.stream()
                .map(file -> {
                    String keyName = amazonS3Util.generateReviewKeyName();
                    String fileUrl = amazonS3Util.uploadFile(keyName, file);

                    return ImageConverter.toImage((keyName.split("/")[1]), keyName, fileUrl);
                })
                .map(imageRepository::save)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Image uploadaImage(MultipartFile file, String folder) {
        String keyName = switch (folder) {
            case "profile" -> amazonS3Util.generateProfileKeyName();
            case "review" -> amazonS3Util.generateReviewKeyName();
            case "event" -> amazonS3Util.generateEventKeyName();
            default -> throw new ImageHandler(ErrorStatus.INVALID_FOLDER);
        };

        String fileUrl = amazonS3Util.uploadFile(keyName, file);
        return imageRepository.save(ImageConverter.toImage((keyName.split("/")[1]), keyName, fileUrl));
    }
}
