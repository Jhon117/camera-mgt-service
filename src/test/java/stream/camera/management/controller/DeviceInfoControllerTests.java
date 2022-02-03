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
import stream.camera.management.dto.DeviceInfoDto;
import stream.camera.management.dto.DeviceInfoPlainDto;
import stream.camera.management.model.DeviceInfo;
import stream.camera.management.service.DeviceInfoService;
import stream.camera.management.util.UtilMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DeviceInfoControllerTests {
    @Mock
    private DeviceInfoService deviceInfoService;

    @InjectMocks
    private DeviceInfoController deviceInfoController;

    private DeviceInfo deviceInfo;

    private List<DeviceInfo> deviceInfoList;

    private List<DeviceInfoDto> deviceInfoDtoList;

    private UtilMapper utilMapper;

    @Before
    public void init() {
        utilMapper = new UtilMapper();
        deviceInfoList = new ArrayList<>();
        deviceInfoDtoList = new ArrayList<>();
        deviceInfo = new DeviceInfo();
        deviceInfo.setUuid("testUuid");
        deviceInfo.setSerialNumber("test-serial");
        deviceInfo.setModel("IP Camera");
        deviceInfo.setActive(true);
        deviceInfo.setBuildDate(LocalDate.now());
        deviceInfo.setHardwareId((long) 1.023);
        deviceInfo.setFirmwareVersion("v1");
        deviceInfo.setIpAddress("192.168.101.1");
        deviceInfo.setStreamUrlList(new String[]{"rtsp://stream1", "rtsp://stream2"});
        deviceInfo.setCreateAt(LocalDate.now());
        deviceInfo.setCreateBy("Tester");
        deviceInfo.setUpdateAt(LocalDate.now());
        deviceInfo.setUpdateBy("Tester2");
        deviceInfoList.add(deviceInfo);
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDtoList.add(utilMapper.mapDeviceInfoToDeviceInfoDto(deviceInfo,deviceInfoDto));
    }

    @Test
    public void getAllDeviceInfo_isSuccess() {
        List<DeviceInfoPlainDto> deviceInfoPlainDtoList = new ArrayList<>();
        deviceInfoList.forEach((deviceInfo) -> {
            DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
            utilMapper.mapDeviceInfoToDeviceInfoDto(deviceInfo, deviceInfoDto);
            deviceInfoPlainDtoList.add(utilMapper.mapDeviceInfoDtoToDeviceInfoPlainDto(deviceInfoDto));
        });

        Mockito.when(deviceInfoService.getAllDeviceInfo()).thenReturn(deviceInfoPlainDtoList);
        String deviceInfoResponse = deviceInfoController.getAllDeviceInfo();
        Assert.assertEquals(deviceInfoPlainDtoList.toString(), deviceInfoResponse);
    }

    @Test
    public void getAllDeviceInfo_isFailed() {
        Mockito.when(deviceInfoService.getAllDeviceInfo()).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String deviceInfoResponse = deviceInfoController.getAllDeviceInfo();
        Assert.assertEquals("Failed to get all device info table rows due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void getDeviceInfoByUuid_isSuccess() {
        DeviceInfoPlainDto deviceInfoPlainDto = new DeviceInfoPlainDto(deviceInfo.getUuid(), deviceInfo.getSerialNumber(),
                deviceInfo.getModel(), deviceInfo.getFirmwareVersion(), deviceInfo.getBuildDate(), deviceInfo.getHardwareId(),
                deviceInfo.getActive(),deviceInfo.getIpAddress(), deviceInfo.getStreamUrlList());
        Mockito.when(deviceInfoService.getDeviceInfoByUuid("testUuid")).thenReturn(deviceInfoPlainDto);
        String deviceInfoResponse = deviceInfoController.getDeviceInfoByUuid("testUuid");
        Assert.assertEquals(deviceInfoPlainDto.toString(), deviceInfoResponse);
    }

    @Test
    public void getDeviceInfoByUuid_isFailed() {
        Mockito.when(deviceInfoService.getDeviceInfoByUuid("testUuid")).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String deviceInfoResponse = deviceInfoController.getDeviceInfoByUuid("testUuid");
        Assert.assertEquals("Failed to get device info row by uuid due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void getDeviceStreamUrlByUuid_isSuccess() {
        String[] mockStreamList = new String[]{"rtsp://stream1", "rtsp://stream2"};

        String outPut = "Find stream url list for uuid testUuid is ";
        for (String streamUrl : mockStreamList) {
            outPut += "\n" + streamUrl;
        }

        Mockito.when(deviceInfoService.getDeviceStreamUrlByUuid("testUuid")).thenReturn(mockStreamList);
        String deviceInfoResponse = deviceInfoController.getDeviceStreamUrlByUuid("testUuid");
        Assert.assertEquals(outPut, deviceInfoResponse);
    }

    @Test
    public void getDeviceStreamUrlByUuid_isFailed() {
        Mockito.when(deviceInfoService.getDeviceStreamUrlByUuid("testUuid")).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String deviceInfoResponse = deviceInfoController.getDeviceStreamUrlByUuid("testUuid");
        Assert.assertEquals("Failed to get device stream url list by uuid due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void getDeviceIpAddressByUuid_isSuccess() {
        Mockito.when(deviceInfoService.getDeviceIpAddressByUuid("testUuid")).thenReturn("192.168.101.1");
        String deviceInfoResponse = deviceInfoController.getDeviceIpAddressByUuid("testUuid");
        Assert.assertEquals("Find device ip address for uuid testUuid is 192.168.101.1", deviceInfoResponse);
    }

    @Test
    public void getDeviceIpAddressByUuid_isFailed() {
        Mockito.when(deviceInfoService.getDeviceIpAddressByUuid("testUuid")).thenThrow(new CannotGetJdbcConnectionException("Database not connected"));
        String deviceInfoResponse = deviceInfoController.getDeviceIpAddressByUuid("testUuid");
        Assert.assertEquals("Failed to get device ip address by uuid due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void createDeviceInfo_isSuccess() {
        String deviceInfoResponse = deviceInfoController.createDeviceInfo(deviceInfoDtoList);
        Assert.assertEquals("Successfully create the device_info table row(s)", deviceInfoResponse);
    }

    @Test
    public void createDeviceInfo_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(deviceInfoService).createDeviceInfo(deviceInfoDtoList);
        String deviceInfoResponse = deviceInfoController.createDeviceInfo(deviceInfoDtoList);
        Assert.assertEquals("Failed to create the device_info table row(s) due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void updateDeviceInfo_isSuccess() {
        String deviceInfoResponse = deviceInfoController.updateDeviceInfo(deviceInfoDtoList);
        Assert.assertEquals("Successfully update the device_info table row(s)", deviceInfoResponse);
    }

    @Test
    public void updateDeviceInfo_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(deviceInfoService).updateDeviceInfo(deviceInfoDtoList);
        String deviceInfoResponse = deviceInfoController.updateDeviceInfo(deviceInfoDtoList);
        Assert.assertEquals("Failed to update the device_info table row(s) due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void deleteDeviceInfo_isSuccess() {
        String deviceInfoResponse = deviceInfoController.deleteDeviceInfo(new ArrayList<String>());
        Assert.assertEquals("Successfully delete the device_info table row(s)", deviceInfoResponse);
    }

    @Test
    public void deleteDeviceInfo_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(deviceInfoService).deleteDeviceInfo(new ArrayList<String>());
        String deviceInfoResponse = deviceInfoController.deleteDeviceInfo(new ArrayList<String>());
        Assert.assertEquals("Failed to delete the device_info table row(s) due to Database not connected", deviceInfoResponse);
    }

    @Test
    public void deleteAllDeviceInfo_isSuccess() {
        String deviceInfoResponse = deviceInfoController.deleteAllDeviceInfo();
        Assert.assertEquals("All device info are deleted successfully", deviceInfoResponse);
    }

    @Test
    public void deleteAllDeviceInfo_isFailed() {
        Mockito.doThrow(new CannotGetJdbcConnectionException("Database not connected")).when(deviceInfoService).deleteAllDeviceInfo();
        String deviceInfoResponse = deviceInfoController.deleteAllDeviceInfo();
        Assert.assertEquals("Failed to delete all device_info table row(s) due to Database not connected", deviceInfoResponse);
    }
}
