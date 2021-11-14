<?php

include 'DatabaseConfig.php';
	
       
        $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
            
	    	$PID = $_POST['PID'];
          
    
      $result = mysqli_query($con,"SELECT * FROM `ParkingSpotsState` WHERE `PID` = '$PID'");
        $json_data = array();
        while($row = mysqli_fetch_assoc($result)){
            $json_data[] = $row;
        
        }
    
    echo json_encode($json_data);
   
		
		
?>