package com.cofomo.product.microservice.services.impl;

import com.cofomo.product.microservice.dto.FournisseurDto;
import com.cofomo.product.microservice.services.WebClientService;
import com.cofomo.product.microservice.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientServiceImpl implements WebClientService {

    private static String calculeApiPath = "http://localhost:8085/api/v1";

    @Override
    public FournisseurDto getFournisseurByReference(String param) {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/fournisseur")
                        .queryParam("reference", param)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    return Mono.error(new NotFoundException("No Supplier found with reference = " + param));
                })
                .bodyToMono(FournisseurDto.class).block();
    }

    @Override
    public FournisseurDto getFournisseurByID(String param) {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/fournisseur/" + param)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(FournisseurDto.class).block();
    }

    WebClient getWebClient() {
        return WebClient
                .builder()
                .baseUrl(calculeApiPath)
                .build();
    }

}
