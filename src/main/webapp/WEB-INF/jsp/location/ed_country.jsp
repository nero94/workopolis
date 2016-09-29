<%@page import="java.sql.*"%>

<%

 String buffer="<select class='form-control edSelect' id='countryEdSelect' name='country' onchange='showEducationState(this.value)'><option value='-1'>Select</option>";  

 try{
 Class.forName("com.mysql.jdbc.Driver").newInstance();  

 Connection con = DriverManager.getConnection("jdbc:mysql://bootcamp.edvantis.com:3306/golf_workapolis","golf","golf498Hed_2d2");  

 Statement stmt = con.createStatement();  

 ResultSet rs = stmt.executeQuery("Select * from country");  

 while(rs.next()){

	 buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(3)+"</option>";

 }
 
 buffer=buffer+"</select>";  

 response.getWriter().println(buffer); 

 }
 catch(Exception e){

     System.out.println(e);

 }

 %>