package stream.camera.management.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import stream.camera.management.dto.DeviceInfoDto;
import stream.camera.management.dto.AuthDto;
import stream.camera.management.dto.DeviceInfoPlainDto;
import stream.camera.management.model.DeviceInfo;
import stream.camera.management.model.Auth;

@Component
public class UtilMapper {

    private ModelMapper modelMapper;

    public UtilMapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE);

    }

    public DeviceInfo mapDeviceInfoDtoToDeviceInfo(DeviceInfoDto deviceInfoDto, DeviceInfo deviceInfo) {
        this.modelMapper.map(deviceInfoDto, deviceInfo);
        return deviceInfo;
    }

    public DeviceInfoDto mapDeviceInfoToDeviceInfoDto(DeviceInfo deviceInfo, DeviceInfoDto deviceInfoDto) {
        this.modelMapper.map(deviceInfo, deviceInfoDto);
        return deviceInfoDto;
    }

    public DeviceInfoPlainDto mapDeviceInfoDtoToDeviceInfoPlainDto(DeviceInfoDto deviceInfoDto) {
        return new DeviceInfoPlainDto(deviceInfoDto.getUuid(), deviceInfoDto.getSerialNumber(), deviceInfoDto.getModel(),
                deviceInfoDto.getFirmwareVersion(), deviceInfoDto.getBuildDate(), deviceInfoDto.getHardwareId(),
                deviceInfoDto.getActive(), deviceInfoDto.getIpAddress(), deviceInfoDto.getStreamUrlList());
    }

    public AuthDto mapAuthToAuthDto(Auth auth, AuthDto authDto) {
        this.modelMapper.map(auth, authDto);
        return authDto;
    }
}
