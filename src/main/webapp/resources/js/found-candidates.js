var personalSkills = {
	OPENESS_TO_EXPERIENCE : "Openess to Experience", 
	CONSCIENTIOUSNESS : "Conscientiousness",
	EXTRAVERSION : "Extraversion", 
	AGREEABLENESS : "Agreeableness", 
	TEUROTICISM : "Teuroticism",
	TEAMWORK : "Teamwork",
	INITIATIVE : "Initiative",
	FLEXIBILITY : "Flexibility",
	TIME_MANAGEMENT : "Time Management",
	DECISION_MAKING : "Decision Making",
	CREATIVITY : "Creativity",
	TRUSTWORTHINESS : "Trustworthiness",
	EMOTIONAL_STABILITY : "Emotional Stability"
};

$(".found-pers-skill-name").each(function(){$(this).attr("value", personalSkills[$(this).val()])});

var directions = {
	ADMINISTRATION : "Admanastration",
	BUSINESS_ANALYSIS: "Business Analysis",
	DESIGN : "Design",
	DATABASES : "Databases",
	PROJECT_MANAGEMENT : "Project Management",
	QUALITY_CONTROL : "Quality Control",
	SOFTWARE_DEVELOPMENT : "Software Development",
	SYSTEM_ARCHITECTURE : "System Architecture"
};

$(".found-inters-direct").each(function(){$(this).attr("value", directions[$(this).val()])});