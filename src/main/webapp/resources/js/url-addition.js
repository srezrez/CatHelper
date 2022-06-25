var url = window.location.href;
console.log("url: ", url);
var urlSplit = url.split("=");
var urlCommand = urlSplit[1].split("&")[0];
var urlParam = url.split("&");
var newUrl = "MyController?command=CHANGE_LOCAL&url=" + urlCommand;
urlParam.slice(1).forEach(param => newUrl += "&" + param);
console.log("new url= ", newUrl)
$("#change-locale-url").attr("href", newUrl);
var aaa = document.getElementById("change-locale-url");
console.log("aaa = ", aaa);