<?php
session_start();

//Modular code ftw
require "database.php";

 $customerid = filter_input(INPUT_POST,'custid');
 $address = filter_input(INPUT_POST,'address');
 $city = filter_input(INPUT_POST,'city');
 $state = filter_input(INPUT_POST,'state');
 $zip = filter_input(INPUT_POST,'zip');
 $type = filter_input(INPUT_POST,'type');
 $notes = filter_input(INPUT_POST,'notes');
 $cost = filter_input(INPUT_POST,'cost');
 $date = filter_input(INPUT_POST,'date');
 if (!empty($customerid)){
if (!empty($address)){

if (mysqli_connect_error()){
	die('Connect Error ('. mysqli_connect_errno() .') '
	 . mysqli_connect_error());
}
else{
	$sql = "INSERT INTO jobs (custid, address, city, state, zip, type, notes, cost, date)
	values ('$customerid','$address','$city','$state','$zip','$type','$notes','$cost','$date')";
	if ($conn->query($sql)){
		echo "New record is inserted successfully";
	}
	else{
		echo "Error: ". $sql ."<br>". $conn->error;
	}
	$conn->close();
}
}
else{
	echo "Address should not be empty";
	die();
}
 }
 else{
	 echo "Customer ID should not be empty";
	 die();
 }
?>
