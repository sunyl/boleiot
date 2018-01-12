package com.boleiot.controller;

import com.boleiot.model.Device;
import com.boleiot.service.DeviceService;
import com.boleiot.utils.HttpResult;
import com.boleiot.utils.JsonUtil;
import com.boleiot.utils.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDevice(@RequestBody Device device) {
        int row = deviceService.addDevice(device);
        return JsonUtil.toJson(row > 0 ? HttpResult.ok() : HttpResult.build(400, "添加设备失败"));
    }

    @RequestMapping(value = "/getNo", method = RequestMethod.GET)
    public String getDeviceNo() {
        return JsonUtil.toJson(HttpResult.ok(UidUtil.getUUID()));
    }
}
