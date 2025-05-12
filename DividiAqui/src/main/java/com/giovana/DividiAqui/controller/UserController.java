package com.giovana.DividiAqui.controller;

import com.giovana.DividiAqui.application.UserApplicationService;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserApplicationService applicationService;

    @Override
    public ResponseEntity<UserResponseDTO> userPost(UserRequestDTO userRequestDTO) {
        var userResponseDTO = applicationService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> userGet() {
        return UserApi.super.userGet();
    }

    @Override
    public ResponseEntity<UserResponseDTO> userIdUserGet(String idUser) {
        return UserApi.super.userIdUserGet(idUser);
    }

    @Override
    public ResponseEntity<UserResponseDTO> userIdUserPut(String idUser, UserRequestDTO userRequestDTO) {
        return UserApi.super.userIdUserPut(idUser, userRequestDTO);
    }
}

