var url = window.location.href;
var urlCommand = url.split("=")[1];
console.log("urlCommand: ", urlCommand);
var newUrl = "MyController?command=CHANGE_LOCAL&url=" + urlCommand;
console.log("new url= ", newUrl)
$("#change-locale-url").attr("href", newUrl);
var aaa = document.getElementById("change-locale-url");
console.log("aaa = ", aaa);