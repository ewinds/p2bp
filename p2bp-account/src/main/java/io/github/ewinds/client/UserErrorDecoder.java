package io.github.ewinds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class UserErrorDecoder implements ErrorDecoder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Exception decode(String methodKey, Response response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        response.headers().entrySet().stream()
            .forEach(entry -> responseHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue())));

        HttpStatus statusCode = HttpStatus.valueOf(response.status());
        String statusText = response.reason();

        byte[] responseBody;
        Map jsonBody;

        try {
            responseBody = IOUtils.toByteArray(response.body().asInputStream());
            String body = StreamUtils.copyToString(new ByteArrayInputStream(responseBody), Charset.forName("utf-8"));
            ObjectMapper objectMapper = new ObjectMapper();
            jsonBody = objectMapper.readValue(body, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process response body.", e);
        }

        Exception exception = new HttpClientErrorException(statusCode, statusText, responseHeaders, responseBody, null);

        // 这里只封装4开头的请求异常
        if (400 <= response.status() || response.status() < 500) {
            exception = new HystrixBadRequestException(jsonBody.get("title").toString(), exception);
        } else {
            logger.error(exception.getMessage(), exception);
        }
        return exception;
    }
}
