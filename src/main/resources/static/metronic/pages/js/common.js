function showAtRight(url) {
    $.ajax({
        url: url,
        type: "GET",
        dataType: 'text',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            $("#content").html(data);
        }
    });
}

function userLogin() {
    if ($('#username').val() == "") {
        $("#error").text("用户名不能为空！");
        return;
    }
    if ($('#password').val() == "") {
        $("#error").text("密码不能为空！");
        return;
    }
    var str = {
        "username": $('#username').val(),
        "password": $('#password').val()
    };
    $.ajax({
        type: "POST",
        url: "/boleiot/user/userLogin",
        data: JSON.stringify(str),
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data.status == 200) {
                window.location.href = 'index';
            } else {
                $("#error").text(data.msg);
            }
        }
    });
}