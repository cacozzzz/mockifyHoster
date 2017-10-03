package io.mockify.hoster.gateways;

import io.mockify.hoster.dao.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

public class RemoteHtmlGateway {
    private static final Logger log = LoggerFactory.getLogger(FileRepository.class);
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

        return getUtf8Bytes(responseEntity);
    }

    private byte[] getUtf8Bytes(ResponseEntity<byte[]> responseEntity) {
        byte[] utf8Bytes = null;

        try {
            utf8Bytes = new String(responseEntity.getBody(),"UTF-8").getBytes();
        } catch (UnsupportedEncodingException e) {
            log.error("Couldn't convert encoding",e);
        }
        return utf8Bytes;
    }
}
