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
