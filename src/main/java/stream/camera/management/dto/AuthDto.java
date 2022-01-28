package stream.camera.management.dto;

import java.time.LocalDate;

public class AuthDto {

    private String uuid;
    private String username;
    private String password;
    private LocalDate createAt;
    private String createBy;
    private LocalDate updateAt;
    private String updateBy;

    public AuthDto() {
        super();
    }

    public AuthDto(String uuid, String username, String password, LocalDate createAt, String createBy,
                         LocalDate updateAt, String updateBy) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
        this.createBy = createBy;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
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

    public LocalDate getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "{" + " uuid='" + uuid + "'" + ", username='" + username + "'" + ", password='" + password + "'" +
                " createAt='" + createAt + "'" + " createBy='" + createBy + "'" + " updateAt='" + updateAt + "'"
                + ", updateBy='" + updateBy + "'" + "}";
    }
}
