package com.boleiot.mapper;


import com.boleiot.model.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMapper {

    int deleteByPrimaryKey(Long id);

    int deleteDeviceByNo(@Param("no") String no);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getDeviceListByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keyword") String keyword);

    int getCount(@Param("keyword") String keyword);

    int activate(@Param("no") String no, @Param("password") String password, @Param("hostname") String hostname, @Param("port") int port);

    Device selectByHostNameAndPort(@Param("hostname") String hostname, @Param("port") int port);

    Device selectByNo(@Param("no") String no);
}