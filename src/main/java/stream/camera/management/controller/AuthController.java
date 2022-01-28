package stream.camera.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stream.camera.management.dto.AuthDto;
import stream.camera.management.dto.AuthPlainDto;
import stream.camera.management.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/camera/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("all")
    public List<AuthPlainDto> getAllAuth() {
        return authService.getAllAuth();
    }

    @GetMapping("{uuid}")
    public AuthPlainDto getAuthByUuid(@PathVariable(value = "uuid") String uuid) {
        return authService.getAuthByUuid(uuid);
    }

    @PostMapping("")
    public String createAuth(@RequestBody List<AuthDto> authDtoList) {
        authService.createAuth(authDtoList);
        return "Successfully create the auth table row(s)";
    }

    @PatchMapping("")
    public String updateAuth(@RequestBody List<AuthDto> authDtoList) {
        authService.updateAuth(authDtoList);
        return "Successfully update the auth table row(s)";
    }

    @DeleteMapping("")
    public String deleteAuth(@RequestBody List<String> uuidList) {
        authService.deleteAuth(uuidList);
        return "Successfully delete the auth table row(s)";
    }

    @DeleteMapping("all")
    public String deleteAllAuth() {
        authService.deleteAllAuth();
        return "All Auth are deleted successfully";
    }
}
