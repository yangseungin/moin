package com.moim.member.acceptance;

import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.UpdateMemberRequest;
import com.moim.member.application.dto.UpdateMemberResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MemberAcceptanceFactory {
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

    public static ExtractableResponse<Response> 회원수정_요청(UpdateMemberRequest request, String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .body(request)
                .when()
                .patch("/members/update")
                .then().log().all()
                .extract();
    }

    public static void 회원등록_성공(ExtractableResponse<Response> 회원등록_요청) {
        assertThat(회원등록_요청.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 회원등록_실패(ExtractableResponse<Response> 회원등록_요청) {
        assertThat(회원등록_요청.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static void 회원수정_성공(UpdateMemberRequest updateMemberRequest,ExtractableResponse<Response> 회원등록_요청) {
        UpdateMemberResponse response = 회원등록_요청.as(UpdateMemberResponse.class);
        assertAll(
                () -> assertThat(updateMemberRequest.getName()).isEqualTo(response.getName()),
                () -> assertThat(updateMemberRequest.getBirth()).isEqualTo(response.getBirth()),
                () -> assertThat(updateMemberRequest.getGender()).isEqualTo(response.getGender()),
                () -> assertThat(updateMemberRequest.getPassword()).isEqualTo(response.getPassword()),
                () -> assertThat(updateMemberRequest.getEmail()).isEqualTo(response.getEmail())

        );
        assertThat(회원등록_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
