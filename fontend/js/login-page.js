function toggleForm(form){
    if(form == "login"){
        document.getElementById("login").style.display = "block";
        document.getElementById("register").style.display = "none";
    } else {
        document.getElementById("login").style.display = "none";
        document.getElementById("register").style.display = "block";
    }
}
