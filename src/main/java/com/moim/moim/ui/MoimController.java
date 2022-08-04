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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/moims")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

    @GetMapping("/list")
    public ResponseEntity<List<MoimResponse>> findAllMoim() {
        List<MoimResponse> moimResponses = moimService.findMoims();
        return ResponseEntity.ok(moimResponses);
    }


    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PostMapping("/create")
    public ResponseEntity<CreateMoimResponse> createMoim(@RequestBody @Valid CreateMoimRequest request, Authentication authentication) {
        CreateMoimResponse moimResponse = moimService.createMoim(request,authentication);
        return ResponseEntity.created(URI.create("/moims/" + moimResponse.getTitle())).body(moimResponse);
    }
}
