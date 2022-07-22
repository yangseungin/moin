package com.moim.member.acceptance;

import com.moim.member.application.dto.SignupMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberAcceptanceFactory {

    public static void 주문등록_실패(ExtractableResponse<Response> 회원등록_요청) {
        assertThat(회원등록_요청.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    public static void 주문등록_성공(ExtractableResponse<Response> 회원등록_요청) {
        assertThat(회원등록_요청.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    public static ExtractableResponse<Response> 회원등록_요청(SignupMemberRequest request) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members/signup")
                .then().log().all()
                .extract();
    }
}
