package com.example.demothree.controller;

import com.example.demothree.dto.ProfileRequest;
import com.example.demothree.dto.ProfileResponse;
import com.example.demothree.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/")
    public ResponseEntity<ProfileResponse> saveProfile(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.ok(profileService.saveProfile(profileRequest));
    }
}
