<?php

include 'DatabaseConfig.php';
	
       
        $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
            
	    	$ID = $_POST['ID'];
          
            $Sql_Query = "DELETE FROM `ParkingArea` WHERE ID = '$ID';";

            $query = "Select * from ParkingArea where ID='$ID';";
			$result = mysqli_query($con, $query);
			if(mysqli_num_rows($result)>0){
            $Sql_Query = "DELETE FROM `ParkingArea` WHERE ID = '$ID';";

		
			if(mysqli_query($con,$Sql_Query)){
			    
			 
                           $json['success'] = "The Parking Area has been Deleted";
                           echo json_encode($json);
			}
			}else{
				
					$json['error'] = 'You Have Entered Wrong Parking Area ID';
				echo json_encode($json);
				mysqli_close($this->connection);
			}

			
		
		
	?>