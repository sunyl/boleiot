package com.boleiot.service;

import com.boleiot.model.Device;

import java.util.List;

public interface DeviceService {

    int addDevice(Device device);

    List<Device> getDeviceListByPage(int start, int limit, String keyword);

    int getCount(String keyword);

    int activate(String no, String password, String hostname, int port);

    Device selectByHostNameAndPort(String hostname, int port);

    Device selectByNo(String no);

    int deleteDeviceByNo(String no);

    int updateDevice(String no, String name, String password, Long over_time, String address);
}
