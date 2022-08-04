package com.moim.jwt.acceptance;

import com.moim.util.AcceptanceTest;
import com.moim.jwt.application.dto.LoginRequest;
import com.moim.member.application.dto.SignupMemberRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.moim.jwt.acceptance.JwtAcceptanceFactory.로그인_성공;
import static com.moim.jwt.acceptance.JwtAcceptanceFactory.로그인_요청;
import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JWT 인수테스트")
public class JwtAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void login() {
        SignupMemberRequest request = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
        LoginRequest loginRequest = new LoginRequest("yangsi", "password12#");

        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(request);
        ExtractableResponse<Response> 로그인_요청 = 로그인_요청(loginRequest);

        로그인_성공(로그인_요청);
    }
}
