package com.moim.jwt.application;

import com.moim.jwt.application.dto.LoginRequest;
import com.moim.jwt.application.dto.TokenData;
import com.moim.jwt.utils.JwtUtil;
import com.moim.member.application.error.AuthorizationException;
import com.moim.member.domain.Member;
import com.moim.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ5YW5nc2kiLCJleHAiOjM5NzE2MDMxNjMwfQ.tsf0yQau1LKz9-jNJM7Ka-pdwZV9VbxIePZqn704HTM";

    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private JwtService jwtService;
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("yangsi", "양승인", "19920606", "남성", "password12#", "rhfpdk92@naver.com");
    }

    @Test
    void 로그인_성공시_액세스토큰을_조회할수있다() {
        LoginRequest request = new LoginRequest("yangsi", "password12#");
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(member));
        when(jwtUtil.getToken(request.getMemberId()))
                .thenReturn(VALID_TOKEN);

        TokenData login = jwtService.login(request);

        assertThat(login.getAccessToken()).isNotBlank();
    }

    @Test
    void 비밀번호가_다른경우_로그인할_수_없다() {
        LoginRequest request = new LoginRequest("yangsi", "wrong_passw0rd");
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(member));

        assertThatThrownBy(() -> jwtService.login(request))
                .isInstanceOf(AuthorizationException.class);
    }

}
