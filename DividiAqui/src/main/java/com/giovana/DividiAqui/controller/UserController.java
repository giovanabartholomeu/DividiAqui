package com.giovana.DividiAqui.controller;

import com.giovana.DividiAqui.application.UserApplicationService;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class UserController implements UserApi {

    private UserApplicationService userApplication;


    @Override
    public ResponseEntity<UserResponseDTO> userPost(UserRequestDTO userRequestDTO) {
        var response = userApplication.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> userGet() {
        var response = userApplication.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<UserResponseDTO> userIdUserGet(String idUser) {
        var response = userApplication.getUserById(UUID.fromString(idUser));
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<UserResponseDTO> userIdUserPut(String idUser, UserRequestDTO userRequestDTO) {
        var response = userApplication.updateUser(UUID.fromString(idUser), userRequestDTO);
        return ResponseEntity.ok(response);
    }

    //Criar o método delete no swagger
    public ResponseEntity<Void> userIdUserDelete(String idUser) {
        userApplication.deleteUserById(UUID.fromString(idUser));
        return ResponseEntity.noContent().build();
    }
}

