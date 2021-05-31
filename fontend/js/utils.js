// $(function () {
//     var includes = $('[data-include]')
//     $.each(includes, function () {
//         var file = './' + $(this).data('include') + '.html'
//         $(this).load(file)
//     })
// })

function toggleForm(form) {
    document.getElementById('btn-' + form).className = 'active';
    document.getElementById(form).style.display = 'block';
    
    if (form == 'schedual') {
        document.getElementById('btn-rooms').className = '';
        document.getElementById('rooms').style.display = 'none';
    } else {
        document.getElementById('btn-schedual').className = '';
        document.getElementById('schedual').style.display = 'none';
    }
}

function closeAllTabs() {
    document.getElementById("booking").style.display = "none";
    document.getElementById("manage-user").style.display = "none";
    // document.getElementById("register").style.display = "none";
}

function showForm(option) {
    var x = document.getElementById(option);
    var btn = document.getElementById("btn-" + option);
    var btnToggle = document.getElementById("sidebar").querySelectorAll("a");
    closeAllTabs();
    // turn-off all button sidebar
    for (i = 0; i < btnToggle.length && option != "register"; i++) {
        btnToggle[i].className = "";
    }

    if (x.style.display === "none") {
        x.style.display = "block";
        btn.className = "active";
    } else {
        x.style.display = "none";
        btn.className = "";
    }
}