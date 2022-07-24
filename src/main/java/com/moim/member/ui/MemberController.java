package com.moim.member.ui;

import com.moim.member.application.MemberService;
import com.moim.member.application.dto.SignupMemberRequest;
import com.moim.member.application.dto.SignupMemberResponse;
import com.moim.member.application.dto.UpdateMemberRequest;
import com.moim.member.application.dto.UpdateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignupMemberResponse> signup(@RequestBody @Valid SignupMemberRequest request) {
        SignupMemberResponse memberResponse = memberService.signup(request);
        return ResponseEntity.created(URI.create("/members/" + memberResponse.getId())).body(memberResponse);
    }

    @PatchMapping("/{memberId}/update")
    public ResponseEntity<UpdateMemberResponse> updateInfo(@PathVariable Long memberId, @RequestBody @Valid UpdateMemberRequest request) {
        UpdateMemberResponse response = memberService.updateInfo(memberId, request);
        return ResponseEntity.ok().body(response);
    }
}
