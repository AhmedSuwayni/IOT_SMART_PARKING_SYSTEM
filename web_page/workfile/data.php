<!DOCTYPE html>
<html>
<head>
	<title>PARKING DATA</title>
	<style type="text/css">
	table{
   border-collapse: collapse;
   width: 100%;
   color: #ff8000;
   font-family: monospace;
   font-size: 25px;
   text-align: left;

	}	
	th {
      background-color: #ff8000;
      color: white;


	}


   tr:nth-child(even) {background-color: #ededed}
	</style>


</head>

<body>
<table>
	<tr>
	<th>Parking Area ID</th>
	<th>Parking Spot ID</th>
	<th>Parking Status</th>	
	<th>Parking Date</th>	
	</tr>
<?php
$conn = mysqli_connect("localhost", "root", "", "u197976357_carparking");
$sql = "SELECT * FROM parkingspotsstate";
$result = $conn->query($sql);


if ($result->num_rows > 0){

while ($row = $result-> fetch_assoc()){

	echo "<tr><td>" . $row["PID"] . "</td><td>" . $row["SID"] . "</td><td>" . $row["New State"] . "</td><td>" . $row["Date"] . "</td></tr>";
}
} 
else{

	echo "No Results";
}

$conn->close();
?>

</table>




</body>
</html>