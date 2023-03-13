package es.dreyesr.authservice.controller;

import es.dreyesr.authservice.dto.AuthUserDto;
import es.dreyesr.authservice.dto.NewUserDto;
import es.dreyesr.authservice.dto.RequestDto;
import es.dreyesr.authservice.dto.TokenDto;
import es.dreyesr.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody NewUserDto newUserDto) {
        return ResponseEntity.ok(authService.register(newUserDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(authService.authenticate(authUserDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(authService.validate(token, requestDto));
    }
}
