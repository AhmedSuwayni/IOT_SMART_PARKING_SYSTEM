<?php

include 'DatabaseConfig.php';
	
       
        $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
            $lat1 = $_POST['lat1'];
	    	$long1 = $_POST['long1'];
		
    		$lat2 = $_POST['lat2'];
    		$long2 = $_POST['long2'];
		
	    	$lat3 = $_POST['lat3'];
    		$long3 = $_POST['long3'];
		
	    	$lat4 = $_POST['lat4'];
	    	$long4 = $_POST['long4'];
	    	
	    	$Columns = $_POST['Columns'];
	    	$Rows = $_POST['Rows'];


            $Sql_Query = "INSERT INTO `ParkingArea` (`ID`, `lat1`, `long1`, `lat2`, `long2`, `lat3`, `long3`, `lat4`, `long4`,`Columns`,`Rows`) VALUES (NULL,'$lat1','$long1','$lat2','$long2','$lat3','$long3','$lat4','$long4','$Columns','$Rows');";

		
			if(mysqli_query($con,$Sql_Query)){
			    
			    $result = mysqli_query($con,"Select ID from ParkingArea where lat1='$lat1'");
                    $row = mysqli_fetch_array($result);
                    $data = $row[0];

                       if($data){
                           
                           $json['success'] = $data;
    				    }else{
    					$json['error'] = 'Some thing went Wrong';
        				}
            				echo json_encode($json);
   
			}
			
		
		
	?>