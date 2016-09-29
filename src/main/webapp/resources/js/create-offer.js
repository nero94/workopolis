
$('#datetimepicker1').datetimepicker({
   defaultDate: new Date(),
   daysOfWeekDisabled: [0,6],
    minDate: moment()
});




var element1 = document.getElementById('comment-lines');

makeCommentLines(element1);

function makeCommentLines(el){

el.setAttribute('rows', 4); // limit height to 2 rows
// el.setAttribute('wrap', 'off'); // ensure no softwrap is not required anymore if we limit the length
el.addEventListener('keydown', limit); // add listener everytime a key is pressed

function limit(e){
if(e.keyCode == 13 && this.value.indexOf('\n')>-1){
// 13 is the ENTER key and \n is the value it make in the textarea
// so if we already have a line break and it's the ENTER key, we prevent it

}

// async to let the dom update before changin the value
setTimeout(limitRow.bind(this), 0);
}

function limitRow(){
var maxLength = 200;
var rows = this.value.split('\n');
rows.forEach(cutOverflow)
this.value = rows.join('\n');

function cutOverflow(row, index, rows) {
rows[index] = row.substring(0, maxLength);
// this if is only if you want to automatically jump to the next line
if (index === 0 && row.length > maxLength)
  rows[1] = row.substring(maxLength) + (rows[1] || '');
}
}
}
var xmlHttp;

function selectVacancy() {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }
    if (document.getElementById('vacancy-select').value =="") return;

    var url = "/select-candidate";

    url += "?vacancy=" + document.getElementById('vacancy-select').value;

    xmlHttp.onreadystatechange = stateChange;

    xmlHttp.open("GET", url, true);
    console.log(url);
    xmlHttp.send();

}

function stateChange() {

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {

        document.getElementById("candidate").innerHTML = xmlHttp.responseText

    }
}

var statuses = {
        CREATED_BY_EMPLOYER : "Created by Employer",
        ACCEPTED_BY_CANDIDATE : "Accepted by Candidate",
        REJECTED_BY_CANDIDATE : "Rejected by Candidate",
        CANCELED_BY_EMPLOYER : "Canceled by Employer",
        CANDIDATE_ACCEPTED : "Candidate is Accepted"
    };

    $(".offer-state-option").each(function(){$(this).attr("label", statuses[$(this).val()])});
