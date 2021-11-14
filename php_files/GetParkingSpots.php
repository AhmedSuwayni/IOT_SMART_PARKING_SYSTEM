<?php
    
include 'DatabaseConfig.php';
    
    $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
    
    
    
    
$query = "SELECT * FROM `ParkingSpot`";
$res = mysqli_query($con,$query);
    $json_data = array();
    while($row = mysqli_fetch_assoc($res)){
        $json_data[] = $row;
        
    }

    echo json_encode($json_data);
   
    
?>