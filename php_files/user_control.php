<?php

include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($ID,$Password)
		{
			$query = "Select * from Admin where ID='$ID' and Password = '$Password' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$json['success'] = ' Welcome '.$ID;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}else{
				
					$json['error'] = 'You Have Entered Wrong Credentials';
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['ID'],$_POST['Password'])) {
		$ID = $_POST['ID'];
		$Password = $_POST['Password'];
		
		if(!empty($ID) && !empty($Password)){
			
			$encrypted_Password = md5($Password);
			$user-> does_user_exist($ID,$Password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}














?>