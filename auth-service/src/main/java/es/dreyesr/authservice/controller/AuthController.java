package es.dreyesr.authservice.controller;

import es.dreyesr.authservice.dto.AuthUserDto;
import es.dreyesr.authservice.dto.NewUserDto;
import es.dreyesr.authservice.dto.RequestDto;
import es.dreyesr.authservice.dto.TokenDto;
import es.dreyesr.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody NewUserDto newUserDto) {
        log.info("In: {}", newUserDto);
        return ResponseEntity.ok(authService.register(newUserDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(@RequestBody AuthUserDto authUserDto) {
        log.info("In: {}", authUserDto);
        return ResponseEntity.ok(authService.authenticate(authUserDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto) {
        log.info("In: {}", requestDto);
        return ResponseEntity.ok(authService.validate(token, requestDto));
    }
}
