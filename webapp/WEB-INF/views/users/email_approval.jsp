<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,java.text.*,java.sql.*,java.io.OutputStream,java.io.BufferedReader, java.io.InputStreamReader,java.net.HttpURLConnection,java.net.URL"%>

<html>
<head>
<meta charset="utf-8">
<title>승인</title>
</head>
<body>
<script language="javascript">

</script>
<%
request.setCharacterEncoding("utf-8");  //Set encoding
 String wiid=request.getParameter("wiid");
 String userId=request.getParameter("userId");
 String actionId=request.getParameter("actionId");
 String oldStateId=request.getParameter("oldStateId");
 
String param = "wiid="+wiid+"&actionId="+actionId+"&oldStateId="+oldStateId;
URL targetURL = new URL("http://sdh.net:8801/approval");

HttpURLConnection hurlc = (HttpURLConnection) targetURL.openConnection();

hurlc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
hurlc.setRequestMethod("POST");
hurlc.setDoOutput(true);
hurlc.setDoInput(true);
hurlc.setInstanceFollowRedirects(false); 

OutputStream opstrm = hurlc.getOutputStream();
opstrm.write(param.getBytes());
opstrm.flush();
opstrm.close();

String buffer = null;
BufferedReader in = new BufferedReader(new InputStreamReader(hurlc.getInputStream(),"UTF-8"));
while((buffer = in.readLine()) != null) {
	out.println(buffer);
}
in.close();


%>

</body>
</html>