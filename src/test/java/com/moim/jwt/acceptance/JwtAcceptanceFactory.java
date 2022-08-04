package com.moim.jwt.acceptance;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.member.application.dto.SignupMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtAcceptanceFactory {
    public static ExtractableResponse<Response> 로그인_요청(LoginRequest loginRequest) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginRequest)
                .when()
                .post("/session/login")
                .then().log().all()
                .extract();
    }
    public static void 로그인_성공(ExtractableResponse<Response> 로그인_요청) {
        assertThat(로그인_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
