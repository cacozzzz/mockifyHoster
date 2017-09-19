package io.mockify.hoster.gateways;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RemoteHtmlGateway {
    private final RestTemplate restTemplate;

    public RemoteHtmlGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] get(String url) {
        if(url == null) return null;

        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);

        if (responseEntity.getStatusCodeValue() != HttpStatus.OK.value()) {
            return null;
        }

        return responseEntity.getBody();
    }
}
