package com.moim.moim;

import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.util.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.moim.member.acceptance.MemberAcceptanceFactory.회원등록_요청;
import static com.moim.moim.MoimAcceptanceFactory.모임_모집상태_변경요청;
import static com.moim.moim.MoimAcceptanceFactory.모임_조회;
import static com.moim.moim.MoimAcceptanceFactory.모임리스트_조회;
import static com.moim.moim.MoimAcceptanceFactory.모임리스트_조회성공;
import static com.moim.moim.MoimAcceptanceFactory.모임모집상태_변경성공;
import static com.moim.moim.MoimAcceptanceFactory.모임모집상태_변경실패;
import static com.moim.moim.MoimAcceptanceFactory.모임생성_성공;
import static com.moim.moim.MoimAcceptanceFactory.모임생성_실패;
import static com.moim.moim.MoimAcceptanceFactory.모임생성_요청;
import static com.moim.moim.MoimAcceptanceFactory.모임종료_성공;
import static com.moim.moim.MoimAcceptanceFactory.모임종료_실패;
import static com.moim.moim.MoimAcceptanceFactory.모임종료_요청;

@DisplayName("모임 인수테스트")
public class MoimAcceptanceTest extends AcceptanceTest {
    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6InlhbmdzaSJ9.HbvHhKVvRyGRMwCN1tymqF4mXewCR5VUkA7YtY7MhP8";

    @BeforeEach
    public void setUp() {
        super.setUp();
        SignupMemberRequest 회원정보 = new SignupMemberRequest("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
        ExtractableResponse<Response> 회원등록_요청 = 회원등록_요청(회원정보);
    }

    @Test
    void 모임들을_조회할_수_있다() {
        ExtractableResponse<Response> 모임리스트_조회 = 모임리스트_조회();

        모임리스트_조회성공(모임리스트_조회);
    }

    @Test
    void 모임을_조회할_수_있다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, true, true, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 모임_조회 = 모임_조회(request.getTitle());

        모임리스트_조회성공(모임_조회);
    }

    @Test
    void 모임을_생성할_수_있다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, true, true, LocalDateTime.of(2023, 5, 20, 0, 0));

        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        모임생성_성공(모임생성_요청);
    }

    @Test
    void 존재하는이름의_모임을_생성할_수_없다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, true, true, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 이미있는모임생성_요청 = 모임생성_요청(request, TOKEN);

        모임생성_실패(이미있는모임생성_요청);
    }

    @Test
    void 모임의_모집상태를_변경할_수_있다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, false, false, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 모임변경_요청 = 모임_모집상태_변경요청(request.getTitle(), TOKEN);

        모임모집상태_변경성공(모임변경_요청, request);
    }

    @Test
    void 종료된_모임의_모집상태를_변경할_수_없다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, false, true, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 모임변경_요청 = 모임_모집상태_변경요청(request.getTitle(), TOKEN);

        모임모집상태_변경실패(모임변경_요청);
    }

    @Test
    void 모임을_종료할_수_있다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, false, false, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 모임종료_요청 = 모임종료_요청(request.getTitle(), TOKEN);

        모임종료_성공(모임종료_요청);
    }

    @Test
    void 종료된_모임을_종료할_수_없다() {
        CreateMoimRequest request = new CreateMoimRequest("모임모임", "모임소개", 5, false, true, LocalDateTime.of(2023, 5, 20, 0, 0));
        ExtractableResponse<Response> 모임생성_요청 = 모임생성_요청(request, TOKEN);

        ExtractableResponse<Response> 모임종료_요청 = 모임종료_요청(request.getTitle(), TOKEN);

        모임종료_실패(모임종료_요청);
    }


}
