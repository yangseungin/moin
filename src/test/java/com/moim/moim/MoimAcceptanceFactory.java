package com.moim.moim;

import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.moim.application.dto.MoimResponse;
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

    public static ExtractableResponse<Response> 모임_모집상태_변경요청(String title, String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when()
                .patch("/moims/{title}/changeReruit", title)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 모임종료_요청(String title, String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when()
                .patch("/moims/{title}/close", title)
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

    public static void 모임모집상태_변경성공(ExtractableResponse<Response> 모임변경_결과, CreateMoimRequest request) {
        assertThat(모임변경_결과.statusCode()).isEqualTo(HttpStatus.OK.value());
        MoimResponse as = 모임변경_결과.as(MoimResponse.class);
        assertThat(as.isRecruiting()).isEqualTo(!request.isRecruiting());
    }

    public static void 모임모집상태_변경실패(ExtractableResponse<Response> 모임변경_결과) {
        assertThat(모임변경_결과.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static void 모임종료_성공(ExtractableResponse<Response> 모임종료_결과) {
        assertThat(모임종료_결과.statusCode()).isEqualTo(HttpStatus.OK.value());
        MoimResponse as = 모임종료_결과.as(MoimResponse.class);
        assertThat(as.isClosed()).isTrue();
    }

    public static void 모임종료_실패(ExtractableResponse<Response> 모임종료_결과) {
        assertThat(모임종료_결과.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
