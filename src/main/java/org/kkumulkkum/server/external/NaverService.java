package org.kkumulkkum.server.external;

import org.kkumulkkum.server.exception.OpenApiException;
import org.kkumulkkum.server.exception.code.OpenApiErrorCode;
import org.kkumulkkum.server.external.dto.LocationsDto;
import org.kkumulkkum.server.external.dto.NaverLocationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NaverService {

    @Value("${naver.client-id}")
    private String naverClientId;

    @Value("${naver.client-secret}")
    private String naverClientSecret;

    @Value("${naver.location-search-url}")
    private String naverLocationSearchUrl;

    @Transactional(readOnly = true)
    public List<LocationsDto> getLocations(String query) {
        try {
            NaverLocationResponse naverLocationResponse = locationSearch(requestMap(query));
            return convertResponse(naverLocationResponse);
        } catch (OpenApiException e) {
            return List.of();
        }
    }

    private NaverLocationResponse locationSearch(MultiValueMap<String, String> request) {
        RestClient restClient = RestClient.create();
        URI uri = createUri(request);
        return restClient.get()
                .uri(uri)
                .header("X-Naver-Client-Id", naverClientId)
                .header("X-Naver-Client-Secret", naverClientSecret)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new OpenApiException(OpenApiErrorCode.INVALID_ARGUMENT);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new OpenApiException(OpenApiErrorCode.INTERNAL_SERVER_ERROR);
                })
                .body(NaverLocationResponse.class);
    }

    private List<LocationsDto> convertResponse(NaverLocationResponse response) {
        List<NaverLocationResponse.SearchLocationItem> items = response.items();

        return items.stream()
                .map(item -> new LocationsDto(convertX(item.getMapx()), convertY(item.getMapy()), trim(item.getTitle()), trim(item.getAddress()), trim(item.getRoadAddress())))
                .collect(Collectors.toList());
    }

    private MultiValueMap<String, String> requestMap(String query) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("query", query);
        map.add("display", "5");
        map.add("start", "1");
        map.add("sort", "random");
        return map;
    }

    private URI createUri(MultiValueMap<String, String> request) {
        return UriComponentsBuilder
                .fromUriString(naverLocationSearchUrl)
                .queryParams(request)
                .build()
                .encode()
                .toUri();
    }

    private double convertX(int x) {

        int integerPart = x / 10000000;
        int decimalPart = x % 10000000;

        double decimalPartInDouble = decimalPart / 10000000.0;

        return integerPart + decimalPartInDouble;
    }

    private double convertY(int x) {

        int integerPart = x / 10000000;
        int decimalPart = x % 10000000;

        double decimalPartInDouble = decimalPart / 10000000.0;

        return integerPart + decimalPartInDouble;
    }

    private String trim(String string) {
        return string.replaceAll("<[^>]*>", "");
    }

}
