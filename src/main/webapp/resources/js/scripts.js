    $( function() {
      try{
      $( "#dialog" ).dialog();
      setTimeout(function(){
    	  $( "#dialog" ).dialog('close');
       }, 4000);
     }catch(err){
       console.log("no dialogs..");
     }
    });
