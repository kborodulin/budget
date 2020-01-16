var authDiv = document.querySelector("#authForm");
var regDiv = document.querySelector("#registrationForm");


document.querySelector("#regBtn").addEventListener("click", e=>{
    e.preventDefault();
    authDiv.style.display = "none";
    regDiv.style.display = "";

});

document.querySelector("#authBtn").addEventListener("click", e=>{
    e.preventDefault();
    authDiv.style.display = "";
    regDiv.style.display = "none";
    
});