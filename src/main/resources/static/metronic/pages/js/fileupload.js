function doUpload() {
    if (document.all.file.value == "") {
        alert("你没有选择文件");
        return;
    }

    $("#btnUpload").attr('disabled', true);
    $("#progressBar").width("0%");
    $("#progressBar").parent().show();
    $("#progressBar").parent().addClass("active");
    document.getElementById('progressBar').innerHTML = "0%";

    var fileObj = $("#file").get(0).files[0];
    var form = new FormData();
    form.append("file", fileObj);
    form.append("title", $('#title').val());
    form.append("remark", $('#remark').val());
    var xhr = new XMLHttpRequest();
    xhr.open("post", '/sunny/img/uploadImg', true);
    xhr.onload = function () {
        $("#btnUpload").attr('disabled', false);
    };
    xhr.upload.addEventListener("progress", progressFunction, false);
    xhr.send(form);
}

function progressFunction(evt) {
    var progressBar = $("#progressBar");
    if (evt.lengthComputable) {
        var completePercent = Math.round(evt.loaded / evt.total * 100) + "%";
        progressBar.width(completePercent);
        document.getElementById('progressBar').innerHTML = completePercent;
    }
}



function table_file() {
    
}
