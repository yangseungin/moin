package com.moim.moim.ui;

import com.moim.moim.application.MoimService;
import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.moim.application.dto.CreateMoimResponse;
import com.moim.moim.application.dto.MoimResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/moims")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

    @GetMapping
    public ResponseEntity<List<MoimResponse>> findAllMoim() {
        List<MoimResponse> moimResponses = moimService.findMoims();
        return ResponseEntity.ok(moimResponses);
    }

    @GetMapping("/{moimTitle}")
    public ResponseEntity<MoimResponse> findMoim(@PathVariable String moimTitle) {
        MoimResponse moimResponse = moimService.findMoim(URLDecoder.decode(moimTitle, StandardCharsets.UTF_8));
        return ResponseEntity.ok(moimResponse);
    }


    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PostMapping("/create")
    public ResponseEntity<CreateMoimResponse> createMoim(@RequestBody @Valid CreateMoimRequest request, Authentication authentication) {
        CreateMoimResponse moimResponse = moimService.createMoim(request, authentication);
        return ResponseEntity.created(URI.create("/moims/" + moimResponse.getTitle())).body(moimResponse);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PatchMapping("/{moimTitle}/changeReruit")
    public ResponseEntity<MoimResponse> changeRecruit(@PathVariable String moimTitle, Authentication authentication) {
        MoimResponse moimResponse = moimService.changeRecruit(moimTitle, authentication);
        return ResponseEntity.ok().body(moimResponse);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PatchMapping("/{moimTitle}/close")
    public ResponseEntity<MoimResponse> closeMoim(@PathVariable String moimTitle, Authentication authentication) {
        MoimResponse moimResponse = moimService.closeMoim(moimTitle, authentication);
        return ResponseEntity.ok().body(moimResponse);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PatchMapping("/{moimTitle}/join")
    public ResponseEntity<MoimResponse> joinMoim(@PathVariable String moimTitle, Authentication authentication) {
        MoimResponse moimResponse = moimService.addMember(moimTitle, authentication);
        return ResponseEntity.ok().body(moimResponse);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PatchMapping("/{moimTitle}/leave")
    public ResponseEntity<MoimResponse> leaveMoim(@PathVariable String moimTitle, Authentication authentication) {
        MoimResponse moimResponse = moimService.removeMember(moimTitle, authentication);
        return ResponseEntity.ok().body(moimResponse);
    }



}
