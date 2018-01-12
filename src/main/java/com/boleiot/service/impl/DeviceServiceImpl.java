package com.boleiot.service.impl;

import com.boleiot.mapper.DeviceMapper;
import com.boleiot.model.Device;
import com.boleiot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public int addDevice(Device device) {
        return deviceMapper.insert(device);
    }
}
