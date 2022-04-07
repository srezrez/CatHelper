// var script = document.createElement('script');
// script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
// script.type = 'text/javascript';
// document.getElementsByTagName('head')[0].appendChild(script);

var reqSelect = document.getElementById("req-select");
console.log("HELLO")
$(document).on('change', '#req-select', function(){
    var data = {status: reqSelect.value}
    console.log("Option is= ", data);
    $.ajax({
        type: "GET",
        url: "MyController?command=FILTER_REQUESTS&status=" + reqSelect.value,
        success: function(response) {
            $('#req-table').append(response);
        }
    });
})

// reqSelect.onchange(function(){
//     var data = {status: reqSelect.value}
//     console.log("Option is= ", data);
//     $.ajax({
//         type: "GET",
//         url: "MyController?command=FILTER_REQUESTS&status=" + reqSelect.value,
//         success: function(response) {
//             $('#req-table').append(response);
//             console.log("table", response);
//         }
//     });
// })