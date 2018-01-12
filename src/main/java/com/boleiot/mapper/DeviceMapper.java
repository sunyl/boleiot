package com.boleiot.mapper;


import com.boleiot.model.Device;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}