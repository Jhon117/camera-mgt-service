package stream.camera.management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stream.camera.management.dto.AuthDto;
import stream.camera.management.dto.AuthPlainDto;
import stream.camera.management.model.Auth;
import stream.camera.management.repository.AuthRepository;
import stream.camera.management.util.UtilMapper;

@Service
public class AuthService {

    private AuthRepository authRepository;

    @Autowired
    private UtilMapper utilMapper;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public List<AuthPlainDto> getAllAuth() {
        List<Auth> authList = authRepository.findAll();
        List<AuthPlainDto> authPlainDtoList = new ArrayList<>();
        authList.forEach((auth) -> {
            AuthDto authDto = new AuthDto();
            utilMapper.mapAuthToAuthDto(auth, authDto);
            AuthPlainDto authPlainDto = new AuthPlainDto(authDto.getUuid(), authDto.getUsername(), authDto.getPassword());
            authPlainDtoList.add(authPlainDto);
        });

        return authPlainDtoList;
    }

    public AuthPlainDto getAuthByUuid(String uuid) {
        Auth auth = authRepository.findByUuid(uuid);
        AuthDto authDto = new AuthDto();
        utilMapper.mapAuthToAuthDto(auth, authDto);
        return new AuthPlainDto(authDto.getUuid(), authDto.getUsername(), authDto.getPassword());
    }

    public void createAuth(List<AuthDto> authDtoList) {
        for(AuthDto authDto: authDtoList) {
            Auth auth = new Auth(authDto.getUuid(), authDto.getUsername(), authDto.getPassword(),
                    LocalDate.now(), authDto.getCreateBy(), null, null);
            this.authRepository.save(auth);
        }
    }

    public void updateAuth(List<AuthDto> authDtoList) {
        for(AuthDto authDto: authDtoList) {
            Auth originAuth = authRepository.findByUuid(authDto.getUuid());

            String username = authDto.getUsername() == null ? originAuth.getUsername() : authDto.getUsername();
            String password = authDto.getPassword() == null ? originAuth.getPassword() : authDto.getPassword();
            LocalDate createAt = authDto.getCreateAt() == null ? originAuth.getCreateAt() : authDto.getCreateAt();
            String createBy = authDto.getCreateBy() == null ? originAuth.getCreateBy() : authDto.getCreateBy();
            String updateBy = authDto.getUpdateBy() == null ? originAuth.getUpdateBy() : authDto.getUpdateBy();

            Auth auth = new Auth(authDto.getUuid(), username, password, createAt, createBy, LocalDate.now(), updateBy);
            this.authRepository.save(auth);
        }
    }

    public void deleteAuth(List<String> uuidList) {
        for(String uuid: uuidList) {
            Auth auth = authRepository.findByUuid(uuid);
            AuthDto authDto = new AuthDto();
            utilMapper.mapAuthToAuthDto(auth, authDto);
            authRepository.delete(auth);
        }
    }

    public void deleteAllAuth() {
        authRepository.deleteAll();
    }
}
