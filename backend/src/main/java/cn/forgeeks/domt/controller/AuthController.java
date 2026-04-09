package cn.forgeeks.domt.controller;

import cn.forgeeks.domt.dto.LoginRequest;
import cn.forgeeks.domt.dto.LoginResponse;
import cn.forgeeks.domt.dto.Result;
import cn.forgeeks.domt.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest,
                                       HttpSession session) {
        LoginResponse response = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("loginUser", response);
        return Result.success(response);
    }

    @PostMapping("/logout")
    public Result<Void> logout(HttpSession session) {
        session.invalidate();
        return Result.success();
    }

    @GetMapping("/current")
    public Result<LoginResponse> current(HttpSession session) {
        LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
        if (loginUser == null) {
            return Result.error(401, "未登录");
        }
        return Result.success(loginUser);
    }
}
