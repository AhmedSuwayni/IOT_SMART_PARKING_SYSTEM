<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>PARKING Area Manegment</title>

<link rel="stylesheet" type="text/css" href="css/index.css">

<?php
error_reporting(0);
include('connect.php');
session_start();
   $n1=  $_SESSION['idworkfile']  ;
$sql="SELECT * FROM admin WHERE ID= '$n1'";

$result = $conn->query($sql);

while($row = $result->fetch_assoc()) {


}
?>
	
</head>
<body>
	<center>
<h1 >Parking Area Manegment</h1>



<pre>
<br>
<?php
if(isSet($_SESSION{'idworkfile'})){
	?>




<a class="btn" id="id1" href="data.php?q1=">Parking Area History</a>



<a class="btn" id="id1" href="adduser.php?q1=">Add New Admin</a>



<a class="btn" id="id1" href="login.php" >Log Out</a>

   


   



   

</pre>

<?php
}

else{?>


              <a class="btn" id="id1" href="login.php" >Log in</a>


<?php
}
?>





<br>

</center>

 
</body>
</html>