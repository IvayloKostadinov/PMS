
$(".toggle-wrap").on("click", function() {
  $(this).toggleClass("active");
  $(".nav-side-menu").toggle("left");
});
(function() {})();

$("#selectedTeam").on("click", function(event) {
  var value = $("#selectedTeam").html();
  var input = $("#teamInput");
  input.val(value);
});

$("#selectedManager").on("click", function() {
  var value = $("#selectedManager");
  var strUser = value.options[value.selectedIndex].value;
  var input = $("#managerInput");
  input.val(strUser);
});

$("#submitPhase").on("click", function() {
  var budget = $("#phaseBudget").val();
  var name = $("#phaseName").val();
  var startDate = $("#phaseStartDate").val();
  var endDate = $("#phaseEndDate").val();

  var row =
    ' <tr th:each="phase : ${phases}"><td>' +
    name +
    "</td>\n" +
    "                        <td >" +
    startDate +
    "</td>\n" +
    "                        <td >" +
    endDate +
    "</td>\n" +
    "                        <td >" +
    budget +
    "</td>\n" +
    " <td><a id='remove' onclick='deletePhase()' href=\"#\">Delete</a></td></tr>";
  $("table tbody").append(row);
});

function deletePhase() {
  $("#remove")
    .parent()
    .parent()
    .remove();
}

//Dropdown
var x, i, j, selElmnt, a, b, c;
/* Look for any elements with the class "custom-select": */
x = document.getElementsByClassName("custom-select");
for (i = 0; i < x.length; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  /* For each element, create a new DIV that will act as the selected item: */
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /* For each element, create a new DIV that will contain the option list: */
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < selElmnt.length; j++) {
    /* For each option in the original select element,
        create a new DIV that will act as an option item: */
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
      /* When an item is clicked, update the original select box,
            and the selected item: */
      var y, i, k, s, h;
      s = this.parentNode.parentNode.getElementsByTagName("select")[0];
      h = this.parentNode.previousSibling;
      for (i = 0; i < s.length; i++) {
        if (s.options[i].innerHTML == this.innerHTML) {
          s.selectedIndex = i;
          h.innerHTML = this.innerHTML;
          y = this.parentNode.getElementsByClassName("same-as-selected");
          for (k = 0; k < y.length; k++) {
            y[k].removeAttribute("class");
          }
          this.setAttribute("class", "same-as-selected");
          break;
        }
      }
      h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
    /* When the select box is clicked, close any other select boxes,
        and open/close the current select box: */
    e.stopPropagation();
    closeAllSelect(this);
    this.nextSibling.classList.toggle("select-hide");
    this.classList.toggle("select-arrow-active");
  });
}

function closeAllSelect(elmnt) {
  /* A function that will close all select boxes in the document,
    except the current select box: */
  var x,
    y,
    i,
    arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i);
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}

/* If the user clicks anywhere outside the select box,
then close all select boxes: */
document.addEventListener("click", closeAllSelect);

/*
News
*/
$(document).ready(function() {
  /*Menu-toggle*/
  $("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("active");
  });
  $('body').scrollspy({target: '#spy', offset: 80});

  /*Smooth link animation*/
  $('a[href*=#]:not([href=#])').click(function () {
      if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') || location.hostname == this.hostname) {

          var target = $(this.hash);
          target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
          if (target.length) {
              $('html,body').animate({
                  scrollTop: target.offset().top
              }, 1000);
              return false;
          }
      }
  });

});

  $("body").scrollspy({ target: "#spy", offset: 80 });
function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}


$(function () {
  $('#myEditor').froalaEditor(
      {toolbarInline: false})
});
