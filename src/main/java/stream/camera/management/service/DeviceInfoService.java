package stream.camera.management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stream.camera.management.dto.AuthDto;
import stream.camera.management.dto.DeviceInfoDto;
import stream.camera.management.dto.DeviceInfoPlainDto;
import stream.camera.management.model.Auth;
import stream.camera.management.model.DeviceInfo;
import stream.camera.management.repository.DeviceInfoRepository;
import stream.camera.management.util.UtilMapper;

@Service
public class DeviceInfoService {

    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private UtilMapper utilMapper;

    @Autowired
    public DeviceInfoService(DeviceInfoRepository deviceInfoRepository) {
        this.deviceInfoRepository = deviceInfoRepository;
    }

    public void createDeviceInfo(List<DeviceInfoDto> deviceInfoDtoList) {
        for(DeviceInfoDto deviceInfoDto: deviceInfoDtoList) {
            DeviceInfo deviceInfo = new DeviceInfo(deviceInfoDto.getUuid(), deviceInfoDto.getSerialNumber(),
                    deviceInfoDto.getModel(), deviceInfoDto.getFirmwareVersion(), deviceInfoDto.getBuildDate(),
                    deviceInfoDto.getHardwareId(), deviceInfoDto.getActive(), deviceInfoDto.getIpAddress(),
                    deviceInfoDto.getStreamUrlList(), LocalDate.now(), deviceInfoDto.getCreateBy(),
                    null, null);
            this.deviceInfoRepository.save(deviceInfo);
        }
    }

    public List<DeviceInfoPlainDto> getAllDeviceInfo() {
        List<DeviceInfo> deviceInfoList = deviceInfoRepository.findAll();
        List<DeviceInfoPlainDto> deviceInfoPlainDtoList = new ArrayList<>();
        deviceInfoList.forEach((deviceInfo) -> {
            DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
            utilMapper.mapDeviceInfoToDeviceInfoDto(deviceInfo, deviceInfoDto);
            deviceInfoPlainDtoList.add(utilMapper.mapDeviceInfoDtoToDeviceInfoPlainDto(deviceInfoDto));
        });

        return deviceInfoPlainDtoList;
    }

    public DeviceInfoPlainDto getDeviceInfoByUuid(String uuid) {
        DeviceInfo deviceInfo = deviceInfoRepository.findByUuid(uuid);
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        utilMapper.mapDeviceInfoToDeviceInfoDto(deviceInfo, deviceInfoDto);
        return utilMapper.mapDeviceInfoDtoToDeviceInfoPlainDto(deviceInfoDto);
    }

    public String[] getDeviceStreamUrlByUuid(String uuid) {
        DeviceInfo deviceInfo = deviceInfoRepository.findByUuid(uuid);
        return deviceInfo.getStreamUrlList();
    }

    public String getDeviceIpAddressByUuid(String uuid) {
        DeviceInfo deviceInfo = deviceInfoRepository.findByUuid(uuid);
        return deviceInfo.getIpAddress();
    }

    public void updateDeviceInfo(List<DeviceInfoDto> deviceInfoDtoList) {
        for(DeviceInfoDto deviceInfoDto: deviceInfoDtoList) {
            //TODO: judge if uuid exist is request
            DeviceInfo originDeviceInfo = deviceInfoRepository.findByUuid(deviceInfoDto.getUuid());

            String serialNumber = deviceInfoDto.getSerialNumber() == null ? originDeviceInfo.getSerialNumber() : deviceInfoDto.getSerialNumber();
            String model = deviceInfoDto.getModel() == null ? originDeviceInfo.getModel() : deviceInfoDto.getModel();
            String firmwareVersion = deviceInfoDto.getFirmwareVersion() == null ? originDeviceInfo.getFirmwareVersion() : deviceInfoDto.getFirmwareVersion();
            LocalDate buildDate = deviceInfoDto.getBuildDate() == null ? originDeviceInfo.getBuildDate() : deviceInfoDto.getBuildDate();
            Long hardwareId = deviceInfoDto.getHardwareId() == null ? originDeviceInfo.getHardwareId() : deviceInfoDto.getHardwareId();
            boolean active = deviceInfoDto.getActive() == null ? originDeviceInfo.getActive() : deviceInfoDto.getActive();
            String ipAddress = deviceInfoDto.getIpAddress() == null ? originDeviceInfo.getIpAddress() : deviceInfoDto.getIpAddress();
            String[] streamUrlList = deviceInfoDto.getStreamUrlList() == null ? originDeviceInfo.getStreamUrlList() : deviceInfoDto.getStreamUrlList();
            LocalDate createAt = deviceInfoDto.getCreateAt() == null ? originDeviceInfo.getCreateAt() : deviceInfoDto.getCreateAt();
            String createBy = deviceInfoDto.getCreateBy() == null ? originDeviceInfo.getCreateBy() : deviceInfoDto.getCreateBy();
            String updateBy = deviceInfoDto.getUpdateBy() == null ? originDeviceInfo.getUpdateBy() : deviceInfoDto.getUpdateBy();

            DeviceInfo deviceInfo = new DeviceInfo(deviceInfoDto.getUuid(), serialNumber, model, firmwareVersion,
                    buildDate, hardwareId, active, ipAddress, streamUrlList, createAt, createBy, LocalDate.now(), updateBy);
            this.deviceInfoRepository.save(deviceInfo);
        }
    }

    public void deleteDeviceInfo(List<String> uuidList) {
        for(String uuid: uuidList) {
            DeviceInfo deviceInfo = deviceInfoRepository.findByUuid(uuid);
            DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
            utilMapper.mapDeviceInfoToDeviceInfoDto(deviceInfo, deviceInfoDto);
            deviceInfoRepository.delete(deviceInfo);
        }
    }

    public void deleteAllDeviceInfo() {
        deviceInfoRepository.deleteAll();
    }
}
