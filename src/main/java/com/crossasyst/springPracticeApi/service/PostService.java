package com.crossasyst.springPracticeApi.service;


import com.crossasyst.springPracticeApi.model.PostRequest;
import com.crossasyst.springPracticeApi.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
public class PostService {


    private final RestTemplate restTemplate;
    @Value("${test.fakeUrl}")
    private String fakeUrl;

    @Autowired
    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public PostResponse createPosts(PostRequest postRequest) {

        HttpEntity<PostRequest> httpEntity = new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> body = restTemplate.exchange(fakeUrl + "/posts", HttpMethod.POST, httpEntity, PostResponse.class);
        return body.getBody();

    }

    public ResponseEntity<PostRequest[]> getPosts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(fakeUrl + "/posts", HttpMethod.GET, httpEntity, PostRequest[].class);
    }

    public PostResponse getById(Long id) {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(httpHeader);
        ResponseEntity<PostResponse> postResponse = restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.GET, entity, PostResponse.class);
        return postResponse.getBody();
    }

    public PostResponse updatePosts(Long id, PostRequest postRequest) {
        HttpEntity httpEntity = new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> response = restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.PUT, httpEntity, PostResponse.class);
        return response.getBody();
    }

    public void deleteById(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.DELETE, httpEntity, PostRequest[].class);
    }
}

