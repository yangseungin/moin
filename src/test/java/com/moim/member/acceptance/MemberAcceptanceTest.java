package com.moim.member.acceptance;

import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.SignupMemberResponse;
import com.moim.member.application.dto.UpdateMemberRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_성공;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_실패;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_요청;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원수정_성공;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원수정_요청;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("회원 인수테스트")
public class MemberAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
//        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void 회원가입을_할_수_있다() {
        SignupMemberRequest request = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(request);

        회원등록_성공(회원등록_요청);
    }

    @Test
    void 생년월일규칙이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19929696", "남성", "password12#", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        회원등록_실패(회원등록_요청);
    }

    @Test
    void 패스워드규칙이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        회원등록_실패(회원등록_요청);
    }

    @Test
    void 이메일형식이_잘못된경우_회원가입을_할_수_없다() {
        SignupMemberRequest invalidRequest = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(invalidRequest);

        회원등록_실패(회원등록_요청);
    }

    @Test
    void 회원정보를_수정할_수_있다() {
        SignupMemberRequest signupRequest = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
        SignupMemberResponse yangsi = 회원등록_요청(signupRequest).as(SignupMemberResponse.class);

        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest("홍길동", "19920707", "여성", "change12#", "change@gmail.com");
        ExtractableResponse<Response> 회원수정_요청결과 = 회원수정_요청(yangsi.getId(), updateMemberRequest);

        회원수정_성공(updateMemberRequest, 회원수정_요청결과);
    }
}
