package com.boleiot.controller;

import com.boleiot.model.Device;
import com.boleiot.service.DeviceService;
import com.boleiot.utils.DatatablesView;
import com.boleiot.utils.HttpResult;
import com.boleiot.utils.JsonUtil;
import com.boleiot.utils.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        return JsonUtil.toJson(HttpResult.ok(UidUtil.getUUID_8()));
    }

    @RequestMapping(value = "/getDeviceList", method = RequestMethod.POST)
    public String getUserListAction(HttpServletRequest request) {
        int draw = request.getParameter("draw") == null ? 1 : Integer.valueOf(request.getParameter("draw"));
        int limit = request.getParameter("limit") == null ? 10 : Integer.valueOf(request.getParameter("limit"));
        int start = request.getParameter("start") == null ? 0 : Integer.valueOf(request.getParameter("start"));
        int page = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
        String search = request.getParameter("search");
        System.out.println("--->UserController:getUserList limit = " + limit + ",start=" + start + ",page=" + page
                + ",draw=" + draw + ",search=" + search);

        List<Device> users = deviceService.getDeviceListByPage(start, limit, search);
        DatatablesView<Device> dataTable = new DatatablesView<>();
        dataTable.setData(users);
        dataTable.setRecordsFiltered(deviceService.getCount(search));
        dataTable.setRecordsTotal(users.size());
        dataTable.setDraw(draw);
        return JsonUtil.toJson(dataTable);
    }

    @RequestMapping(value = "/deleteDevice/{no}", method = RequestMethod.DELETE)
    public HttpResult deleteDevice(@PathVariable("no") String no) {
        System.out.println("--->deleteDevice no = " + no);
        int row = deviceService.deleteDeviceByNo(no);
        if (row > 0) {
            return new HttpResult(true);
        }
        return new HttpResult(201, "删除失败", "");
    }
}
