package stream.camera.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stream.camera.management.dto.DeviceInfoDto;
import stream.camera.management.dto.DeviceInfoPlainDto;
import stream.camera.management.service.DeviceInfoService;

@RestController
@RequestMapping("/camera/device-info")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping("all")
    public List<DeviceInfoPlainDto> getAllDeviceInfo() {
        return deviceInfoService.getAllDeviceInfo();

    }

    @GetMapping("{uuid}")
    public DeviceInfoPlainDto getDeviceInfoByUuid(@PathVariable(value = "uuid") String uuid) {
        return deviceInfoService.getDeviceInfoByUuid(uuid);
    }

    @GetMapping("/stream-url/{uuid}")
    public String getDeviceStreamUrlByUuid(@PathVariable(value = "uuid") String uuid) {
        return "Find stream url list for uuid " + uuid + "is " + deviceInfoService.getDeviceStreamUrlByUuid(uuid);
    }

    @GetMapping("/ip-address/{uuid}")
    public String getDeviceIpAddressByUuid(@PathVariable(value = "uuid") String uuid) {
        return "Find stream url list for uuid " + uuid + "is " + deviceInfoService.getDeviceIpAddressByUuid(uuid);
    }

    @PostMapping("")
    public String createDeviceInfo(@RequestBody List<DeviceInfoDto> deviceInfoDtoList) {
        deviceInfoService.createDeviceInfo(deviceInfoDtoList);
        return "Successfully create the device_info table row(s)";
    }

    @PatchMapping("")
    public String updateDeviceInfo(@RequestBody List<DeviceInfoDto> deviceInfoDtoList) {
        deviceInfoService.updateDeviceInfo(deviceInfoDtoList);
        return "Successfully update the device_info table row(s)";
    }

    @DeleteMapping("")
    public String deleteDeviceInfo(@RequestBody List<String> uuidList) {
        deviceInfoService.deleteDeviceInfo(uuidList);
        return "Successfully delete the device_info table row(s)";
    }

    @DeleteMapping("all")
    public String deleteAllDeviceInfo() {
        deviceInfoService.deleteAllDeviceInfo();
        return "All device info are deleted successfully";
    }
}