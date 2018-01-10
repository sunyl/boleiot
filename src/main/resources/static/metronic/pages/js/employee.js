function init_select() {
    $.ajax({
        type: "GET",
        url: "/sunny/dept/getDeptList",
        dataType: 'json',
        success: function (data) {
            $.each(data, function (i) {
                $('#dept-combobox').append("<option value=" + data[i].id + ">" + data[i].name + "</option>");

            });

            // $('#dept-combobox').selectpicker('refresh');
        },
        error: function (data) {
            alert("初始化部门失败！");
        }
    });

    $.ajax({
        type: "GET",
        url: "/sunny/job/getJobList",
        dataType: 'json',
        success: function (data) {
            $.each(data, function (i) {
                $('#job-combobox').append("<option value=" + data[i].id + ">" + data[i].name + "</option>");

            });

            //$('#job-combobox').selectpicker('refresh');
        },
        error: function (data) {
            alert("初始化部门失败!");
        }
    });
}

function add_employee() {
    if ($.trim($('#name').val()) == "") {
        alert("姓名不能为空");
        return;
    }
    if ($.trim($('#cardId').val()) == "") {
        alert("身份证号码不能为空");
        return;
    }
    var str = {
        "name": $('#name').val(),
        "cardId": $('#cardId').val(),
        "sex": $('#sex-combobox').val(),
        "job": {"id": $('#job-combobox').val()},
        "education": $('#education').val(),
        "email": $('#email').val(),
        "phone": $('#phone').val(),
        "tel": $('#tel').val(),
        "party": $('#party').val(),
        "qqNum": $('#qqNum').val(),
        "address": $('#address').val(),
        "postCode": $('#postCode').val(),
        "birthday": $('#birthday').val(),
        "race": $('#race').val(),
        "speciality": $('#speciality').val(),
        "hobby": $('#hobby').val(),
        "remark": $('#remark').val(),
        "dept": {"id": $('#dept-combobox').val()}
    };
    $.ajax({
        type: "POST",
        url: "/sunny/employee/employeeAddAction",
        data: JSON.stringify(str),
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data.flag) {
                alert("添加成功！");
            } else {
                alert("添加失败！" + data.msg);
            }
        },
        error: function (data) {
            alert("请求失败");
        }
    });
}

function clear() {
}

function table_employee() {
    var tables = $("#table-employee").dataTable({
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
            param.dept_id = $('#dept-combobox').val();
            param.job_id = $('#job-combobox').val();
            param.search = $('#keyword').val();
            param.orderby = data.orderBys;
            $.ajax({
                type: "post",
                url: "employee/getEmployeeList",
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
            {"data": 'id'},
            {"data": 'name'},
            {"data": 'cardId'},
            {
                "data": 'sex',
                "render": function (data) {
                    return data == 0 ? '男' : '女';
                }
            },
            {"data": 'phone'},
            {"data": 'email'},
            {"data": 'address'},
            {"data": 'education'},
            {"data": 'dept.name'},
            {"data": 'job.name'},
            {"data": 'createDate'},
            {"data": null, "width": "100px"}
        ],
        // 操作按钮
        columnDefs: [
            {
                targets: 0,
                defaultContent: "<input type='checkbox' name='checkList'>"
            },
            {
                targets: -1,
                defaultContent: "<div class='btn-group'>"+
                "<button id='delRow' class='btn btn-primary btn-sm' type='button'><i class='fa fa-trash-o'></i></button>"+
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

    // 批量删除
    $("#btn-delAll").on("click", function () {
        alert("批量是删除");
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

    // 删除
    $("#table-employee tbody").on("click", "#delRow", function () {
        var data = tables.api().row($(this).parents("tr")).data();
        if (confirm("是否确认删除这条信息?")) {
            $.ajax({
                url: "employee/deleteEmployee/" + data.id,
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
