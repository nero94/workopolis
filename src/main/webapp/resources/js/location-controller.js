var xmlHttp1;
var xmlHttp;

var currentCountryName = "Select";
var currentCountryId = "-1";
var countryUnique1 = "";
var countryUnique2 = "";




  showCountry = function (countryId ,id, name) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/country";
   

    xmlHttp.onreadystatechange = countryChange;

    countryUnique1 = countryId;

    if (id && name){
    	currentCountryName = name;
    	currentCountryId = id;

    }

    xmlHttp.open("GET", url, true);

    xmlHttp.send(null);

}

function countryChange() {

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {


            $("#country").html(xmlHttp.responseText);
            $("#country option").first().html(currentCountryName);
            $("#country option").first().val(currentCountryId);

 

    }
}

function showState(str) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/state";

    url += "?country=" + str;

    xmlHttp.onreadystatechange = stateChange;
    

    xmlHttp.open("GET", url, true);

    xmlHttp.send(null);

}

function stateChange() {

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {

        $("#"+ countryUnique1 + " #state").html(xmlHttp.responseText);
    



    }
}

function showCity(str) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/city";

    url += "?state=" + str;

    xmlHttp.onreadystatechange = cityChange;

    xmlHttp.open("GET", url, true);
    

    xmlHttp.send(null);

}

function cityChange() {

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {

        $("#"+ countryUnique1 + " #city").html(xmlHttp.responseText);
    


    }
}





//FOR EDUCATION
showEducationCountry = function (countryId ) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp1 = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp1 = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp1 == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/ed_country";

    xmlHttp1.onreadystatechange = countryEducationChange;

    countryUnique2 = countryId;
    


    xmlHttp1.open("GET", url, true);

    xmlHttp1.send(null);

}

function countryEducationChange() {

    if (xmlHttp1.readyState == 4 || xmlHttp1.readyState == "complete") {


            $("#"+ countryUnique2 + " #country").html(xmlHttp1.responseText);


    

    }
}

function showEducationState(str) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp1 = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp1 = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp1 == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/ed_state";

    url += "?country=" + str;

    xmlHttp1.onreadystatechange = stateEducationChange;

    xmlHttp1.open("GET", url, true);
    

    xmlHttp1.send(null);

}

function stateEducationChange() {

    if (xmlHttp1.readyState == 4 || xmlHttp1.readyState == "complete") {

        $("#"+ countryUnique2 + " #educationState").html(xmlHttp1.responseText);



    }
}

function showEducationCity(str) {

    if (typeof XMLHttpRequest != "undefined") {

        xmlHttp1 = new XMLHttpRequest();

    } else if (window.ActiveXObject) {

        xmlHttp1 = new ActiveXObject("Microsoft.XMLHTTP");

    }
    if (xmlHttp1 == null) {

        alert("Browser does not support XMLHTTP Request")

        return;
    }

    var url = "/location/ed_city";

    url += "?state=" + str;

    xmlHttp1.onreadystatechange = cityEducationChange;

    xmlHttp1.open("GET", url, true);
    

    xmlHttp1.send(null);

}

function cityEducationChange() {

    if (xmlHttp1.readyState == 4 || xmlHttp1.readyState == "complete") {

        $("#"+ countryUnique2 + " #educationCity").html(xmlHttp1.responseText);

    

    }
}
