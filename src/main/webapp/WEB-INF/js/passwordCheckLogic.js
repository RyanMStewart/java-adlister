
var msg = document.getElementById('message');
var pwd = document.getElementById('password');

var number = document.getElementById('number');
var length = document.getElementById('length');
var symbol = document.getElementById('symbol');

pwd.addEventListener("blur", function(e) {
    document.getElementById('message').setAttribute("style", "display:none");
});

pwd.addEventListener("focus", function(e) {
    // msg.style.display = "none";
    document.getElementById('message').setAttribute("style", "display:block");
});

pwd.addEventListener('keyup', function() {

    // Validate numbers
    var numbers = /[0-9]/g;
    if(pwd.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }

    // Validate symbols
    var symbols = /[$-/:-?{-~!"^_`\[\]]/g;
    if(pwd.value.match(symbols)) {
        symbol.classList.remove("invalid");
        symbol.classList.add("valid");
    } else {
        symbol.classList.remove("valid");
        symbol.classList.add("invalid");
    }
    // Validate length
    if(pwd.value.length >= 8) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.add("invalid");
        length.classList.remove("valid");
    }
});
