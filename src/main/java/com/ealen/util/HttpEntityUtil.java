package com.ealen.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Arrays;


public class HttpEntityUtil {
    public static <T> HttpEntity<T> createHttpEntity(T content) {
        return HttpEntityUtil.createHttpEntity(content, null);
    }

    public static <T> HttpEntity<T> createHttpEntity(T content, MediaType contentType, MediaType... acceptArray) {
        HttpHeaders headers = new HttpHeaders();

        if (contentType != null) {
            headers.setContentType(contentType);
        }

        if (acceptArray.length != 0) {
            headers.setAccept(Arrays.asList(acceptArray));
        } else {
            headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json; charset=UTF-8")));
        }

        return new HttpEntity<T>(content, headers);
    }


    public static <T> HttpEntity<T> createDroolsHttpEntity(T content, String userName, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/xml; charset=UTF-8"));
        headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/xml; charset=UTF-8")));

        String auth = userName + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        return new HttpEntity<T>(content, headers);
    }
//    public class XmlHttpMessageConverter extends AbstractXmlHttpMessageConverter {
//
//    }
}