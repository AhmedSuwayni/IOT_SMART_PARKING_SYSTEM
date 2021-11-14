<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Add New Admin</title>
	<link rel="stylesheet" type="text/css" href="css/adduser.css">
	<style type="text/css">
div{
	top: 10px;
position: relative;
background:#ffa500;
width: 100%;
height: 100%;

}
input{

	position: relative;
	

}
label{
	
position: relative;



}
}
</style>
</head>
<body>
	<center>
<div class="container">
	<pre>
<label>Add New Admin</label>
	<?php
	$q1=$_GET['q1'];
	if($q1 == 1){
		echo "Used Phone Number";
	}
	if($q1 == 2){
		echo "Password does not match";
	}



	?>
<form action="fun/adduser.php" method="get">	

<label>Name</label>
<input type="" name="name"> 

<label>  Phone Number</label>
<input type="" name="ID">
	
<label>  Password</label>
<input type="password" name="Password">
	
<label>  Confirm Password</label>
<input type="password" name="Password2">

	
<input type="SUBMIT" name="">




</form>
</pre>


</center>
</body>
</html>