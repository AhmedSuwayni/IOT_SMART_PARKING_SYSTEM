<?php
include('../connect.php');
$name=$_GET['name'];
$ID=$_GET['ID'];
$sql="SELECT * FROM admin WHERE ID= '$ID' ";

$result = $conn->query($sql);

while($row = $result->fetch_assoc()) {

$count = $result->num_rows;
}
if ($count>0){
    header('Location:http://localhost/workfile/adduser.php?q1=1');
    exit();
}

$Password=$_GET['Password'];
$Password2=$_GET['Password2'];

if($Password !== $Password2){

    header('Location:http://localhost/workfile/adduser.php?q1=2');

    exit();
}


$sql="INSERT INTO admin(name,ID,Password)
 VALUES('$name','$ID','$Password')";
$result = $conn->query($sql);

header('Location:http://localhost/workfile/index.php')

?>