document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll(".share-btn");

    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            var content = encodeURIComponent(button.getAttribute("data-text"));
            var url = button.getAttribute("data-url");

            if(url) {
                window.location.href = url + "?sharedContent=" + content;
            } else {
                window.location.href = "https://www.google.com";
            }
        });
    });
});