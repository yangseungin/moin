package com.moim.member.acceptance;

import com.moim.member.application.dto.SignupMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.moim.member.acceptance.MemberAcceptanceFactory.주문등록_성공;
import static com.moim.member.acceptance.MemberAcceptanceFactory.주문등록_실패;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_요청;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("회원 인수테스트")
public class MemberAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 회원가입을_할_수_있다() {
        SignupMemberRequest request = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(request);

        주문등록_성공(회원등록_요청);
    }

    @Test
    void 생년월일규칙이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19929696", "남성", "password12#", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        주문등록_실패(회원등록_요청);
    }

    @Test
    void 패스워드규칙이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        주문등록_실패(회원등록_요청);
    }

    @Test
    void 이메일형식이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        주문등록_실패(회원등록_요청);
    }
}
