package com.humber.UserService.component;

import com.humber.UserService.model.Image;
import com.humber.UserService.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@Slf4j
public class ImageServiceClient {
    @Value("${imageService.getImageById}")
    private String getImageById;

    @Value("${imageService.addImage}")
    private String addImage;

    private final RestTemplate restTemplate;

    public ImageServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public byte[] getImage() {
        log.info("getImageById: " + getImageById);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                getImageById,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<byte[]>() {}
        );

        return response.getBody();
    }

    public Image addImage(MultipartFile image) {
        log.info("addImage: " + addImage);

        Image imageDetail = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MultipartFile> request = new HttpEntity<>(image, headers);
            imageDetail = restTemplate.postForObject(addImage, request, Image.class);
            log.info("imageDetail: " + imageDetail);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return imageDetail;
    }
}
