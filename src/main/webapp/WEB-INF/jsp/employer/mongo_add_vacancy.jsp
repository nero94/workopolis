<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.edvantis.workopolis.model.skill.TechnicalSkill"%>
<%@ page import="com.edvantis.workopolis.model.vacancy.Vacancy"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.LinkedHashSet"%>
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
        <h3 id="my-int" class="int-name">Create a Vacancy</h3>
      </div>
      <div class="col-lg-12 intervs well">
        <form:form action="/mongo_add_vacancy" method="post" modelAttribute="vacancy">
          <table class="table table-striped table-responsive">
            <thead>
              <th>Title</th>
              <th>Direction</th>
              <th>Location</th>
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
              <td><form:select path="location" id="pref-location" class="form-control col-lg-12" name="location">
               <option></option>
               <option>Ukraine</option>
               <option>USA</option>
               <option>Germany</option>
               <option>Egypt</option>
               <option>Israel</option>
               <option>Poland</option>
               <option>Brazil</option>
            </form:select></td>
              <td><form:select path="status" class="form-control" >
                    <form:option value="FULL_TIME" label="Full Time"/>
                    <form:option value="PART_TIME" label="Part Time"/>
                  </form:select></td>
              <td>
              <div class="input-group">
  				<span class="input-group-addon">$</span>
              <form:input type="number" path="salary"/>
              </div>
              </td>
            </tbody>
          </table>
          <table class="table table-striped table-responsive">
            <thead>
              <th><h3>General Description</h3></th>
            </thead>
            <tbody>
              <td><form:textarea path="description" type="text" id="tworows" class="col-lg-12 col-md-12  col-sm-12 col-xs-12"/></td>
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

          <input id="submit-result"  type="submit" value="Save and Post">
        </form:form>
      </div>
    </div>
  </div>
  <%@ include file="../../jspf/footer.jspf"%>
</body>
<!-- Function for Making rows in tex areas, making url for request(skills) -->
<script src="resources/js/mongo_add-vacancy.js"></script>
