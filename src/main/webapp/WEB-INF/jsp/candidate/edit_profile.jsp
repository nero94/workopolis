
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
        <head>
            <title>Candidate</title>
            <%@ include file="../../jspf/head.jspf" %>
                <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Tangerine">
                <link rel="stylesheet" href="/resources/candidate/css/Candidate's_profile.css">
                <script src="/resources/candidate/js/Candidate's_profile.js"></script>
                <link rel="stylesheet" href="/resources/css/add-vacancy.css" charset="utf-8">
                <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
                <link rel="stylesheet" href="/resources/css/popup.css">

                <script type="text/javascript">
          	  	<%@ include file="/resources/employer/validation.js"%>
        	   </script>
        </head>

        <body>
            <%@ include file="../../jspf/candidate_header.jspf" %>

                  <div id="ss_menu">
                     <div class="ss_button"><h3 class="acord-head">Change user info</h3></div>
                     <div class="ss_content">
                                 <div class="panel panel-default">
                        <div class="panel-heading">
                           <p>Change user info</p>
                        </div>
                        <div class="panel-body">
                            <form action="/candidate/edit_personal_info" path="candidate" method="POST" class="form-horizontal">
                                <div class="form-group">
                                    <label for="name" class="col-sm-4 control-label">Name</label>
                                    <div class="col-lg-4">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${candidate.firstName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="middlename" class="col-lg-4 control-label">Middle name</label>
                                    <div class="col-lg-4">
                                        <input type="text" class="form-control" id="middlename" name="middleName" placeholder="Middle name" value="${candidate.middleName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lastname" class="col-lg-4 control-label">Lastname</label>
                                    <div class="col-lg-4">
                                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Lastname" value="${candidate.lastName}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone-number" class="col-lg-4 control-label">Mobile phone</label>
                                    <div class="col-lg-4">
                                        <input type="tel" name="telNumber" class="form-control" id="phone-number" placeholder="Phone number" value="${candidate.telNumber}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="date-of-birth" class="col-lg-4 control-label">Date of Birth</label>
                                    <div class="col-lg-4">

                                        <input id="datepicker" type='text' class="form-control" name="dateOfBirth" placeholder="Date of Birth" value="${candidate.dateofBirth}" />
                                    </div> 
                                </div>
                                <div class="form-group">
                                    <label for="additional" class="col-lg-4 control-label">Additional information</label>
                                    <div class="col-lg-4">
                                        <textarea name="additionalInformation" class="form-control" id="additional" placeholder="Additional information">${candidate.additionalInformation}</textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="skype" class="col-lg-4 control-label">Skype</label>
                                    <div class="col-lg-4">
                                        <input type="text" name="skypeUrl" class="form-control" id="skype" placeholder="Skype" value="${candidate.skypeUrl}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="facebook" class="col-lg-4 control-label">Facebook link</label>
                                    <div class="col-lg-4">
                                        <input type="url" name="facebookUrl" class="form-control" id="facebook" placeholder="Facebook link" value="${candidate.facebookUrl}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="linkedin" class="col-lg-4 control-label">LinkedIn link</label>
                                    <div class="col-lg-4">
                                        <input type="url" name="linkedInUrl" class="form-control" id="linkedin" placeholder="LinkedIn link" value="${candidate.linkedInUrl}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-2 col-lg-offset-5">
                                        <button type="submit" action class="btn btn-success">Change</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                     </div>
                     <div class="ss_button"><h3 class="acord-head">Address info</h3></div>
                     <div class="ss_content">
                                 <div class="panel panel-default">
                        <div class="panel-heading">
                            <p>Address info</p>
                        </div>
                        <div class="panel-body" id="candidateAddressBody">
                            <form action="/candidate/edit_location_info" method="POST" class="form-horizontal">
                                <div class="form-group">
                                    <label for="countrySelect" class="col-lg-4 control-label">Country</label>
                                    <div id="country" class="country col-lg-4">
                                        <select class='form-control' id='countrySelect' name='country'>
                                            <option value='${candidate.address.country.id}'>${candidate.address.country.name}</option>
                                        </select>
                                        <input type="hidden" id="countrySelectName" value="${candidate.address.country.name}">
                                        <input type="hidden" id="countrySelectId" value="${candidate.address.country.id}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="stateSelect" class="col-lg-4 control-label">State</label>
                                    <div id="state" class="col-lg-4">
                                        <select class="form-control" id="stateSelect" name="state" disabled>
                                            <option value='${candidate.address.state.id}'>${candidate.address.state.name}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="citySelect" class="col-lg-4 control-label">City</label>
                                    <div id="city" class="col-lg-4">
                                        <select class="form-control" id="citySelect" name="city" disabled>
                                            <option value='${candidate.address.city.id}'>${candidate.address.city.name}</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="zipcode" class="col-lg-4 control-label">Zipcode</label>
                                    <div class="col-lg-4">
                                        <input type="text" name="zip" class="form-control" id="zipcode" placeholder="Zipcode" value="${candidate.address.zip}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="street" class="col-lg-4 control-label">Street</label>
                                    <div class="col-lg-4">
                                        <input type="text" name="street" class="form-control" id="street" placeholder="Street" value="${candidate.address.street}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-2 col-lg-offset-5">
                                        <button type="submit" action class="btn btn-success">Change</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                     </div>
                     <div class="ss_button"><h3 class="acord-head">Change professional info</h3></div>
                     <div class="ss_content">
                              <div class="panel panel-default">
                        <div class="panel-heading">
                            <p>Change professional info</p>
                        </div>
                        <div class="panel-body">
                            <form action="/candidate/edit_professional_info" method="POST" class="form-horizontal">
                                <div class="form-group">
                                    <label for="direction" class="col-lg-4 control-label">Interested direction</label>
                                    <div class="col-lg-4">
                                        <select name="direction" class="form-control" id="direction">
                                            <option id="selectedDirection" value="${candidate.interstedDirection}" label="" />
                                            <option value="ADMINISTRATION" label="Administration" />
                                            <option value="BUSINESS_ANALYSIS" label="Business Analysis" />
                                            <option value="DESIGN" label="Design" />
                                            <option value="DATABASES" label="Databases" />
                                            <option value="PROJECT_MANAGEMENT" label="Project Management" />
                                            <option value="QUALITY_CONTROL" label="Quality Control" />
                                            <option value="SOFTWARE_DEVELOPMENT" label="Software Development" />
                                            <option value="SYSTEM_ARCHITECTURE" label="System Archtecture" />
                                        </select>
                                    </div>
                                </div>
                                <table class="table table-striped table-responsive" id="copy-tech-skill">
                                    <thead>
                                        <th>
                                            <h3>Technical Skills Input</h3>
                                        </th>
                                    </thead>
                                    <div class="readonly">
                                        <tbody>
                                            <thead>
                                                <th>
                                                    <h4>Input Name of The Skill</h4>
                                                </th>
                                                <th>
                                                    <h4>Select a Category</h4>
                                                </th>
                                                <th>
                                                    <h4>Select a required level</h4>
                                                </th>
                                            </thead>
                                            <c:forEach items="${candidate.candidateTechnicalSkills}" var="technicalSkill">
                                                <tbody>
                                                    <td>
                                                        <input id="submitedTechSkillName" value="${technicalSkill.name}" class="col-lg-8 techSkillName" readonly/>
                                                    </td>
                                                    <td>
                                                        <select id="submitedTechSkillCategory" class="form-control techSkillSelect" disabled>
                                                            <option class="techSkillOption" value="${technicalSkill.category}" label="${technicalSkill.category}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select id="submitedTechSkillLevel" class="form-control techSkillSelect" disabled>
                                                            <option class="techSkillOption" value="${technicalSkill.level}" label="${technicalSkill.level}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input id="add-tech-skill" class="add-tech-skill hidden-visibility" type="button" value="Save Skill" onclick="SendTechSkill()">
                                                    </td>
                                                    <td>
                                                        <input id="delete-tech-skill" class="delete-tech-skill shown-visibility" type="button" value="Delete Skill" onclick="DeleteTechSkill(this)">
                                                    </td>
                                                </tbody>
                                            </c:forEach>
                                            <tbody>
                                                <td>
                                                    <input id="techSkillName" class="col-lg-8 techSkillName" />
                                                </td>
                                                <td>
                                                    <select id="techSkillCategory" class="form-control techSkillSelect">
                                                        <option value="TECHNOLOGIES" label="Technologies" />
                                                        <option value="LANGUAGES" label="Languages" />
                                                        <option value="FRAMEWORKS" label="Frameworks" />
                                                        <option value="DATABASES" label="Databases" />
                                                        <option value="TOOLS" label="Tools" />
                                                        <option value="PROTOCOLS" label="Protocols" />
                                                        <option value="LIBRARIES" label="Libraries" />
                                                        <option value="TECHNIQUES" label="Techniques" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="techSkillLevel" class="form-control techSkillSelect">
                                                        <option value="BASIC" label="Basic" />
                                                        <option value="AVERAGE" label="Average" />
                                                        <option value="STRONG" label="Strong" />
                                                        <option value="EXPERT" label="Expert" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="add-tech-skill" class="add-tech-skill" type="button" value="Save Skill" onclick="SendTechSkill()">
                                                </td>
                                                <td>
                                                    <input id="delete-tech-skill" class="delete-tech-skill" type="button" value="Delete Skill" onclick="DeleteTechSkill(this)">
                                                </td>
                                            </tbody>
                                        </tbody>
                                    </div>
                                </table>
                                <table class="table table-striped table-responsive" id="copy-com-skill">
                                    <thead>
                                        <th>
                                            <h3>Communication Skills Input</h3>
                                        </th>
                                    </thead>
                                    <div class="readonly">
                                        <tbody>
                                            <thead>
                                                <th>
                                                    <h4>Select a Language name</h4>
                                                </th>
                                                <th>
                                                    <h4>Select a required level</h4>
                                                </th>
                                            </thead>
                                            <c:forEach items="${candidate.candidateCommunicationSkills}" var="communicationSkill">
                                                <tbody>
                                                    <td>
                                                        <select id="submitedComSkillName" class="form-control comSkillSelect" disabled>
                                                            <option value="${communicationSkill.name}" label="${communicationSkill.name}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select id="submitedComSkillLevel" class="form-control comSkillSelect" disabled>
                                                            <option value="${communicationSkill.level}" label="${communicationSkill.level}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input id="add-com-skill" class="add-com-skill hidden-visibility" type="button" value="Save Skill" onclick="SendComSkill()">
                                                    </td>
                                                    <td>
                                                        <input id="delete-com-skill" class="delete-com-skill shown-visibility" type="button" value="Delete Skill" onclick="DeleteComSkill(this)">
                                                    </td>
                                                </tbody>
                                            </c:forEach>
                                            <tbody>
                                                <td>
                                                    <select id="comSkillName" class="form-control comSkillSelect">
                                                        <option value="ENGLISH" label="English" />
                                                        <option value="GERMAN" label="German" />
                                                        <option value="FRENCH" label="French" />
                                                        <option value="SPANISH" label="Spanish" />
                                                        <option value="RUSSIAN" label="Russian" />
                                                        <option value="UKRAINIAN" label="Ukrainian" />
                                                        <option value="POLISH" label="Polish" />
                                                        <option value="CHINEESE" label="Chineese" />
                                                        <option value="JAPANEESE" label="Japaneese" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="comSkillLevel" class="form-control comSkillSelect">
                                                        <option value="BEGINER" label="Beginer" />
                                                        <option value="ELEMENTARY" label="Elementary" />
                                                        <option value="INTERMEDIATE" label="Intermediate" />
                                                        <option value="UPPER_INTERMEDIATE" label="Upper Intermediate" />
                                                        <option value="ADVANCED" label="Advanced" />
                                                        <option value="PROFICIENT" label="Proficient" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="add-com-skill" class="add-com-skill" type="button" value="Save Skill" onclick="SendComSkill()">
                                                </td>
                                                <td>
                                                    <input id="delete-com-skill" class="delete-com-skill" type="button" value="Delete Skill" onclick="DeleteComSkill(this)">
                                                </td>
                                            </tbody>
                                        </tbody>
                                    </div>
                                </table>
                                <table class="table table-striped table-responsive" id="copy-pers-skill">
                                    <thead>
                                        <th>
                                            <h3>Personal Skills Input</h3>
                                        </th>
                                    </thead>
                                    <div class="readonly">
                                        <tbody>
                                            <thead>
                                                <th>
                                                    <h4>Select a Name of The Skill</h4>
                                                </th>
                                                <th colspan="3">
                                                    <h4>Select a required level</h4>
                                                </th>
                                            </thead>
                                            <c:forEach items="${candidate.candidatePersonalSkills}" var="personalSkill">
                                                <tbody>
                                                    <td>
                                                        <select id="submitedPersSkillName" class="form-control persSkillSelect" disabled>
                                                            <option class="techSkillOption" value="${personalSkill.personalSkillName}" label="${personalSkill.personalSkillName}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select id="submitedPersSkillLevel" class="form-control persSkillSelect" disabled>
                                                            <option value="${personalSkill.personalSkillLevel}" label="${personalSkill.personalSkillLevel}" />
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input id="add-pers-skill" class="add-pers-skill hidden-visibility" type="button" value="Save Skill" onclick="SendPersSkill()">
                                                    </td>
                                                    <td>
                                                        <input id="delete-pers-skill" class="delete-pers-skill shown-visibility" type="button" value="Delete Skill" onclick="DeletePersSkill(this)">
                                                    </td>
                                                </tbody>
                                            </c:forEach>
                                            <tbody>
                                                <td>
                                                    <select id="persSkillName" class="form-control persSkillSelect">
                                                        <option value="OPENESS_TO_EXPERIENCE" label="Openess to experiance" />
                                                        <option value="CONSCIENTIOUSNESS" label="Conscientiosness" />
                                                        <option value="EXTRAVERSION" label="Extraversion" />
                                                        <option value="AGREEABLENESS" label="Agreeableness" />
                                                        <option value="TEUROTICISM" label="Teuroticism" />
                                                        <option value="TEAMWORK" label="Tamwork" />
                                                        <option value="INITIATIVE" label="Initiative" />
                                                        <option value="FLEXIBILITY" label="Flexibility" />
                                                        <option value="TIME_MANAGEMENT" label="Time management" />
                                                        <option value="DECISION_MAKING" label="Decision making" />
                                                        <option value="CREATIVITY" label="Creativity" />
                                                        <option value="TRUSTWORTHINESS" label="Trustworthiness" />
                                                        <option value="EMOTIONAL_STABILITY" label="Emotional stability" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <select id="persSkillLevel" class="form-control persSkillSelect">
                                                        <option value="MINIMUM" label="Minimum" />
                                                        <option value="AVERAGE" label="Average" />
                                                        <option value="MAXIMUM" label="Maximum" />
                                                    </select>
                                                </td>
                                                <td>
                                                    <input id="add-pers-skill" class="add-pers-skill" type="button" value="Save Skill" onclick="SendPersSkill()">
                                                </td>
                                                <td>
                                                    <input id="delete-pers-skill" class="delete-pers-skill" type="button" value="Delete Skill" onclick="DeletePersSkill(this)">
                                                </td>
                                            </tbody>
                                        </tbody>
                                    </div>
                                </table>
                                <div class="form-group">
                                    <div class="col-lg-1 col-lg-offset-5">
                                        <button type="submit" action class="btn btn-success">Change</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>



                     </div>
                     <div class="ss_button"><h3 class="acord-head">Education info</h3></div>
                     <div class="ss_content">
                                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p>Education info</p>
                        </div>
                        <span><h3  id="university-header">University</h3></span>

                              <form action="/candidate/edit_education_info" method="POST" class="form-horizontal">

                              <div id="copy-education-candidate">
                               <div class="panel-body for-back-ed">
                                 <div class="row col-lg-12 col-md-12 col-sm-12 col-xs-12" id="educationSelectBody">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                       <label for="universityName" class="col-lg-3 col-md-3 col-xs-3 col-sm-3 control-label">Name</label>
                                       <input id="universityName" class="col-lg-9 col-md-9 col-sm-9 col-xs-9 universityName" />
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                       <label for="universityCountry" class="col-lg-3  col-md-3 col-xs-3  col-sm-3 control-label">Country</label>
                                       <div id="country" class="country col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                          <select class='form-control edSelect' id='countryEdSelect'>
                                             <option value=''></option>
                                          </select>
                                          <input type="hidden" id="countryEducationSelectName" value="">
                                          <input type="hidden" id="countryEducationSelectId" value="">
                                       </div>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                       <label for="universityState" class="col-lg-3  col-md-3 col-xs-3  col-sm-3 control-label">State</label>
                                       <div id="educationState" class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                          <select class="form-control edSelect" id="stateEdSelect" disabled>
                                             <option value=''></option>
                                          </select>
                                       </div>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                       <label for="universityCity" class="col-lg-3  col-md-3 col-xs-3 col-sm-3 control-label">City</label>
                                       <div id="educationCity" class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                          <select class="form-control edSelect" id="cityEdSelect" disabled>
                                             <option value=''></option>
                                          </select>
                                       </div>
                                    </div>
                                 </div>
                                 <div class="row col-lg-12 second-ed-row">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                       <label for="universityName" class="col-lg-2 col-sm-2  col-md-2  col-xs-2 control-label">Education Field</label>
                                       <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                          <select id="edFieldName" class="form-control edSelect">
                                             <option value="AUTOMATION_AND_MACHINE_CONSTRUCTING" label="Automation and Machine Constructing" />
                                             <option value="ELECTRONICS_AND_TELECOMMUNICATIONS" label="Electronics and Telecommunications" />
                                             <option value="SYSTEM_ANALYSIS" label="System Analysis" />
                                             <option value="COMPUTER_SCIENCE" label="Computer Science" />
                                             <option value="SOFTWARE_ENGINEERING" label="Software Engineering" />
                                             <option value="SYSTEM_ARCHITECTURE" label="System Architecture" />
                                             <option value="PROJECT_MANAGEMENT" label="Project Management" />
                                             <option value="SYSTEM_PROGRAMMING" label="System Programming" />
                                             <option value="METROLOGY_AND_MEASURING_EQUIPMENT" label="Metrology and Measuring Equipment" />
                                             <option value="INFORMATION_TECHNOLOGY" label="Information Technology" />
                                             <option value="INFORMATION_AND_COMMUNICATION" label="Information and Communication" />
                                             <option value="SYSTEMS_SECURITY" label="Systems Security" />
                                             <option value="INFORMATION_SECURITY_MANAGEMENT" label="Information Security Management" />
                                             <option value="QUALITY_STANDARTIZATION_AND_CERTIFICATION" label="Quality Standartization and Certification" />
                                             <option value="APPLIED_MATHEMATICS" label="Applied Mathematics" />
                                             <option value="APPLIED_PHYSICS" label="Applied Physics" />
                                             <option value="MATHEMATICAL_AND_COMPUTER_MODELING" label="Mathematical and Computer Modeling" />
                                          </select>
                                       </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 ">
                                       <label for="universityName" class="col-lg-2 col-md-2 col-ms-2 col-xs-2 control-label">Education Degree</label>
                                       <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
                                          <select id="edDegreeName" class="form-control edSelect">
                                             <option value="ASSOCIATE" label="Associate" />
                                             <option value="BACHELOUR" label="Bachelour" />
                                             <option value="MASTER" label="Master" />
                                             <option value="PHD" label="PHD" />
                                             <option value="DOCTOR" label="Doctor" />
                                          </select>
                                       </div>
                                    </div>
                                 </div>
                                 <div class="row col-lg-12 second-ed-row">
                                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                       <label for="universityName" class="col-lg-5 control-label">Start Date</label>
                                       <input id="datepicker1" type='text' class="col-lg-7  col-md-7 col-sm-7 col-xs-7 startEdDate" placeholder="Start Date" />
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                                       <label for="universityName" class="col-lg-5  col-md-5 col-sm-5 col-xs-5 control-label">Finish Date</label>
                                       <input id="datepicker2" type='text' class="col-lg-7  col-md-7 col-sm-7 col-xs-7 finEdDate" placeholder="Finish Date" />
                                    </div>
                                    <div class="col-lg-2  col-md-2 col-sm-2 col-xs-2">
                                       <input id="add-cand-education" class="add-cand-education" type="button" value="Add Education" onclick="AddEducation()">
                                    </div>
                                    <div class="col-lg-2  col-md-2 col-sm-2 col-xs-2">
                                       <input id="delete-cand-education" class="delete-cand-education" type="button" value="Delete Education" onclick="DeleteEducation(this)">
                                    </div>
                                 </div>
                              </div>
                              </div>



                              <c:forEach items="${candidate.educations}" var="education">
                                 <div class="panel-body for-back-ed">
                                    <div class="row col-lg-12">
                                       <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                          <label for="universityName" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label">Name</label>
                                          <input id="submitedUniversityName" value="${education.university.name}" class="col-lg-9 universityName" readonly/>
                                       </div>
                                       <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                          <label for="universityCountry" class="col-lg-3 col-sm-2 control-label">Country</label>
                                          <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                             <select id="submitedEdCountryEdSelect" class="form-control universitySelect" disabled>
                                                <option class="universityOption" value="${education.university.address.country.name}" label="${education.university.address.country.name}" />
                                             </select>
                                          </div>
                                       </div>
                                       <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                          <label for="universityCountry" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label">State</label>
                                          <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                             <select id="submitedEdStateEdSelect" class="form-control universitySelect" disabled>
                                                <option class="universityOption" value="${education.university.address.state.name}" label="${education.university.address.state.name}" />
                                             </select>
                                          </div>
                                       </div>
                                       <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                                          <label for="universityCountry" class="col-lg-3 col-sm-2 control-label">City</label>
                                          <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                             <select id="submitedEdCityEdSelect" class="form-control universitySelect" disabled>
                                                <option class="universityOption" value="${education.university.address.city.name}" label="${education.university.address.city.name}" />
                                             </select>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 second-ed-row">
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="universityName" class="col-lg-2 col-sm-2 control-label">Education Field</label>
                                          <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                             <select id="submitedEdFieldName" class="form-control universitySelect" disabled>
                                                <option id="" class="educationOption educationFieldForCandidate" value="${education.educationField}" />
                                             </select>
                                          </div>
                                       </div>
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="universityName" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">Education Degree</label>
                                          <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                                             <select id="submitedEdDegreeName" class="form-control universitySelect" disabled>
                                                <option class="educationOption" value="${education.educationDegree}" label="${education.educationDegree}" />
                                             </select>
                                          </div>
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 second-ed-row">
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="universityName" class="col-lg-5  col-md-5 col-sm-5 col-xs-5 control-label">Start Date</label>
                                          <fmt:parseDate pattern="yyyy-MM-dd" value="${education.university.startDate}" var="parsedStartDate" />
                                          <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedStartDate}" var="formattedStartDate" />
                                          <input id="submitedDatepicker1" type='text' class="col-lg-7  col-md-7 col-sm-7 col-xs-7 dateEdPicker submitedDatepicker1"  value="${formattedStartDate}" readonly/>
                                       </div>
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="universityName" class="col-lg-5  col-md-5 col-sm-5 col-xs-5 control-label">Finish Date</label>
                                           <fmt:parseDate pattern="yyyy-MM-dd" value="${education.university.finishDate}" var="parsedFinishDate" />
                                          <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedFinishDate}" var="formattedFinishDate" />
                                          <input id="submitedDatepicker2" type='text' class="col-lg-7  col-md-7 col-sm-7 col-xs-7  dateEdPicker submitedDatepicker1" value="${formattedFinishDate}" readonly/>
                                       </div>

                                          <input id="delete-existed-cand-education" class="delete-existed-cand-education" type="button" value="Delete Education" onclick="DeleteEducation(this)"/>

                                    </div>
                                 </div>
                              </c:forEach>

                              <div class="panel-body">
                                 <div class="col-lg-1 col-lg-offset-5 col-md-1 col-md-offset-5 col-sm-1 col-sm-offset-5 col-xs-1 col-xs-offset-5">
                                    <button type="submit" action class="btn btn-success">Change</button>
                                 </div>
                              </div>
                        </form>
                      </div>






                     </div>
                     <div class="ss_button"><h3 class="acord-head">Experience Info</h3></div>
                     <div class="ss_content">
                              <div class="panel panel-default">
                        <div class="panel-heading">
                            <p>Experience Info</p>
                        </div>
                        <span><h3  id="university-header">Experience</h3></span>
                        <form action="/candidate/edit_experiance_info" method="POST" class="form-horizontal">
                           <div id="copy-experiance-candidate">
                              <div class="panel-body for-back-ed">
                                 <div class="row col-lg-12 experianceSelectBody">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                       <label for="companyyName" class="col-lg-5 col-md-5 col-sm-5 col-xs-5 control-label">Company Name</label>
                                       <input id="companyName" class="col-lg-7 col-md-7 col-sm-7 col-xs-7 companyName" />
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                       <label for="position" class="col-lg-4 col-md-4 col-sm-4 col-xs-4  control-label">Position</label>
                                       <input id="position" class="col-lg-8 col-md-8 col-sm-8 col-xs-8 position" />
                                    </div>
                                 </div>
                                    <div class="row col-lg-12 experianceSelectBody second-ed-row">
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="startExpDate" class="col-lg-4  col-md-4 col-sm-4 col-xs-4 control-label">Start Date</label>
                                          <input id="datepicker3" type='text' class="col-lg-8 startExpDate" placeholder="Start Date" />
                                       </div>
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="finishExpDate" class="col-lg-4 col-md-4 col-sm-4 col-xs-4  control-label">Finish Date</label>
                                          <input id="datepicker4" type='text' class="col-lg-8  col-md-8 col-sm-8 col-xs-8  finishExpDate" placeholder="Finish Date" />
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 experianceSelectBody second-ed-row">
                                       <div class="col-lg-12">
                                          <div  class="col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4">
                                             <span><h3  id="university-header">Responsibilities</h3></span>
                                          </div>
                                          <textarea rows="6" type="text" id="expResponsibilities" class="col-lg-12 col-md-12  col-sm-12 col-xs-12 expResponsibilities"></textarea>
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 experianceSelectBody second-ed-row">
                                       <div class="col-lg-2 col-lg-offset-8 col-md-2 col-md-offset-8 col-sm-2 col-sm-offset-8 col-xs-2 col-xs-offset-8">
                                          <input id="add-cand-experiance" class="add-cand-experiance" type="button" value="Add Experience" onclick="AddExperience()">
                                       </div>
                                       <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                          <input id="delete-cand-experiance" class="delete-cand-experiance" type="button" value="Delete Experience" onclick="DeleteExperience(this)">
                                       </div>
                                    </div>
                              </div>
                           </div>
                           <c:forEach items="${candidate.experiances}" var="experiance">
                              <div class="panel-body for-back-ed">
                                    <div class="row col-lg-12">
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="companyyName" class="col-lg-5 col-sm-2 control-label">Company Name</label>
                                          <input id="submitedCompanyName" value="${experiance.companyName}" class="col-lg-7 companyName" readonly/>
                                       </div>
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="position" class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label">Position</label>
                                          <input id="submitedPosition" value="${experiance.position}" class="col-lg-8 companyName" readonly/>
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 experianceSelectBody second-ed-row">
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="startExpDate" class="col-lg-4  col-md-4 col-sm-4 col-xs-4  control-label">Start Date</label>
                                           <fmt:parseDate pattern="yyyy-MM-dd" value="${experiance.startDate}" var="parsedExpStartDate" />
                                           <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedExpStartDate}" var="formattedExpStartDate" />
                                          <input id="submitedDatepicker3" type='text' class="col-lg-8 col-md-8  col-sm-8  col-xs-8 dateExpPicker submitedDatepicker3"  placeholder="Start Date" value="${formattedExpStartDate}" readonly/>
                                       </div>
                                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                          <label for="finishExpDate" class="col-lg-4  col-md-4 col-sm-4 col-xs-4  control-label">Finish Date</label>
                                          <fmt:parseDate pattern="yyyy-MM-dd" value="${experiance.finishdate}" var="parsedExpFinishDate" />
                                           <fmt:formatDate pattern="MM/dd/yyyy" value="${parsedExpFinishDate}" var="formattedExpFinishDate" />
                                          <input id="submitedDatepicker4" type='text' class="col-lg-8  col-md-8  col-sm-8  col-xs-8  dateExpPicker submitedDatepicker4"  placeholder="Finish Date" value="${formattedExpFinishDate}" readonly/>
                                       </div>
                                    </div>
                                    <div class="row col-lg-12 experianceSelectBody second-ed-row">
                                       <div class="col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4">
                                          <span><h3  id="university-header">Responsibilities</h3></span>
                                       </div>
                                       <textarea rows="6" type="text" id="submitedeExpResponsibilities" class="col-lg-12 col-md-12  col-sm-12 col-xs-12 submitedeExpResponsibilities" readonly>${experiance.responsibilities}</textarea>
                                    </div>
                                    <div class="col-lg-2 col-lg-offset-8 col-md-2 col-md-offset-8 col-sm-2 col-sm-offset-8 col-xs-2 col-xs-offset-8">
                                       <input id="delete-existed-cand-experiance" class="delete-existed-cand-experiance" type="button" value="Delete Experience" onclick="DeleteExperience(this)">
                                    </div>
                              </div>
                           </c:forEach>
                           <div class="panel-body">
                              <div class="col-lg-1 col-lg-offset-5 col-md-1 col-md-offset-5 col-sm-1 col-sm-offset-5 col-xs-1 col-xs-offset-5">
                                 <button type="submit" action class="btn btn-success">Change</button>
                              </div>
                           </div>
                        </form>
                     </div>



                     </div>
                     <div class="ss_button"><h3 class="acord-head">Change password</h3></div>
                     <div class="ss_content">
                              <div class="panel panel-default">
                        <div class="panel-heading">
                            <p>Change password</p>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" action="/candidate/change_password" method="POST" name="frm" onsubmit="return validateForm()">
                                <div class="form-group">
                                    <label for="new-password" class="col-sm-4 control-label">New password</label>
                                    <div class="col-lg-4">
                                        <input type="password" class="form-control" id="password1" placeholder="New password" name="newPassword1">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="repeat-new-password" class="col-sm-4 control-label">Repeat new password</label>
                                    <div class="col-lg-4">
                                        <input type="password" class="form-control" id="password2" placeholder="Repeat new password" name="newPassword2">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-2 col-lg-offset-5">
                                        <button type="submit" class="btn btn-success">Change</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>



                     </div>


                  </div>


              	<c:if test="${not empty msg}">
          		<div id="dialog" title="">
          			${msg}
          		</div>
              </c:if>

                <%@ include file="../../jspf/footer.jspf" %>
                    <script src="/resources/js/location-controller.js"></script>
                    <script src="/resources/js/edit-candidate.js"></script>
                    <script>
                        showCountry("candidateAddressBody", document.getElementById("countrySelectId").value, document.getElementById("countrySelectName").value);
                         showEducationCountry("educationSelectBody");

                        var skillOptions = {
                            TECHNOLOGIES: "Technologies",
                            LANGUAGES: "Languages",
                            FRAMEWORKS: "Frameworks",
                            DATABASES: "Databases",
                            TOOLS: "Tools",
                            PROTOCOLS: "Protocols",
                            LIBRARIES: "Libraries",
                            TECHNIQUES: "Techniques",
                            OPENESS_TO_EXPERIENCE: "Openess to experiance",
                            CONSCIENTIOUSNESS: "Conscientiosness",
                            EXTRAVERSION: "Extraversion",
                            AGREEABLENESS: "Agreeableness",
                            TEUROTICISM: "Teuroticism",
                            TEAMWORK: "Tamwork",
                            INITIATIVE: "Initiative",
                            FLEXIBILITY: "Flexibility",
                            TIME_MANAGEMENT: "Time management",
                            DECISION_MAKING: "Decision making",
                            CREATIVITY: "Creativity",
                            TRUSTWORTHINESS: "Trustworthiness",
                            EMOTIONAL_STABILITY: "Emotional stability",
                            ADMINISTRATION : "Administration",
                            BUSINESS_ANALYSIS : "Business Analysis",
                            DESIGN : "Design",
                            DATABASES : "Databases",
                            PROJECT_MANAGEMENT : "Project Management",
                            QUALITY_CONTROL : "Quality Control",
                            SOFTWARE_DEVELOPMENT : "Software Development",
                            SYSTEM_ARCHITECTURE : "System Architecrute"
                        }

                        var educationFields = {
                            AUTOMATION_AND_MACHINE_CONSTRUCTING : "Automomation and Machine Constructing",
                            ELECTRONICS_AND_TELECOMMUNICATIONS : "Electronics and Telecommunications",
                            SYSTEM_ANALYSIS : "System Analysis",
                            COMPUTER_SCIENCE : "Computer Science",
                            SOFTWARE_ENGINEERING : "Software Engineering",
                            SYSTEM_ARCHITECTURE : "System Architecture",
                            PROJECT_MANAGEMENT : "Project Management",
                            SYSTEM_PROGRAMMING : "System Programming",
                            METROLOGY_AND_MEASURING_EQUIPMENT : "Metrology and Measuring Equipment",
                            INFORMATION_TECHNOLOGY : "Information Technology",
                            INFORMATION_AND_COMMUNICATION : "Information and Communication",
                            SYSTEMS_SECURITY : "System Security",
                            INFORMATION_SECURITY_MANAGEMENT : "Information Security Management",
                            QUALITY_STANDARTIZATION_AND_CERTIFICATION : "Quality Standartization and Certification",
                            APPLIED_MATHEMATICS : "Applied Mathematics",
                            APPLIED_PHYSICS : "Applied Physics",
                            MATHEMATICAL_AND_COMPUTER_MODELING : "Mathematical and Computer Modeling"
                        }

                        $("#selectedDirection").attr("label", skillOptions[$("#selectedDirection").val()]);

                        $(".educationFieldForCandidate").each(function(){
                            $(this).html(educationFields[$(this).val()])
                        })
                        $(function() {
                            $('#datetimepicker1').datetimepicker();
                        });

                    </script>


        </body>

        </html>
