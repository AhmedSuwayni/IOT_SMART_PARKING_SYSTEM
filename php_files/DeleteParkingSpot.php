<?php

include 'DatabaseConfig.php';
	
       
        $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
            
	    	$SID = $_POST['SID'];
          
		
		$query = "Select * from ParkingSpot where SID='$SID';";
			$result = mysqli_query($con, $query);
			if(mysqli_num_rows($result)>0){
            $Sql_Query = "UPDATE `ParkingSpot` SET `SID` = '$SID' ,`Column`='0', `Row`='0', `isEmpty`= 0 , `PID`='0' WHERE `SID` = '$SID';";

		
			if(mysqli_query($con,$Sql_Query)){
			    
			 
                           $json['success'] = "The Parking Spot has been Deleted";
                           echo json_encode($json);
			}
			}else{
				
					$json['error'] = 'You Have Entered Wrong Parking Spot ID ,You Can Find it On Sensor Information Label';
				echo json_encode($json);
				mysqli_close($this->connection);
			}
		
	?>