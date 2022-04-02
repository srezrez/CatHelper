var menuItems = [document.getElementById("all-cats"), document.getElementById("added-cats"), document.getElementById("requests")];

menuItems[0].addEventListener("click", function(){
    console.log("first item clicked");
});

menuItems.forEach(function(elem) {
    elem.addEventListener("click", function(){
        elem.className = 'active';
        menuItems.forEach(function(e) {
            if(e != elem) e.className = '';
        });
    });
})