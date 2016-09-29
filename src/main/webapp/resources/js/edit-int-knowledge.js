 $(document).on('ready',function() {
	$(document).on('click','.add-int-knowledge',function(){
		$(".delete-int-knowledge").addClass("shown-visibility");
		$(".add-int-knowledge").removeClass("shown-visibility");
  		$(".add-int-knowledge").addClass("hidden-visibility");

  		$("#knowledgeCatefory").attr("id","submitedField");
  		
		$(".knowledgeSelect").attr("disabled", true);
		 $('#copy-knowledge-field').append('<div class="form-group">'+
		 				'<label for="knowledgField" class="col-lg-4 control-label">Add Knowledge Field</label>'+
                         '<div class="col-lg-4">'+
                           '<select id="knowledgeCatefory" class="form-control knowledgeSelect">'+
                              '<option value="TECHNICAL" label="Technical" />'+
                              '<option value="COMMUNICATION" label="Communication" />'+
                              '<option value="PERSONAL" label="Personal" />'+
                           '</select>'+
                         '</div>'+
                         '<input id="add-int-knowledge" class="add-int-knowledge shown-visibility" type="button" value="Add" onclick="AddKnowledgeField()">'+
                         '<input id="delete-int-knowledge" class="delete-int-knowledge hidden-visibility" type="button" value="Delete" onclick="DeleteKnowledgeField(this)">'+
                     '</div>'+
                     '</div>');
	});
	$(document).on('click','.delete-int-knowledge',function(){
  		$(this).parent().remove();
  	});

});

 function AddKnowledgeField(){
	var xhttp = new XMLHttpRequest();
	var url = "/interviewer/add_int_field?category=" + $("#knowledgeCatefory").val();
  	console.log(url);
	xhttp.open('GET', url, true);
	xhttp.send();
	}

function DeleteKnowledgeField(x){
	var scope = $(x).parents()[0];
  	var xhttp = new XMLHttpRequest();
  	var url = "/interviewer/delete_int_field?category=" + $(scope).find("#submitedField").val();
    console.log(url);
  	xhttp.open('GET', url, true);
  	xhttp.send();
  	}