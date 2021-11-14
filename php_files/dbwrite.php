<?php

    $host = "localhost";				// localhost
    $dbname = "u197976357_CarParking";		// Database name
    $username = "u197976357_root";		// Database username
    $password = "YsAQ2[#]6&";		// Database password


$conn = new mysqli($host, $username, $password, $dbname);
$con=mysqli_connect("localhost","u197976357_root","YsAQ2[#]6&","u197976357_CarParking");


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

else { echo "Connected to mysql database. "; }


    
// If values send by NodeMCU are not empty then insert into MySQL database table

  if(!empty($_POST['sendparking1']) && !empty($_POST['sendparking2']) && !empty($_POST['sendparking3']) && !empty($_POST['sendparking4']) && !empty($_POST['sendparking5']) && !empty($_POST['sendparking6']) )
    {
		$parking1 = $_POST['sendparking1'];
                $parking2 = $_POST['sendparking2'];
					$parking3 = $_POST['sendparking3'];
						$parking4 = $_POST['sendparking4'];
							$parking5 = $_POST['sendparking5'];
								$parking6 = $_POST['sendparking6'];

	       // mysqli_query($con,"UPDATE nodemcu_table SET parking1 = $parking1, parking2 = $parking2, parking3 = //$parking3, parking4 = $parking4, parking5 = $parking5, parking6 = $parking6 WHERE id=99999");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking1 WHERE SID=1");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking2 WHERE SID=2");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking3 WHERE SID=3");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking4 WHERE SID=4");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking5 WHERE SID=10");;
	         mysqli_query($con,"UPDATE ParkingSpot SET isEmpty= $parking6 WHERE SID=11");;
 	}

$conn->close();
$con->close();


?>
