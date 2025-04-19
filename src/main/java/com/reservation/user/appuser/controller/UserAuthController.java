package com.reservation.user.appuser.controller;

import com.reservation.response.Response;
import com.reservation.user.appuser.dto.request.AuthenticateUserRequest;
import com.reservation.user.appuser.dto.response.UserResponse;
import com.reservation.user.appuser.service.AuthService;
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

@Tag(name = "Auth", description = "Endpoints available with admin authentication")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserAuthController {

    private final AuthService authService;

    @Operation(
            summary = "Authentication"
    )
    @PostMapping()
    public ResponseEntity<Response<UserResponse>> authenticate(@Valid @RequestBody AuthenticateUserRequest request) {
        authService.authenticate(request);
        return ResponseEntity.ok(Response.success("Authenticated successfully",null, HttpStatus.OK));
    }
}
