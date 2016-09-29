<%@page import="java.sql.*"%>

<%
String state=request.getParameter("state");  

 String buffer="<select name='city' class='form-control'><option value='-1'>Select</option>";  

 try{
 Class.forName("com.mysql.jdbc.Driver").newInstance();  

 Connection con = DriverManager.getConnection("jdbc:mysql://bootcamp.edvantis.com:3306/golf_workapolis","golf","golf498Hed_2d2");  

 Statement stmt = con.createStatement();  

 ResultSet rs = stmt.executeQuery("Select * from cities where state_id='"+state+"' ");  

   while(rs.next()){

   buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";  

   }  
 buffer=buffer+"</select>";  

 response.getWriter().println(buffer); 

 }
 catch(Exception e){

     System.out.println(e);

 }
 %>