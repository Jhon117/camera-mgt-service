package stream.camera.management.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import stream.camera.management.dto.AuthDto;
import stream.camera.management.dto.AuthPlainDto;
import stream.camera.management.model.Auth;
import stream.camera.management.service.AuthService;
import stream.camera.management.util.UtilMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTests {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private Auth auth;

    private List<Auth> authList;

    private List<AuthDto> authDtoList;

    private UtilMapper utilMapper;

    @Before
    public void init() {
        utilMapper = new UtilMapper();
        authList = new ArrayList<>();
        authDtoList = new ArrayList<>();
        auth = new Auth();
        auth.setUuid("testUuid");
        auth.setUsername("Admin");
        auth.setPassword("Password");
        auth.setCreateAt(LocalDate.now());
        auth.setCreateBy("Tester");
        auth.setUpdateAt(LocalDate.now());
        auth.setUpdateBy("Tester2");
        authList.add(auth);
        AuthDto authDto = new AuthDto();
        authDtoList.add(utilMapper.mapAuthToAuthDto(auth, authDto));
    }

    @Test
    public void getAllAuth_isSuccess() {
        List<AuthPlainDto> authPlainDtoList = new ArrayList<>();
        authList.forEach((auth) -> {
            AuthDto authDto = new AuthDto();
            utilMapper.mapAuthToAuthDto(auth, authDto);
            AuthPlainDto authPlainDto = new AuthPlainDto(authDto.getUuid(), authDto.getUsername(), authDto.getPassword());
            authPlainDtoList.add(authPlainDto);
        });
        Mockito.when(authService.getAllAuth()).thenReturn(authPlainDtoList);
        String authResponse = authController.getAllAuth();
        Assert.assertEquals(authPlainDtoList.toString(), authResponse);
    }

    @Test
    public void getAllAuth_isFailed() {
        Mockito.when(authService.getAllAuth()).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String authResponse = authController.getAllAuth();
        Assert.assertEquals("Failed to get all auth table rows due to Database not connected", authResponse);
    }

    @Test
    public void getAuthByUuid_isSuccess() {
        AuthPlainDto authPlainDto = new AuthPlainDto(auth.getUuid(), auth.getUsername(), auth.getPassword());
        Mockito.when(authService.getAuthByUuid("testUuid")).thenReturn(authPlainDto);
        String authResponse = authController.getAuthByUuid("testUuid");
        Assert.assertEquals(authPlainDto.toString(), authResponse);
    }

    @Test
    public void getAuthByUuid_isFailed() {
        Mockito.when(authService.getAuthByUuid("testUuid")).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String authResponse = authController.getAuthByUuid("testUuid");
        Assert.assertEquals("Failed to get auth row by uuid due to Database not connected", authResponse);
    }

    @Test
    public void createAuth_isSuccess() {
        String authResponse = authController.createAuth(authDtoList);
        Assert.assertEquals("Successfully create the auth table row(s)", authResponse);
    }

    @Test
    public void createAuth_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(authService).createAuth(authDtoList);
        String authResponse = authController.createAuth(authDtoList);
        Assert.assertEquals("Failed to create auth table row(s) due to Database not connected", authResponse);
    }

    @Test
    public void updateAuth_isSuccess() {
        String authResponse = authController.updateAuth(authDtoList);
        Assert.assertEquals("Successfully update the auth table row(s)", authResponse);
    }

    @Test
    public void updateAuth_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(authService).updateAuth(authDtoList);
        String authResponse = authController.updateAuth(authDtoList);
        Assert.assertEquals("Failed to update auth table row(s) due to Database not connected", authResponse);
    }

    @Test
    public void deleteAuth_isSuccess() {
        String authResponse = authController.deleteAuth(new ArrayList<String>());
        Assert.assertEquals("Successfully delete the auth table row(s)", authResponse);
    }

    @Test
    public void deleteAuth_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(authService).deleteAuth(new ArrayList<String>());
        String authResponse = authController.deleteAuth(new ArrayList<String>());
        Assert.assertEquals("Failed to delete auth table row(s) due to Database not connected", authResponse);
    }

    @Test
    public void deleteAllAuth_isSuccess() {
        String authResponse = authController.deleteAllAuth();
        Assert.assertEquals("All Auth are deleted successfully", authResponse);
    }

    @Test
    public void deleteAllAuth_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(authService).deleteAllAuth();
        String authResponse = authController.deleteAllAuth();
        Assert.assertEquals("Failed to delete All auth table rows due to Database not connected", authResponse);
    }
}
