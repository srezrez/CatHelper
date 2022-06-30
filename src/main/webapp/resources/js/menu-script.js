var $menu = $('.menu-toggle');
var loadTheme = function() {
    var selector = "#" + localStorage.getItem('active-menu');
    $(selector).addClass('active');
};
$('.menu-toggle').click(function() {
    localStorage.setItem('active-menu', $(this).attr('id'));
});
loadTheme();
























// var menuItems = [document.getElementById("all-cats"), document.getElementById("added-cats"), document.getElementById("requests")];
//
// menuItems[0].click(function(e) {
//     e.className = 'active';
//     localStorage.setItem('active-container', e);
// })
//
// localStorage.getItem('active-container').className = 'active';

// menuItems[0].addEventListener("click", function(){
//     console.log("first item clicked");
// });
//
// menuItems.forEach(function(elem) {
//     elem.addEventListener("click", function(){
//         elem.className = 'active';
//         menuItems.forEach(function(e) {
//             if(e != elem) e.className = '';
//         });
//     });
// })