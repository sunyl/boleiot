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

function init_sidebar(index, child) {
    switch (index) {
        case 0:
            $('#user-manager').addClass("start active open").siblings().removeClass('start active open');
            if (child == 0) {
                $('#user-query').addClass("active").siblings().removeClass('active');
            } else if (child == 1) {
                $('#user-add').addClass("active").siblings().removeClass('active');
            }
            break;
        case 1:
            $('#dept-manager').addClass("start active open");
            if (child == 0) {
                $('#dept-query').addClass("active");
            } else if (child == 1) {
                $('#dept-add').addClass("active");
            }
            break;
        case 2:
            $('#job-manager').addClass("start active open");
            if (child == 0) {
                $('#job-query').addClass("active");
            } else if (child == 1) {
                $('#job-add').addClass("active");
            }
            break;
        case 3:
            $('#employee-manager').addClass("start active open");
            if (child == 0) {
                $('#employee-query').addClass("active");
            } else if (child == 1) {
                $('#employee-add').addClass("active");
            }
            break;
        case 4:
            $('#notice-manager').addClass("start active open");
            if (child == 0) {
                $('#notice-query').addClass("active");
            } else if (child == 1) {
                $('#notice-add').addClass("active");
            }
            break;
        case 5:
            $('#file-manager').addClass("start active open");
            if (child == 0) {
                $('#file-download').addClass("active");
            } else if (child == 1) {
                $('#file-upload').addClass("active");
            }
            break;
        default:
    }

}