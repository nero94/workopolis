// For making copies of skill inputs
$(document).on('ready',function() {
$(document).on('click','.add-tech-skill',function(){

  $(".techSkillName").attr("readonly", true);
  $(".techSkillSelect").attr("disabled", true);

  $(".delete-tech-skill").addClass("shown-visibility");
  $(".add-tech-skill").addClass("hidden-visibility");

  $("#techSkillName").attr("id", "submitedTechSkillName");
  $("#techSkillCategory").attr("id", "submitedTechSkillCategory");
  $("#techSkillLevel").attr("id", "submitedTechSkillLevel");
  $("#techSkillImportance").attr("id", "submitedTechSkillImportance");
          $('#copy-tech-skill').append('<tbody class="generated">' +
                '<td><input id="techSkillName" class="col-lg-8 techSkillName"/></td>' +
                '<td><select id="techSkillCategory" class="form-control techSkillSelect" >' +
                      '<option value="TECHNOLOGIES" label="Technologies"/>' +
                      '<option value="LANGUAGES" label="Languages"/>' +
                      '<option value="FRAMEWORKS" label="Frameworks"/>' +
                      '<option value="DATABASES" label="Databases"/>' +
                      '<option value="TOOLS" label="Tools"/>' +
                      '<option value="PROTOCOLS" label="Protocols"/>' +
                      '<option value="LIBRARIES" label="Libraries"/>' +
                      '<option value="TECHNIQUES" label="Techniques"/>' +
                    '</select></td>' +
                '<td><select id="techSkillLevel" class="form-control techSkillSelect" >' +
                      '<option value="BASIC" label="Basic"/>' +
                      '<option value="AVERAGE" label="Average"/>' +
                      '<option value="STRONG" label="Strong"/>' +
                      '<option value="EXPERT" label="Expert"/>' +
                    '</select></td>' +
                    '<td><select id="techSkillImportance" class="form-control techSkillSelect" >' +
                              '<option value="REQUIRED" label="Required"/>' +
                              '<option value="ADDITIONAL" label="Additional"/>' +
                            '</select></td>' +
                    '<td><input id="add-tech-skill" class="add-tech-skill" type="button" value="Save Skill" onclick="SendTechSkill()"></td>'+
                    '<td><input id="delete-tech-skill" class="delete-tech-skill" type="button" value="Delete Skill" onclick="DeleteTechSkill(this)"></td>'+
              '</tbody>');

});
$(document).on('click','.delete-tech-skill',function(){
  $(this).parent().parent().parent().remove();
});


$(document).on('click','.add-com-skill',function(){


  $(".comSkillSelect").attr("disabled", true);

  $(".delete-com-skill").addClass("shown-visibility");
  $(".add-com-skill").addClass("hidden-visibility");

  $("#comSkillName").attr("id", "submitedComSkillName");
  $("#comSkillLevel").attr("id", "submitedComSkillLevel");
  $("#comSkillImportance").attr("id", "submitedComSkillImportance");

          $('#copy-com-skill').append(  '<tbody>' +
              '<td><select id="comSkillName" class="form-control comSkillSelect">' +
                    '<option value="ENGLISH" label="English"/>' +
                    '<option value="GERMAN" label="German"/>' +
                    '<option value="FRENCH" label="French"/>' +
                    '<option value="SPANISH" label="Spanish"/>' +
                    '<option value="RUSSIAN" label="Russian"/>' +
                    '<option value="UKRAINIAN" label="Ukrainian"/>' +
                    '<option value="POLISH" label="Polish"/>' +
                    '<option value="CHINEESE" label="Chineese"/>' +
                    '<option value="JAPANEESE" label="Japaneese"/>' +
                  '</select></td>' +
              '<td><select id="comSkillLevel" class="form-control comSkillSelect" >' +
                    '<option value="BEGINER" label="Beginer"/>' +
                    '<option value="ELEMENTARY" label="Elementary"/>' +
                    '<option value="INTERMEDIATE" label="Intermediate"/>' +
                    '<option value="UPPER_INTERMEDIATE" label="Upper Intermediate"/>' +
                    '<option value="ADVANCED" label="Advanced"/>' +
                    '<option value="PROFICIENT" label="Proficient"/>' +
                  '</select></td>' +
                  '<td></td>' +
              '<td><select id="comSkillImportance" class="form-control comSkillSelect">' +
                        '<option value="REQUIRED" label="Required"/>' +
                        '<option value="ADDITIONAL" label="Additional"/>' +
                      '</select></td>' +
              '<td><input id="add-com-skill" class="add-com-skill" type="button" value="Save Skill" onclick="SendComSkill()"></td>' +
              '<td><input id="delete-com-skill" class="delete-com-skill" type="button" value="Delete Skill" onclick="DeleteComSkill(this)"></td>' +
            '</tbody>');

});
$(document).on('click','.delete-com-skill',function(){
  $(this).parent().parent().parent().remove();
});


$(document).on('click','.add-pers-skill',function(){


  $(".persSkillSelect").attr("disabled", true);

  $(".delete-pers-skill").addClass("shown-visibility");
  $(".add-pers-skill").addClass("hidden-visibility");

  $("#persSkillName").attr("id", "submitedPersSkillName");
  $("#persSkillLevel").attr("id", "submitedPersSkillLevel");

          $('#copy-pers-skill').append( '<tbody>' +
            '<td><select id="persSkillName" class="form-control persSkillSelect">' +
                  '<option value="OPENESS_TO_EXPERIENCE" label="Openess to experiance"/>' +
                  '<option value="CONSCIENTIOUSNESS" label="Conscientiosness"/>' +
                  '<option value="EXTRAVERSION" label="Extraversion"/>' +
                  '<option value="AGREEABLENESS" label="Agreeableness"/>' +
                  '<option value="TEUROTICISM" label="Teuroticism"/>' +
                  '<option value="TEAMWORK" label="Teamwork"/>' +
                  '<option value="INITIATIVE" label="Initiative"/>' +
                  '<option value="FLEXIBILITY" label="Flexibility"/>' +
                  '<option value="TIME_MANAGEMENT" label="Time management"/>' +
                  '<option value="DECISION_MAKING" label="Decision making"/>' +
                  '<option value="CREATIVITY" label="Creativity"/>' +
                  '<option value="TRUSTWORTHINESS" label="Trustworthiness"/>' +
                  '<option value="EMOTIONAL_STABILITY" label="Emotional stability"/>' +
                '</select></td>' +
                '<td></td>' +
            '<td><select id="persSkillLevel" class="form-control persSkillSelect" >' +
                  '<option value="MINIMUM" label="Minimum"/>' +
                  '<option value="AVERAGE" label="Average"/>' +
                  '<option value="MAXIMUM" label="Maximum"/>' +
                '</select></td>' +
            '<td><input id="add-pers-skill" class="add-pers-skill" type="button" value="Save Skill" onclick="SendPersSkill()"></td>' +
            '<td><input id="delete-pers-skill" class="delete-pers-skill" type="button" value="Delete Skill" onclick="DeletePersSkill(this)"></td>' +
          '</tbody>');

});
$(document).on('click','.delete-pers-skill',function(){
  $(this).parent().parent().parent().remove();
});
});




var element1 = document.getElementById('tworows');
var element2 = document.getElementById('tworows1');
var element3 = document.getElementById('tworows2');
var element4 = document.getElementById('tworows3');
var element5 = document.getElementById('tworows4');

    make2Lines(element1);
    makeMLines(element2);
    makeOfferLines(element3);
    makeExtraLines(element4);
    makeMoreLines(element5);

function make2Lines(el){

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
function makeMLines(el){

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
function makeOfferLines(el){


// el.setAttribute('wrap', 'off'); // ensure no softwrap is not required anymore if we limit the length


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
function makeExtraLines(el){

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
function makeMoreLines(el){

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

var testName = new RegExp("[0-9A-Za-z]{3}");

function SendTechSkill(){
	var xhttp = new XMLHttpRequest();
	var url = "/add_technical_skill?name=" + $("#techSkillName").val() + "&category=" + $("#techSkillCategory").val() + "&level=" + $("#techSkillLevel").val() + "&importance=" + $("#techSkillImportance").val();
  console.log(url);
	xhttp.open('GET', url, true);
	xhttp.send();
	}

  function DeleteTechSkill(x){
	var scope = $(x).parent().parents()[0];
  	var xhttp = new XMLHttpRequest();
  	var url = "/delete_technical_skill?name=" + $(scope).find("#submitedTechSkillName").val() + "&category=" + $(scope).find("#submitedTechSkillCategory").val() + "&level=" + $(scope).find("#submitedTechSkillLevel").val() + "&importance=" + $(scope).find("#submitedTechSkillImportance").val();
    console.log(url);
  	xhttp.open('GET', url, true);
  	xhttp.send();
  	}


    function SendComSkill(){
    	var xhttp = new XMLHttpRequest();
    	var url = "/add_communication_skill?name=" + $("#comSkillName").val() + "&level=" + $("#comSkillLevel").val() + "&importance=" + $("#comSkillImportance").val();
      console.log(url);
    	xhttp.open('GET', url, true);
    	xhttp.send();
    	}

      function DeleteComSkill(x){
    	var scope = $(x).parent().parents()[0];
      	var xhttp = new XMLHttpRequest();
      	var url = "/delete_communication_skill?name=" + $(scope).find("#submitedComSkillName").val() + "&level=" + $(scope).find("#submitedComSkillLevel").val() + "&importance=" + $(scope).find("#submitedComSkillImportance").val();
        console.log(url);
      	xhttp.open('GET', url, true);
      	xhttp.send();
      	}


        function SendPersSkill(){
          var xhttp = new XMLHttpRequest();
          var url = "/add_personal_skill?name=" + $("#persSkillName").val() + "&level=" + $("#persSkillLevel").val();
          console.log(url);
          xhttp.open('GET', url, true);
          xhttp.send();
          }

          function DeletePersSkill(x){
          	var scope = $(x).parent().parents()[0];
            var xhttp = new XMLHttpRequest();
            var url = "/delete_personal_skill?name=" + $(scope).find("#submitedPersSkillName").val() + "&level=" + $(scope).find("#submitedPersSkillLevel").val();
            console.log(url);
            xhttp.open('GET', url, true);
            xhttp.send();
            }

var skillOptions = {
TECHNOLOGIES: "Technologies",
LANGUAGES: "Languages",
FRAMEWORKS: "Frameworks",
DATABASES: "Databases",
TOOLS: "Tools",
PROTOCOLS: "Protocols",
LIBRARIES: "Libraries",
TECHNIQUES: "Techniques",
OPENESS_TO_EXPERIENCE:"Openess to experiance",
CONSCIENTIOUSNESS:"Conscientiosness",
EXTRAVERSION: "Extraversion",
AGREEABLENESS:"Agreeableness",
TEUROTICISM:"Teuroticism",
TEAMWORK:"Tamwork",
INITIATIVE:"Initiative",
FLEXIBILITY:"Flexibility",
TIME_MANAGEMENT:"Time management",
DECISION_MAKING:"Decision making",
CREATIVITY:"Creativity",
TRUSTWORTHINESS:"Trustworthiness",
EMOTIONAL_STABILITY:"Emotional stability"
}

$(".techSkillOption").each(function(){ $(this).attr("label", skillOptions[$(this).val()]) });
