package stream.camera.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "device_info")
public class DeviceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "build_date")
    private LocalDate buildDate;

    @Column(name = "hardware_id")
    private Long hardwareId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "stream_url_list")
    private String[] streamUrlList;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private LocalDate updateAt;

    @Column(name = "update_by")
    private String updateBy;

    public DeviceInfo() {
        super();
    }

    public DeviceInfo(String uuid, String serialNumber, String model, String firmwareVersion, LocalDate buildDate,
                      Long hardwareId, Boolean active, String ipAddress, String[] streamUrlList, LocalDate createAt,
                      String createBy, LocalDate updateAt, String updateBy) {
        this.uuid = uuid;
        this.serialNumber = serialNumber;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.buildDate = buildDate;
        this.hardwareId = hardwareId;
        this.active = active;
        this.ipAddress = ipAddress;
        this.streamUrlList = streamUrlList;
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

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public LocalDate getBuildDate() {
        return this.buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public Long getHardwareId() {
        return this.hardwareId;
    }

    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String[] getStreamUrlList() {
        return this.streamUrlList;
    }

    public void setStreamUrlList(String[] streamUrlList) {
        this.streamUrlList = streamUrlList;
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
        return "{" + " uuid='" + uuid + "'" + ", serialNumber='" + serialNumber + "'" + ", model='"
                + model + "'" + ", firmwareVersion='" + firmwareVersion + "'" + ", buildDate='" + buildDate
                + "'" + ", hardwareId='" + hardwareId + "'" + " active='" + active + "'"
                + " ipAddress='" + ipAddress + "'" + " streamUrlList='" + streamUrlList + "'"
                + " createAt='" + createAt + "'" + " createBy='" + createBy + "'" + " updateAt='" + updateAt + "'"
                + ", updateBy='" + updateBy + "'" + "}";
    }
}
