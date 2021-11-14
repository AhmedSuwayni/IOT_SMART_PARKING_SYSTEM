<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<br>
	<title>log in</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<style type="text/css">

	<title>log in</title>

<style type="text/css">
div{
	top: 310px;
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
<?php
session_start();
session_unset();
session_destroy();





?>
</head>
<body>
	<center>
		
	<div class="container">

		<br>
		<br>
	    <br>
		<br>
		<br>
		<br>
		<br>

		<img src="img/user.png">
		<h1 class="haa">Log in</h1>
<form action="fun/logintest.php" method="get">
<input type="" name="n1" placeholder="Phone number">	
<br>
<br>

<input type="password" name="n2"placeholder="Password">	
<br>

<br>
<input type="SUBMIT" name="">
</form>

<a href=""></a>



</center>
</body>
</html>