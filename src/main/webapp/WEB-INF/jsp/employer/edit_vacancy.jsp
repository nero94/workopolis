<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.edvantis.workopolis.model.skill.TechnicalSkill"%>
<%@ page import="com.edvantis.workopolis.model.vacancy.Vacancy"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Adding Vacancy</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file="../../jspf/head.jspf"%>
    <link rel="stylesheet" href="resources/css/add-vacancy.css" charset="utf-8">
    <link rel="stylesheet" href="resources/css/interviewer-profile.css" charset="utf-8">
</head>
<body>
  <%@ include file="../../jspf/employer_header.jspf"%>
  <div id="add-vac-container" class="container">
    <div class="interv-info well col-lg-12">
      <div id="int-info-head" class="well col-lg-12 row">
        <h3 id="my-int" class="int-name">Edit Vacancy</h3>
      </div>
      <div class="col-lg-12 intervs well">
        <form:form action="/edit_vacancy" method="post" modelAttribute="vacancy">
          <table class="table table-striped table-responsive">
            <thead>
              <th>Title</th>
              <th>Direction</th>
              <th>Status</th>
              <th>Salary</th>
            </thead>
            <tbody>
              <td><form:input path="title"/></td>
              <td><form:select path="direction" class="form-control" >
                    <form:option value="ADMINISTRATION" label="Administration"/>
                    <form:option value="BUSINESS_ANALYSIS" label="Business Analysis"/>
                    <form:option value="DESIGN" label="Design"/>
                    <form:option value="DATABASES" label="Databases"/>
                    <form:option value="PROJECT_MANAGEMENT" label="Project Management"/>
                    <form:option value="QUALITY_CONTROL" label="Quality Control"/>
                    <form:option value="SOFTWARE_DEVELOPMENT" label="Software Development"/>
                    <form:option value="SYSTEM_ARCHITECTURE" label="System Archtecture"/>
                  </form:select></td>
              <td><form:select path="status" class="form-control" >
                    <form:option value="FULL_TIME" label="Full Time"/>
                    <form:option value="PART_TIME" label="Part Time"/>
                  </form:select></td>
                  <td><div class="input-group">
                    <span class="input-group-addon">$</span>
                    <form:input path="salary" type="number"/>
                  </div></td>
            </tbody>
          </table>
          <table class="table table-striped table-responsive">
            <thead>
              <th><h3>General Description</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="description" type="text" id="tworows" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
            </tbody>
            <thead>
              <th><h3>Responsibilities</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="responsibilities" type="text" id="tworows1" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
            </tbody>
            <thead>
              <th><h3>Required Experiance</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="requiredExperiance" type="text" id="tworows3" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
            </tbody>
          </table>
          <table class="table table-striped table-responsive" id = "copy-tech-skill">
            <thead>
              <th><h3>Technical Skills Input</h3></th>
            </thead>
            <div class="readonly">
            <tbody>
              <thead>
                <th><h4>Input Name of The Skill</h4></th>
                <th><h4>Select a Category</h4></th>
                <th><h4>Select a required level</h4></th>
                <th colspan="3"><h4>Select an importance</h4></th>
              </thead>
              <c:forEach items="${vacancy.technicalSkills}" var="technicalSkill">
                <tbody>
                <td><input id="submitedTechSkillName" value = "${technicalSkill.name}" class="col-lg-8 techSkillName" readonly/></td>
                <td><select id="submitedTechSkillCategory" class="form-control techSkillSelect" disabled>
                      <option class="techSkillOption" value = "${technicalSkill.category}" label="${technicalSkill.category}"/>
                    </select></td>
                <td><select id="submitedTechSkillLevel" class="form-control techSkillSelect" disabled>
                      <option class="techSkillOption" value="${technicalSkill.level}" label="${technicalSkill.level}"/>
                    </select></td>
                <td><select id="submitedTechSkillImportance" class="form-control techSkillSelect" disabled>
                          <option value="${technicalSkill.importance}" label="${technicalSkill.importance}"/>
                        </select></td>
                <td><input id="add-tech-skill" class="add-tech-skill hidden-visibility" type="button" value="Save Skill" onclick="SendTechSkill()"></td>
                <td><input id="delete-tech-skill" class="delete-tech-skill shown-visibility" type="button" value="Delete Skill" onclick="DeleteTechSkill(this)"></td>
              </tbody>
              </c:forEach>
              <tbody>
                <td><input id="techSkillName" class="col-lg-8 techSkillName"/></td>
                <td><select id="techSkillCategory" class="form-control techSkillSelect" >
                      <option value="TECHNOLOGIES" label="Technologies"/>
                      <option value="LANGUAGES" label="Languages"/>
                      <option value="FRAMEWORKS" label="Frameworks"/>
                      <option value="DATABASES" label="Databases"/>
                      <option value="TOOLS" label="Tools"/>
                      <option value="PROTOCOLS" label="Protocols"/>
                      <option value="LIBRARIES" label="Libraries"/>
                      <option value="TECHNIQUES" label="Techniques"/>
                    </select></td>
                <td><select id="techSkillLevel" class="form-control techSkillSelect" >
                      <option value="BASIC" label="Basic"/>
                      <option value="AVERAGE" label="Average"/>
                      <option value="STRONG" label="Strong"/>
                      <option value="EXPERT" label="Expert"/>
                    </select></td>
                <td><select id="techSkillImportance" class="form-control techSkillSelect">
                          <option value="REQUIRED" label="Required"/>
                          <option value="ADDITIONAL" label="Additional"/>
                        </select></td>
                <td><input id="add-tech-skill" class="add-tech-skill" type="button" value="Save Skill" onclick="SendTechSkill()"></td>
                <td><input id="delete-tech-skill" class="delete-tech-skill" type="button" value="Delete Skill" onclick="DeleteTechSkill(this)"></td>
              </tbody>
              </tbody>
            </div>
          </table>

          <table class="table table-striped table-responsive" id = "copy-com-skill">
            <thead>
              <th><h3>Communication Skills Input</h3></th>
            </thead>
            <div class="readonly">
            <tbody>
              <thead>
                <th><h4>Select a Language name</h4></th>
                <th><h4>Select a required level</h4></th>
                <th colspan="4"><h4>Select an importance</h4></th>
              </thead>
              <c:forEach items="${vacancy.communicationSkills}" var="communicationSkill">
                <tbody>
                  <td><select id="submitedComSkillName" class="form-control comSkillSelect" disabled>
                        <option value="${communicationSkill.name}" label="${communicationSkill.name}"/>
                      </select></td>
                  <td><select id="submitedComSkillLevel" class="form-control comSkillSelect" disabled>
                        <option value="${communicationSkill.level}" label="${communicationSkill.level}"/>

                      </select></td>
                  <td><select id="submitedComSkillImportance" class="form-control comSkillSelect" disabled>
                            <option value="${communicationSkill.importance}" label="${communicationSkill.importance}"/>
                      </select></td>
                  <td><input id="add-com-skill" class="add-com-skill hidden-visibility" type="button" value="Save Skill" onclick="SendComSkill()"></td>
                  <td><input id="delete-com-skill" class="delete-com-skill shown-visibility" type="button" value="Delete Skill" onclick="DeleteComSkill(this)"></td>
                </tbody>
              </c:forEach>

              <tbody>
                <td><select id="comSkillName" class="form-control comSkillSelect">
                      <option value="ENGLISH" label="English"/>
                      <option value="GERMAN" label="German"/>
                      <option value="FRENCH" label="French"/>
                      <option value="SPANISH" label="Spanish"/>
                      <option value="RUSSIAN" label="Russian"/>
                      <option value="UKRAINIAN" label="Ukrainian"/>
                      <option value="POLISH" label="Polish"/>
                      <option value="CHINEESE" label="Chineese"/>
                      <option value="JAPANEESE" label="Japaneese"/>
                    </select></td>
                <td><select id="comSkillLevel" class="form-control comSkillSelect" >
                      <option value="BEGINER" label="Beginer"/>
                      <option value="ELEMENTARY" label="Elementary"/>
                      <option value="INTERMEDIATE" label="Intermediate"/>
                      <option value="UPPER_INTERMEDIATE" label="Upper Intermediate"/>
                      <option value="ADVANCED" label="Advanced"/>
                      <option value="PROFICIENT" label="Proficient"/>
                    </select></td>
                <td><select id="comSkillImportance" class="form-control comSkillSelect">
                          <option value="REQUIRED" label="Required"/>
                          <option value="ADDITIONAL" label="Additional"/>
                        </select></td>
                <td><input id="add-com-skill" class="add-com-skill" type="button" value="Save Skill" onclick="SendComSkill()"></td>
                <td><input id="delete-com-skill" class="delete-com-skill" type="button" value="Delete Skill" onclick="DeleteComSkill(this)"></td>
              </tbody>
              </tbody>
            </div>
          </table>

          <table class="table table-striped table-responsive" id = "copy-pers-skill">
            <thead>
              <th><h3>Personal Skills Input</h3></th>
            </thead>
            <div class="readonly">
            <tbody>
              <thead>
                <th><h4>Select a Name of The Skill</h4></th>
                <th colspan="3"><h4>Select a required level</h4></th>
              </thead>
              <c:forEach items="${vacancy.personalSkills}" var="personalSkill">
                <tbody>
                  <td><select id="submitedPersSkillName" class="form-control persSkillSelect" disabled>
                        <option class="techSkillOption" value="${personalSkill.personalSkillName}" label="${personalSkill.personalSkillName}"/>
                      </select></td>
                  <td><select id="submitedPersSkillLevel" class="form-control persSkillSelect" disabled>
                        <option value="${personalSkill.personalSkillLevel}" label="${personalSkill.personalSkillLevel}"/>
                      </select></td>
                  <td><input id="add-pers-skill" class="add-pers-skill hidden-visibility" type="button" value="Save Skill" onclick="SendPersSkill()"></td>
                  <td><input id="delete-pers-skill" class="delete-pers-skill shown-visibility" type="button" value="Delete Skill" onclick="DeletePersSkill(this)"></td>
                </tbody>
              </c:forEach>
              <tbody>
                <td><select id="persSkillName" class="form-control persSkillSelect">
                      <option value="OPENESS_TO_EXPERIENCE" label="Openess to experiance"/>
                      <option value="CONSCIENTIOUSNESS" label="Conscientiosness"/>
                      <option value="EXTRAVERSION" label="Extraversion"/>
                      <option value="AGREEABLENESS" label="Agreeableness"/>
                      <option value="TEUROTICISM" label="Teuroticism"/>
                      <option value="TEAMWORK" label="Tamwork"/>
                      <option value="INITIATIVE" label="Initiative"/>
                      <option value="FLEXIBILITY" label="Flexibility"/>
                      <option value="TIME_MANAGEMENT" label="Time management"/>
                      <option value="DECISION_MAKING" label="Decision making"/>
                      <option value="CREATIVITY" label="Creativity"/>
                      <option value="TRUSTWORTHINESS" label="Trustworthiness"/>
                      <option value="EMOTIONAL_STABILITY" label="Emotional stability"/>
                    </select></td>
                <td><select id="persSkillLevel" class="form-control persSkillSelect" >
                      <option value="MINIMUM" label="Minimum"/>
                      <option value="AVERAGE" label="Average"/>
                      <option value="MAXIMUM" label="Maximum"/>
                    </select></td>
                <td><input id="add-pers-skill" class="add-pers-skill" type="button" value="Save Skill" onclick="SendPersSkill()"></td>
                <td><input id="delete-pers-skill" class="delete-pers-skill" type="button" value="Delete Skill" onclick="DeletePersSkill(this)"></td>
              </tbody>
              </tbody>
            </div>
          </table>

          <table class="table table-striped table-responsive">
            <thead>
              <th colspan="2"><h3>Required Education</h3></th>
            </thead>
            <tbody>
              <thead>
                <th><h4>Select an Education Field</h4></th>
                <th><h4>Select an Education Degree</h4></th>
              </thead>
              <tbody>
                <td><form:select path="educationField" class="form-control" >
                      <form:option value="AUTOMATION_AND_MACHINE_CONSTRUCTING" label="Automation and Machine Constructing"/>
                      <form:option value="ELECTRONICS_AND_TELECOMMUNICATIONS" label="Electronics and Telecommunications"/>
                      <form:option value="SYSTEM_ANALYSIS" label="System Analysis"/>
                      <form:option value="COMPUTER_SCIENCE" label="Computer Science"/>
                      <form:option value="SOFTWARE_ENGINEERING" label="Software Engineering"/>
                      <form:option value="SYSTEM_ARCHITECTURE" label="System Archotecture"/>
                      <form:option value="PROJECT_MANAGEMENT" label="Project Management"/>
                      <form:option value="SYSTEM_PROGRAMMING" label="System Programming"/>
                      <form:option value="METROLOGY_AND_MEASURING_EQUIPMENT" label="Metrology and Measuring Equipment"/>
                      <form:option value="INFORMATION_TECHNOLOGY" label="Information Technology"/>
                      <form:option value="INFORMATION_AND_COMMUNICATION_SYSTEMS_SECURITY" label="Information and Communication Systems Security"/>
                      <form:option value="INFORMATION_SECURITY_MANAGEMENT" label="Information Security Management"/>
                      <form:option value="QUALITY_STANDARTIZATION_AND_CERTIFICATION" label="Quallity Standartization and Certification"/>
                      <form:option value="APPLIED_MATHEMATICS" label="Applied Mathematics"/>
                      <form:option value="APPLIED_PHYSICS" label="Applied Physics"/>
                      <form:option value="MATHEMATICAL_AND_COMPUTER_MODELING" label="Mathematical and Computer Modeling"/>
                    </form:select></td>
                <td><form:select path="educationDegree" class="form-control" >
                      <form:option value="ASSOCIATE" label="Associate"/>
                      <form:option value="BACHELOUR" label="Bachelour"/>
                      <form:option value="MASTER" label="Master"/>
                      <form:option value="PHD" label="Phd"/>
                      <form:option value="DOCTOR" label="Doctor"/>
                    </form:select></td>
              </tbody>
            </tbody>
          </table>

          <table class="table table-striped table-responsive">
            <thead>
              <th><h3>Please provide information about benefits working with you</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="employerOffer" type="text" id="tworows4" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
            </tbody>
          </table>

          <input id="submit-result"  type="submit" value="Save and Post">
        </form:form>
      </div>
    </div>
  </div>
  <%@ include file="../../jspf/footer.jspf"%>
</body>
<!-- Function for Making rows in tex areas, making url for request(skills) -->
<script src="resources/js/add-vacancy.js"></script>
