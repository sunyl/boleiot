function addDevice() {
    if ($('#no').val() == "") {
        alert("设备编号不能为空");
        return;
    }
    if ($('#password').val() == "") {
        alert("密码不能为空");
        return;
    }
    if ($('#name').val() == "") {
        alert("设备名称不能为空");
        return;
    }
    var str = {
        "no": $('#no').val(),
        "password": $('#password').val(),
        "name": $('#name').val(),
        "overTime": $('#over_time').val(),
        "comments": $('#desc').val(),
        "address": $('#address').val()
    };
    $.ajax({
        type: "POST",
        url: "/boleiot/device/add",
        data: JSON.stringify(str),
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            alert("添加成功！");
        },
        error: function (data) {
            alert("添加失败！");
        }
    });
}

function getDeviceNo() {
    var no;
    $.ajax({
        async: false,
        type: "GET",
        url: "/boleiot/device/getNo",
        dataType: 'json',
        success: function (data) {
            no = data.data;
        },
        error: function (data) {
        }
    });
    return no;
}

function getStatus(status) {
    if (status == null || status == 0) {
        return "<div><span class='label label-default'>离线</span></div>";
    }
    return "<div><span class='label label-success'>在线</span></div>";
}

function getOverTime(overTime) {
    if (overTime == null || overTime == 0) {
        return "0秒（不过期）";
    }
    return overTime + "秒";
}

function terminal_list() {

    var tables = $("#table-user").dataTable({
        serverSide: true,// 分页，取数据等等的都放到服务端去
        processing: true,// 载入数据的时候是否显示“载入中”
        pageLength: 10,  // 首次加载的数据条数
        ordering: false, // 排序操作在服务端进行，所以可以关了。
        pagingType: "full_numbers",
        autoWidth: false,
        stateSave: true,// 保持翻页状态，和comTable.fnDraw(false);结合使用
        searching: false,
        bLengthChange: false,
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param.draw = data.draw;
            param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
            param.start = data.start;//开始的记录序号
            param.page = (data.start / data.length) + 1;//当前页码
            param.search = $('#keyword').val();
            param.orderby = data.orderBys;
            $.ajax({
                type: "post",
                url: "device/getDeviceList",
                cache: false,
                data: param,
                dataType: "json",
                success: function (result) {
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.recordsTotal;//返回数据全部记录
                    returnData.recordsFiltered = result.recordsFiltered;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.data;//返回的数据列表
                    callback(returnData);

                }
            });
        },
        columns: [
            {"data": null, "width": "10px"},
            {"data": null, "width": "80px"},
            {"data": 'no'},
            {"data": 'password'},
            {"data": 'name'},
            {"data": null, "width": "150px"},
            {"data": 'address'},
            {"data": null, "width": "100px"}
        ],
        // 操作按钮
        columnDefs: [
            {
                targets: 0,
                defaultContent: "<input type='checkbox' name='checkList'>"
            },
            {
                targets: 1,
                render: function (data, type, row, meta) {
                    return getStatus(row.status);
                }
            },
            {
                targets: 5,
                render: function (data, type, row, meta) {
                    return getOverTime(row.overTime);
                }
            },
            {
                targets: -1,
                defaultContent: "<div class='btn-group'>" +
                "<button id='editRow' class='btn btn-primary btn-sm' type='button'><i class='fa fa-edit'></i></button>" +
                "<button id='delRow' class='btn btn-primary btn-sm' type='button'><i class='fa fa-trash-o'></i></button>" +
                "</div>"
            }
        ],
        language: {
            "sProcessing": "处理中...",
            //"sLengthMenu":   "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },

    });
    // 查询按钮
    $("#btn-query").on("click", function () {
        tables.fnDraw();
    });

    // 添加
    $("#btn-add").on("click", function () {
        $("input[name=userName]").val("");
        $("input[name=loginName]").val("");
        $("input[name=status]").val("");
        $("input[name=password]").val("");
        $("#editModal").modal("show");
    });

    // 批量删除
    $("#btn-delAll").on("click", function () {
        alert("批量是删除");
    });

    // 导出
    $("#btn-export").on("click", function () {
        alert("导出");
    });

    // 刷新
    $("#btn-re").on("click", function () {
        tables.fnDraw(false);
    });

    // checkbox全选
    $("#checkAll").on("click", function () {
        if ($(this).prop("checked") === true) {
            $("input[name='checkList']").prop("checked", $(this).prop("checked"));
            // $("#dataTable tbody tr").addClass('selected');
            $(this).hasClass('selected')
        } else {
            $("input[name='checkList']").prop("checked", false);
            $("#dataTable tbody tr").removeClass('selected');
        }
    });

    // 修改
    $("#table-user tbody").on("click", "#editRow", function () {
        var data = tables.api().row($(this).parents("tr")).data();
        $("input[name=userName]").val(data.userName);
        $("input[name=loginName]").val(data.loginName);
        $("input[name=status]").val(data.status);
        $("input[name=password]").val(data.password);

        $("#editModal").modal("show");
    });

    $("#btn-submit").on("click", function () {
        alert("success");
        /*$.ajax({
          cache: false,
          type: "POST",
          url: url,
          data:$("#editForm").serialize(),
          async: false,
          error: function(request) {
        	  alert("Server Connection Error...");
          },
          success: function(data) {
            if(data.status == 1){
                $("#editModal").modal("hide");
                alert("success");
                tables.fnDraw();
            }else{
                alert("fail");
            }
          }
      });*/
    });

    // 删除
    $("#table-user tbody").on("click", "#delRow", function () {
        var data = tables.api().row($(this).parents("tr")).data();
        if (confirm("是否确认删除这条信息?")) {
            $.ajax({
                url: "user/deleteUser/" + data.id,
                type: 'delete',
                contentType: 'application/json;charset=utf-8',
                dataType: "json",
                cache: "false",
                success: function (data) {
                    if (data) {
                        alert("删除成功");
                        tables.api().row().remove().draw(true);
                    } else {
                        alert("删除失败");
                    }
                },
                error: function (err) {
                    alert(err);
                }
            });
        }
    });
}

