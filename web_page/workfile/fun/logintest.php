<?php
include('../connect.php');
$n1=$_GET['n1'];
$n2=$_GET['n2'];
$sql="SELECT * FROM admin WHERE ID= '$n1' AND Password= '$n2'";

$result = $conn->query($sql);

while($row = $result->fetch_assoc()) {

$count = $result->num_rows;
}
if ($count>0){

    session_start();
    $_SESSION['idworkfile'] = $n1 ;

	header('Location:http://localhost/workfile/index.php');
}

else{
	header('Location:http://localhost/workfile/login.php');
}

?>