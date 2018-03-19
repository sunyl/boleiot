function getMenuHtml(menus) {
    var result = '';
    for (var i = 0; i < menus.length; i++) {
        result += "<li class='nav-item '>" +
            "<a href='javascript:;' class='nav-link nav-toggle'>" +
            "<i class=" + menus[i].icon + "></i>" +
            "<span class='title'>" + menus[i].title + "</span>" +
            "<span class='arrow'></span>" +
            "</a>";
        result += getChildMeus(menus[i].children);
        result += "</li>";
    }
    return result;
}

function getChildMeus(childMenus) {
    var data = "<ul class='sub-menu'>";
    for (var i = 0; i < childMenus.length; i++) {
        data += "<li class='nav-item '>" +
            "<a href='###' class='nav-link' onclick='showAtRight(" + "\"" + childMenus[i].url + "\"" + ")'>" +
            "<i class=" + childMenus[i].icon + "></i>" +
            "<span class='title'> " + childMenus[i].title + "</span>" +
            "</a>";
        data += getChildMeus(childMenus[i].children);
        data += "</li>";
    }
    data += "</ul>";
    return data;
}