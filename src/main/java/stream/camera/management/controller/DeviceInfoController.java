package stream.camera.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import stream.camera.management.dto.DeviceInfoDto;
import stream.camera.management.dto.DeviceInfoPlainDto;
import stream.camera.management.service.DeviceInfoService;

@RestController
@RequestMapping("/camera/device-info")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping("all")
    public String getAllDeviceInfo() {
        try {
            List<DeviceInfoPlainDto> deviceInfoPlainDtoList = deviceInfoService.getAllDeviceInfo();
            return deviceInfoPlainDtoList.toString();
        } catch (Exception e) {
            return "Failed to get all device info table rows due to " + e.getMessage();
        }

    }

    @GetMapping("{uuid}")
    public String getDeviceInfoByUuid(@PathVariable(value = "uuid") String uuid) {
        try {
            DeviceInfoPlainDto deviceInfoPlainDto = deviceInfoService.getDeviceInfoByUuid(uuid);
            return deviceInfoPlainDto.toString();
        } catch (Exception e) {
            return "Failed to get device info row by uuid due to " + e.getMessage();
        }
    }

    @GetMapping("/stream-url/{uuid}")
    public String getDeviceStreamUrlByUuid(@PathVariable(value = "uuid") String uuid) {
        try {
            String[] streamUrlList = deviceInfoService.getDeviceStreamUrlByUuid(uuid);
            String outPut = "Find stream url list for uuid " + uuid + " is ";
            for (String streamUrl : streamUrlList) {
                outPut += "\n" + streamUrl;
            }
            return outPut;
        } catch (Exception e) {
            return "Failed to get device stream url list by uuid due to " + e.getMessage();
        }
    }

    @GetMapping("/ip-address/{uuid}")
    public String getDeviceIpAddressByUuid(@PathVariable(value = "uuid") String uuid) {
        try {
            return "Find device ip address for uuid " + uuid + " is " + deviceInfoService.getDeviceIpAddressByUuid(uuid);
        } catch (Exception e) {
            return "Failed to get device ip address by uuid due to " + e.getMessage();
        }
    }

    @PostMapping("")
    public String createDeviceInfo(@RequestBody List<DeviceInfoDto> deviceInfoDtoList) {
        try {
            deviceInfoService.createDeviceInfo(deviceInfoDtoList);
            return "Successfully create the device_info table row(s)";
        } catch (Exception e) {
            return "Failed to create the device_info table row(s) due to " + e.getMessage();
        }
    }

    @PutMapping("")
    public String updateDeviceInfo(@RequestBody List<DeviceInfoDto> deviceInfoDtoList) {
        try {
            deviceInfoService.updateDeviceInfo(deviceInfoDtoList);
            return "Successfully update the device_info table row(s)";
        } catch (Exception e) {
            return "Failed to update the device_info table row(s) due to " + e.getMessage();
        }
    }

    @DeleteMapping("")
    public String deleteDeviceInfo(@RequestBody List<String> uuidList) {
        try {
            deviceInfoService.deleteDeviceInfo(uuidList);
            return "Successfully delete the device_info table row(s)";
        } catch (Exception e) {
            return "Failed to delete the device_info table row(s) due to " + e.getMessage();
        }
    }

    @DeleteMapping("all")
    public String deleteAllDeviceInfo() {
        try {
            deviceInfoService.deleteAllDeviceInfo();
            return "All device info are deleted successfully";
        } catch (Exception e) {
            return "Failed to delete all device_info table row(s) due to " + e.getMessage();
        }
    }
}
