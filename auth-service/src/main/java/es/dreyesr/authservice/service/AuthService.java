package es.dreyesr.authservice.service;

import es.dreyesr.authservice.dto.AuthUserDto;
import es.dreyesr.authservice.dto.NewUserDto;
import es.dreyesr.authservice.dto.RequestDto;
import es.dreyesr.authservice.dto.TokenDto;
import es.dreyesr.authservice.entity.Token;
import es.dreyesr.authservice.repository.TokenRepository;
import es.dreyesr.authservice.enumeration.TokenType;
import es.dreyesr.authservice.enumeration.Role;
import es.dreyesr.authservice.entity.User;
import es.dreyesr.authservice.repository.UserRepository;
import es.dreyesr.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public TokenDto register(NewUserDto request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }

    public TokenDto authenticate(AuthUserDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public TokenDto validate(String token, RequestDto requestDto) {

        if (!jwtService.isTokenValid(token))
            return null;
        String email = jwtService.extractEmail(token);
        if (!userRepository.findByEmail(email).isPresent())
            return null;

        // TODO routeValidation

        return new TokenDto(token);
    }
}
