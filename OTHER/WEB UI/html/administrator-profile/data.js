$(document).ready(function(){
    data= [   { name:"first",
 data:[
          {value:150, date:"01/01/2016"},
          {value:200, date:"01/02/2016"},
          {value:320, date:"01/03/2016"},
          {value:430, date:"01/04/2016"},
		  {value:440, date:"01/04/2016"}
        
        ]
      },
      { name:"second",
      data:[
          {value:100, date:"01/01/2016"},
          {value:150, date:"01/02/2016"},
          {value:300, date:"01/03/2016"},
          {value:400, date:"01/04/2016"},
		  {value:400, date:"01/04/2016"}
        ]
      }
  ]
  // Multiple lines
  options={
    height: 150,
    width: 600,
  }
  $("#multi").pista(data, options);
    });