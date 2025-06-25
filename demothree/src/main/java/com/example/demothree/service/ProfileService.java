package com.example.demothree.service;

import com.example.demothree.dto.ProfileRequest;
import com.example.demothree.dto.ProfileResponse;
import com.example.demothree.entity.Profile;
import com.example.demothree.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileResponse saveProfile(ProfileRequest profileRequest) {
        profileRepository.save(Profile.builder()
                .fullName(profileRequest.getFullName())
                .email(profileRequest.getEmail())
                .phone(profileRequest.getPhone())
                .address(profileRequest.getAddress())
                .build());

        return ProfileResponse.builder()
                .fullName(profileRequest.getFullName())
                .email(profileRequest.getEmail())
                .phone(profileRequest.getPhone()).build();
    }
}
