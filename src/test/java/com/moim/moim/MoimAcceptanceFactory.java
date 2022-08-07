package com.moim.moim;

import com.moim.moim.application.dto.CreateMoimRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class MoimAcceptanceFactory {
    public static ExtractableResponse<Response> 모임리스트_조회() {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/moims")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 모임_조회(String title) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/moims/" + title)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 모임생성_요청(CreateMoimRequest request, String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .body(request)
                .when()
                .post("/moims/create")
                .then().log().all()
                .extract();
    }

    public static void 모임리스트_조회성공(ExtractableResponse<Response> 모임리스트_조회) {
        assertThat(모임리스트_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 모임_조회성공(ExtractableResponse<Response> 모임_조회) {
        assertThat(모임_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 모임생성_성공(ExtractableResponse<Response> 모임생성_결과) {
        assertThat(모임생성_결과.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 모임생성_실패(ExtractableResponse<Response> 모임생성_결과) {
        assertThat(모임생성_결과.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
