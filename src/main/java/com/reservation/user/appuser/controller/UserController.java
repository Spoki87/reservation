package com.reservation.user.appuser.controller;

import com.reservation.response.Response;
import com.reservation.user.appuser.dto.request.CreateUserRequest;
import com.reservation.user.appuser.dto.response.UserResponse;
import com.reservation.user.appuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "Endpoints available with admin authentication")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Add new admin"
    )
    @PostMapping()
    public ResponseEntity<Response<UserResponse>> register(@Valid @RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.addUser(request);
        return ResponseEntity.ok(Response.success("User added successfully",userResponse, HttpStatus.CREATED));
    }

}
