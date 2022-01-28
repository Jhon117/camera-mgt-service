package stream.camera.management.dto;

public class AuthPlainDto {
    private String uuid;
    private String username;
    private String password;

    public AuthPlainDto() {
        super();
    }

    public AuthPlainDto(String uuid, String username, String password) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" + " uuid='" + uuid + "'" + ", username='" + username + "'" + ", password='" + password + "'" + "}";
    }
}
