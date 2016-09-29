
$('#datetimepicker1').datetimepicker({
   defaultDate: new Date(),
   daysOfWeekDisabled: [0,6],
    minDate: moment()
});
$(document).on('ready',function() {
	$(document).on('click','.check-cand-email',function(){
		$(".submit-result").addClass("shown-visibility");
	});

function CheckCandidate(){
	var xhttp = new XMLHttpRequest();
	var url = "/check-candidate?email=" + $("#candEmail").val();
  console.log(url);
	xhttp.open('GET', url, true);
	xhttp.send();
	}


});
	var element1 = document.getElementById('comment-lines');

	makeCommentLines(element1);

	function makeCommentLines(el){

    try{
	el.setAttribute('rows', 4); // limit height to 2 rows

	// el.setAttribute('wrap', 'off'); // ensure no softwrap is not required anymore if we limit the length
	el.addEventListener('keydown', limit); // add listener everytime a key is pressed
}
catch(e){}
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
