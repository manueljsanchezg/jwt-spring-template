package manugtx.jwtexample.auth;

import jakarta.validation.Valid;
import manugtx.jwtexample.dto.LoginRequest;
import manugtx.jwtexample.dto.LoginResponse;
import manugtx.jwtexample.dto.RegisterRequest;
import manugtx.jwtexample.security.jwt.JwtUtil;
import manugtx.jwtexample.user.User;
import manugtx.jwtexample.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateToken(authentication);
        String username = jwtUtil.getUsernameFromToken(jwtToken);
        List<String> roles = jwtUtil.getAuthoritiesFromToken(jwtToken);
        LoginResponse response = new LoginResponse(jwtToken, username, roles);
        return ResponseEntity.ok(response);
    }
}
