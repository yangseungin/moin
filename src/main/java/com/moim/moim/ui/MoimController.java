package com.moim.moim.ui;

import com.moim.moim.application.MoimService;
import com.moim.moim.application.dto.CreateMoimRequest;
import com.moim.moim.application.dto.CreateMoimResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/moims")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

    @PreAuthorize("isAuthenticated() and hasAuthority('USER')")
    @PostMapping("/create")
    public ResponseEntity<CreateMoimResponse> createMoim(@RequestBody @Valid CreateMoimRequest request) {
        CreateMoimResponse moimResponse = moimService.createMoim(request);
        return ResponseEntity.created(URI.create("/moims/" + moimResponse.getTitle())).body(moimResponse);
    }
}
