function openProject(evt, projectName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
        tablinks[i].style.display = "";
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    document.getElementById(projectName).style.display = "block";
    evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click();

function deleteConfirm(projectId) {
    if (confirm('Are you sure you want to delete the project?')) {
        location.href='/projects/' + projectId + '/delete';
    } else {
        return false;
    }
}

function searchProjects() {
    var input, filter, div, button, i;
    input = document.getElementById("mySearch");
    filter = input.value.toUpperCase();
    div = document.getElementById("myButtons");
    button = div.getElementsByTagName("button");
    for (i = 0; i < button.length; i++) {
        if (button[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            button[i].style.display = "";
        } else {
            button[i].style.display = "none";
        }
    }
}