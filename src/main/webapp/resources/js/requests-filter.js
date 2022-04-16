// var script = document.createElement('script');
// script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
// script.type = 'text/javascript';
// document.getElementsByTagName('head')[0].appendChild(script);

var reqSelect = document.getElementById("req-select");
var table = document.getElementById("req-table");
$(document).on('change', '#req-select', function(){
    var data = {status: reqSelect.value}
    console.log("Option is= ", data);
    $.ajax({
        type: "GET",
        url: "MyController?command=FILTER_REQUESTS&status=" + reqSelect.value,
        cache: false,
        success: function(response) {
            $("#req-table tr").remove();
            var obj = $($.parseHTML(response)).find("#req-table");
            $("#req-table").append(obj)
        }
    });
})