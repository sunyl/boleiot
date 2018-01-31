package com.boleiot.service.impl;

import com.boleiot.mapper.DeviceMapper;
import com.boleiot.model.Device;
import com.boleiot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public int addDevice(Device device) {
        return deviceMapper.insert(device);
    }

    @Override
    public List<Device> getDeviceListByPage(int start, int limit, String keyword) {
        return deviceMapper.getDeviceListByPage(start, limit, keyword);
    }

    @Override
    public int getCount(String keyword) {
        return deviceMapper.getCount(keyword);
    }

    @Override
    public int activate(String no, String password, String hostname, int port) {
        return deviceMapper.activate(no, password, hostname, port);
    }

    @Override
    public Device selectByHostNameAndPort(String hostname, int port) {
        return deviceMapper.selectByHostNameAndPort(hostname, port);
    }

    @Override
    public Device selectByNo(String no) {
        return deviceMapper.selectByNo(no);
    }

    @Override
    public int deleteDeviceByNo(String no) {
        return deviceMapper.deleteDeviceByNo(no);
    }

    @Override
    public int updateDevice(String no, String name, String password, Long over_time, String address) {
        return deviceMapper.updateDevice(no, name, password, over_time, address);
    }
}
