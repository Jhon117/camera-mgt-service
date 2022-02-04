package stream.camera.management.dto;

import java.time.LocalDate;

public class DeviceInfoPlainDto {
    private String uuid;
    private String serialNumber;
    private String model;
    private String firmwareVersion;
    private LocalDate buildDate;
    private Long hardwareId;
    private Boolean active;
    private String ipAddress;
    private String[] streamUrlList;

    public DeviceInfoPlainDto() {
        super();
    }

    public DeviceInfoPlainDto(String uuid, String serialNumber, String model, String firmwareVersion, LocalDate buildDate,
                              Long hardwareId, Boolean active, String ipAddress, String[] streamUrlList) {
        this.uuid = uuid;
        this.serialNumber = serialNumber;
        this.model = model;
        this.firmwareVersion = firmwareVersion;
        this.buildDate = buildDate;
        this.hardwareId = hardwareId;
        this.active = active;
        this.ipAddress = ipAddress;
        this.streamUrlList = streamUrlList;
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

    @Override
    public String toString() {
        return "{" + " uuid='" + uuid + "'" + ", serialNumber='" + serialNumber + "'" + ", model='"
                + model + "'" + ", firmwareVersion='" + firmwareVersion + "'" + ", buildDate='" + buildDate
                + "'" + ", hardwareId='" + hardwareId + "'" + " active='" + active + "'"
                + " ipAddress='" + ipAddress + "'" + " streamUrlList='" + streamUrlList + "'" + "}";
    }
}
