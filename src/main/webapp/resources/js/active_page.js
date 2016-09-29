

$( document ).ready(function() {
  $('.navbar-inverse .navbar-nav>li>a').each(function(){
      var path = window.location.href;
      var current = path.substring(path.lastIndexOf('/')+1);
      var url = $(this).attr('href');
      var urlNow = url.substring(url.lastIndexOf('/')+1);


      if((urlNow == current)){
          $(this).addClass('activeButtom');
        }else if(current == "cand_regi"){
          $("#register").addClass('activeButtom');
        }else if(current == "emp_regi"){
          $("#register").addClass('activeButtom');

}



  });
});
