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
    public String getAllAuth() {
        try {
            List<AuthPlainDto> authPlainDtoList = authService.getAllAuth();
            return authPlainDtoList.toString();
        } catch (Exception e) {
            return "Failed to get all auth table rows due to " + e.getMessage();
        }
    }

    @GetMapping("{uuid}")
    public String getAuthByUuid(@PathVariable(value = "uuid") String uuid) {
        try {
            AuthPlainDto authPlainDto = authService.getAuthByUuid(uuid);
            return authPlainDto.toString();
        } catch (Exception e) {
            return "Failed to get auth row by uuid due to " + e.getMessage();
        }
    }

    @PostMapping("")
    public String createAuth(@RequestBody List<AuthDto> authDtoList) {
        try {
            authService.createAuth(authDtoList);
            return "Successfully create the auth table row(s)";
        } catch (Exception e) {
            return "Failed to create auth table row(s) due to " + e.getMessage();
        }
    }

    @PutMapping("")
    public String updateAuth(@RequestBody List<AuthDto> authDtoList) {
        try {
            authService.updateAuth(authDtoList);
            return "Successfully update the auth table row(s)";
        } catch (Exception e) {
            return "Failed to update auth table row(s) due to " + e.getMessage();
        }
    }

    @DeleteMapping("")
    public String deleteAuth(@RequestBody List<String> uuidList) {
        try {
            authService.deleteAuth(uuidList);
            return "Successfully delete the auth table row(s)";
        } catch (Exception e) {
            return "Failed to delete auth table row(s) due to " + e.getMessage();
        }
    }

    @DeleteMapping("all")
    public String deleteAllAuth() {
        try {
            authService.deleteAllAuth();
            return "All Auth are deleted successfully";
        } catch (Exception e) {
            return "Failed to delete All auth table rows due to " + e.getMessage();
        }
    }
}
