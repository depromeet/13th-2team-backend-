package com.depromeet;

import com.depromeet.oversweet.MemberKakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@FeignClient(value = "KakaoFeignClient", url = "https://kapi.kakao.com", configuration = FeignConfig.class)
public interface KakaoFeignClient {

    @GetMapping(value = "/v2/user/me")
    MemberKakaoUserInfoResponse findKakaoUserInfo(
            @RequestHeader(name = CONTENT_TYPE) String contentType,
            @RequestHeader(name = AUTHORIZATION) String accessToken
    );
}

