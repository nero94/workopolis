// For making copies of skill inputs
$(document).on('ready',function() {

jQuery(function() {
jQuery('.ss_button').on('click',function() {
if(jQuery(this).next('.ss_content').is(':visible')) {
jQuery('.ss_content').slideUp('fast');
} else {
jQuery('.ss_content').slideUp('fast');
jQuery(this).next('.ss_content').slideDown('fast');
}
});
});

$( function() {
    $( "#datepicker").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true
      });
    if ($( "#datepicker" ).val()) $( "#datepicker" ).val(FormatDate($( "#datepicker" ).val()));

  } );

$( function() {
    $( "#datepicker1").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true
      });
  } );

$( function() {
    $( "#datepicker2").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true
      });
  } );
$( function() {
    $( "#datepicker3").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true
      });
  } );

$( function() {
    $( "#datepicker4").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true
      });
  } );


   function FormatDate(date) {
               var formatted_birth = $.datepicker.formatDate('yy-mm-dd', new Date(date));
              return formatted_birth;
            };

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
                  '<option value="TEAMWORK" label="Tamwork"/>' +
                  '<option value="INITIATIVE" label="Initiative"/>' +
                  '<option value="FLEXIBILITY" label="Flexibility"/>' +
                  '<option value="TIME_MANAGEMENT" label="Time management"/>' +
                  '<option value="DECISION_MAKING" label="Decision making"/>' +
                  '<option value="CREATIVITY" label="Creativity"/>' +
                  '<option value="TRUSTWORTHINESS" label="Trustworthiness"/>' +
                  '<option value="EMOTIONAL_STABILITY" label="Emotional stability"/>' +
                '</select></td>' +
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

$(document).on('click','.add-cand-education',function(){

  $(".universityName").attr("readonly", true);
  $(".startEdDate").attr("readonly", true);
  $(".finEdDate").attr("readonly", true);
  $(".edSelect").attr("disabled", true);

  $(".delete-cand-education").addClass("shown-visibility");
  $(".add-cand-education").addClass("hidden-visibility");


   $("#universityName").attr("id", "submitedUniversityName");
   $("#countryEdSelect").attr("id", "submitedEdCountryEdSelect");
   $("#stateEdSelect").attr("id", "submitedEdStateEdSelect");
   $("#cityEdSelect").attr("id", "submitedEdCityEdSelect");
   $("#edFieldName").attr("id", "submitedEdFieldName");
   $("#edDegreeName").attr("id", "submitedEdDegreeName");
   $("#educationSelectBody").attr("id", "submitedducationSelectBody");
   $("#datepicker1").attr("id", "submitedDatepicker1");
   $("#datepicker2").attr("id", "submitedDatepicker2");
   $("#first-delete").attr("id", "delete-this");
   $("#second-delete").attr("id", "and-delete-this");

          $('#copy-education-candidate').prepend('<div class="panel-body for-back-ed">'+
            '<div class="row col-lg-12" id="educationSelectBody">'+
                                    '<div class="col-lg-3">'+
                                       '<label for="universityName" class="col-lg-3 col-sm-2 control-label">Name</label>'+
                                       '<input id="universityName" class="col-lg-9 universityName" />'+
                                    '</div>'+
                                    '<div class="col-lg-3">'+
                                       '<label for="universityCountry" class="col-lg-3 col-sm-2 control-label">Country</label>'+
                                       '<div id="country" class="country col-lg-9">'+
                                          '<select class="form-control edSelect" id="countryEdSelect">'+
                                             '<option value=""></option>'+
                                          '</select>'+
                                          '<input type="hidden" id="countryEducationSelectName" value="">'+
                                          '<input type="hidden" id="countryEducationSelectId" value="">'+
                                       '</div>'+
                                    '</div>'+
                                    '<div class="col-lg-3">'+
                                       '<label for="universityState" class="col-lg-3 col-sm-2 control-label">State</label>'+
                                       '<div id="educationState" class="col-lg-9">'+
                                          '<select class="form-control edSelect" id="stateEdSelect" disabled>'+
                                             '<option value=""></option>'+
                                          '</select>'+
                                       '</div>'+
                                    '</div>'+
                                    '<div class="col-lg-3">'+
                                       '<label for="universityCity" class="col-lg-3 col-sm-2 control-label">City</label>'+
                                       '<div id="educationCity" class="col-lg-9">'+
                                          '<select class="form-control edSelect" id="cityEdSelect" disabled>'+
                                             '<option value=""></option>'+
                                          '</select>'+
                                       '</div>'+
                                    '</div>'+
                                 '</div>'+
                                 '<div class="row col-lg-12 second-ed-row">'+
                                    '<div class="col-lg-6">'+
                                       '<label for="universityName" class="col-lg-2 col-sm-2 control-label">Education Field</label>'+
                                       '<div class="col-lg-10">'+
                                          '<select id="edFieldName" class="form-control edSelect">'+
                                             '<option value="AUTOMATION_AND_MACHINE_CONSTRUCTING" label="Automation and Machine Constructing" />'+
                                             '<option value="ELECTRONICS_AND_TELECOMMUNICATIONS" label="Electronics and Telecommunications" />'+
                                             '<option value="SYSTEM_ANALYSIS" label="System Analysis" />'+
                                             '<option value="COMPUTER_SCIENCE" label="Computer Science" />'+
                                             '<option value="SOFTWARE_ENGINEERING" label="Software Engineering" />'+
                                             '<option value="SYSTEM_ARCHITECTURE" label="System Architecture" />'+
                                             '<option value="PROJECT_MANAGEMENT" label="Project Management" />'+
                                             '<option value="SYSTEM_PROGRAMMING" label="System Programming" />'+
                                             '<option value="METROLOGY_AND_MEASURING_EQUIPMENT" label="Metrology and Measuring Equipment" />'+
                                             '<option value="INFORMATION_TECHNOLOGY" label="Information Technology" />'+
                                             '<option value="INFORMATION_AND_COMMUNICATION" label="Information and Communication" />'+
                                             '<option value="SYSTEMS_SECURITY" label="Systems Security" />'+
                                             '<option value="INFORMATION_SECURITY_MANAGEMENT" label="Information Security Management" />'+
                                             '<option value="QUALITY_STANDARTIZATION_AND_CERTIFICATION" label="Quality Standartization and Certification" />'+
                                             '<option value="APPLIED_MATHEMATICS" label="Applied Mathematics" />'+
                                             '<option value="APPLIED_PHYSICS" label="Applied Physics" />'+
                                             '<option value="MATHEMATICAL_AND_COMPUTER_MODELING" label="Mathematical and Computer Modeling" />'+
                                          '</select>'+
                                       '</div>'+
                                    '</div>'+
                                    '<div class="col-lg-6">'+
                                       '<label for="universityName" class="col-lg-2 col-sm-2 control-label">Education Degree</label>'+
                                       '<div class="col-lg-10">'+
                                          '<select id="edDegreeName" class="form-control edSelect">'+
                                             '<option value="ASSOCIATE" label="Associate" />'+
                                             '<option value="BACHELOUR" label="Bachelour" />'+
                                             '<option value="MASTER" label="Master" />'+
                                             '<option value="PHD" label="PHD" />'+
                                             '<option value="DOCTOR" label="Doctor" />'+
                                          '</select>'+
                                       '</div>'+
                                    '</div>'+
                                 '</div>'+
                                 '<div class="row col-lg-12 second-ed-row">'+
                                    '<div class="col-lg-3">'+
                                       '<label for="universityName" class="col-lg-5 control-label">Start Date</label>'+
                                       '<input id="datepicker1" type="text" class="col-lg-7 startEdDate" placeholder="Start Date" />'+
                                    '</div>'+
                                    '<div class="col-lg-4">'+
                                       '<label for="universityName" class="col-lg-5 control-label">Finish Date</label>'+
                                       '<input id="datepicker2" type="text" class="fcol-lg-7 finEdDate" placeholder="Finish Date" />'+
                                    '</div>'+
                                    '<div class="col-lg-2">'+
                                       '<input id="add-cand-education" class="add-cand-education" type="button" value="Add Education">'+
                                    '</div>'+
                                    '<div class="col-lg-2">'+
                                       '<input id="delete-cand-education" class="delete-cand-education" type="button" value="Delete Education" onclick="DeleteEducation(this)">'+
                                    '</div>'+
                                 '</div>'+
                                 '</div>');

showEducationCountry("educationSelectBody");

$( function() {
    $( "#datepicker1").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        maxDate: 0
      });
  } );

$( function() {
    $( "#datepicker2").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        maxDate: 0
      });
  } );
});
$(document).on('click','.delete-cand-education',function(){
  $(this).parent().parent().parent().remove();
});
$(document).on('click','.delete-existed-cand-education',function(){
  $(this).parent().parent().remove();
});




$(document).on('click','.add-cand-experiance',function(){

  $(".companyName").attr("readonly", true);
  $(".position").attr("readonly", true);
  $(".startExpDate").attr("readonly", true);
  $(".finishExpDate").attr("readonly", true);
  $(".expResponsibilities").attr("readonly", true);


  $(".delete-cand-experiance").addClass("shown-visibility");
  $(".add-cand-experiance").addClass("hidden-visibility");


   $("#companyName").attr("id", "submitedCompanyName");
   $("#position").attr("id", "submitedExpPosition");
   $("#datepicker3").attr("id", "submitedDatepicker3");
   $("#datepicker4").attr("id", "submitedDatepicker4");
   $("#expResponsibilities").attr("id", "submitedeExpResponsibilities");

          $('#copy-experiance-candidate').prepend('<div class="panel-body for-back-ed">'+
                                 '<div class="row col-lg-12 experianceSelectBody">'+
                                    '<div class="col-lg-6">'+
                                       '<label for="companyyName" class="col-lg-5 col-sm-2 control-label">Company Name</label>'+
                                       '<input id="companyName" class="col-lg-7 companyName" />'+
                                    '</div>'+
                                    '<div class="col-lg-6">'+
                                       '<label for="position" class="col-lg-4 col-sm-2 control-label">Position</label>'+
                                       '<input id="position" class="col-lg-8 position" />'+
                                    '</div>'+
                                  '</div>'+
                                    '<div class="row col-lg-12 experianceSelectBody second-ed-row">'+
                                       '<div class="col-lg-6">'+
                                          '<label for="startExpDate" class="col-lg-4 control-label">Start Date</label>'+
                                          '<input id="datepicker3" type="text" class="col-lg-8 startExpDate" placeholder="Start Date" />'+
                                       '</div>'+
                                       '<div class="col-lg-6">'+
                                          '<label for="finishExpDate" class="col-lg-4 control-label">Finish Date</label>'+
                                          '<input id="datepicker4" type="text" class="col-lg-8 finishExpDate" placeholder="Finish Date" />'+
                                       '</div>'+
                                    '</div>'+
                                    '<div class="row col-lg-12 experianceSelectBody second-ed-row">'+
                                       '<div class="col-lg-12">'+
                                          '<div  class="col-lg-offset-4">'+
                                             '<span><h3  id="university-header">Responsibilities</h3></span>'+
                                          '</div>'+
                                          '<textarea rows="6" type="text" id="expResponsibilities" class="col-lg-12 col-md-12  col-sm-12 col-xs-12 expResponsibilities"></textarea>'+
                                       '</div>'+
                                    '</div>'+
                                    '<div class="row col-lg-12 experianceSelectBody second-ed-row">'+
                                       '<div class="col-lg-2 col-lg-offset-8">'+
                                          '<input id="add-cand-experiance" class="add-cand-experiance" type="button" value="Add Experience" onclick="AddExperience()">'+
                                       '</div>'+
                                       '<div class="col-lg-2">'+
                                          '<input id="delete-cand-experiance" class="delete-cand-experiance" type="button" value="Delete Experience" onclick="DeleteExperience(this)">'+
                                       '</div>'+
                                    '</div>'+
                                 '</div>'+
                              '</div>');


$( function() {
    $( "#datepicker3").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        maxDate: 0
      });
  } );

$( function() {
    $( "#datepicker4").datepicker(
      {
        dateFormat: 'yy-mm-dd',
        maxDate: 0
      });
  } );
});
$(document).on('click','.delete-cand-experiance',function(){
  $(this).parent().parent().parent().remove();
});
$(document).on('click','.delete-existed-cand-experiance',function(){
  $(this).parent().parent().remove();
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
function FormatDate(date) {
               var formatted_birth = $.datepicker.formatDate('yy-mm-dd', new Date(date));
              return formatted_birth;
            };


function AddExperience(){
  var xhttp = new XMLHttpRequest();
  var url = "/candidate/add_new_experience?name=" + $("#companyName").val() + "&position=" + $("#position").val() + "&startDate=" + $("#datepicker3").val() + "&finishDate=" + $("#datepicker4").val() + "&responsibilities=" + $("#expResponsibilities").val();
  console.log(url);
  xhttp.open('GET', url, true);
  xhttp.send();
}

function DeleteExperience(x){
  var scope = $(x).parent().parent();
  var xhttp = new XMLHttpRequest();
  var url = "/candidate/delete_experience?name=" + $(scope).find("#submitedCompanyName").val() + "&position=" + $(scope).find("#submitedPosition").val() + "&responsibilities=" + $(scope).find("#submitedeExpResponsibilities").val();
  console.log(url);
  xhttp.open('GET', url, true);
  xhttp.send();
}


function AddEducation(){
  var xhttp = new XMLHttpRequest();
  var url = "/candidate/add_new_education?name=" + $("#universityName").val() + "&country=" + $("#countryEdSelect").val() + "&state=" + $("#stateEdSelect").val() + "&city=" + $("#cityEdSelect").val() + "&edField=" + $("#edFieldName").val() + "&edDegree=" + $("#edDegreeName").val() + "&startDate=" + $("#datepicker1").val() + "&finishDate=" + $("#datepicker2").val();
  console.log(url);
  xhttp.open('GET', url, true);
  xhttp.send();
}

function DeleteEducation(x){
  var scope = $(x).parent().parent();
  var xhttp = new XMLHttpRequest();
  var url = "/candidate/delete_education?name=" + $(scope).find("#submitedUniversityName").val() + "&startDate=" + FormatDate($(scope).find("#submitedDatepicker1").val()) + "&finishDate=" + FormatDate($(scope).find("#submitedDatepicker2").val());
  console.log(url);
  xhttp.open('GET', url, true);
  xhttp.send();
}

function SendTechSkill(){
  // if (!testName.test($("#techSkillName").val())) {
  //   alert("Please input valid name!");
  //   return;
  // }
	var xhttp = new XMLHttpRequest();
	var url = "/candidate/add_technical_skill?name=" + $("#techSkillName").val() + "&category=" + $("#techSkillCategory").val() + "&level=" + $("#techSkillLevel").val();
  console.log(url);
	xhttp.open('GET', url, true);
	xhttp.send();
	}

  function DeleteTechSkill(x){
	var scope = $(x).parent().parents()[0];
  	var xhttp = new XMLHttpRequest();
  	var url = "/candidate/delete_technical_skill?name=" + $(scope).find("#submitedTechSkillName").val() + "&category=" + $(scope).find("#submitedTechSkillCategory").val() + "&level=" + $(scope).find("#submitedTechSkillLevel").val();
    console.log(url);
  	xhttp.open('GET', url, true);
  	xhttp.send();
  	}


    function SendComSkill(){
    	var xhttp = new XMLHttpRequest();
    	var url = "/candidate/add_communication_skill?name=" + $("#comSkillName").val() + "&level=" + $("#comSkillLevel").val();
      console.log(url);
    	xhttp.open('GET', url, true);
    	xhttp.send();
    	}

      function DeleteComSkill(x){
    	var scope = $(x).parent().parents()[0];
      	var xhttp = new XMLHttpRequest();
      	var url = "/candidate/delete_communication_skill?name=" + $(scope).find("#submitedComSkillName").val() + "&level=" + $(scope).find("#submitedComSkillLevel").val();
        console.log(url);
      	xhttp.open('GET', url, true);
      	xhttp.send();
      	}


        function SendPersSkill(){
          var xhttp = new XMLHttpRequest();
          var url = "/candidate/add_personal_skill?name=" + $("#persSkillName").val() + "&level=" + $("#persSkillLevel").val();
          console.log(url);
          xhttp.open('GET', url, true);
          xhttp.send();
          }

          function DeletePersSkill(x){
          	var scope = $(x).parent().parents()[0];
            var xhttp = new XMLHttpRequest();
            var url = "/candidate/delete_personal_skill?name=" + $(scope).find("#submitedPersSkillName").val() + "&level=" + $(scope).find("#submitedPersSkillLevel").val();
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
