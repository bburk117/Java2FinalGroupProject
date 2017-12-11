<?php
session_start();
require 'database.php';

 //How are your tables set up are they going to work with my code?
 $firstname = filter_input(INPUT_POST,'fname');
 $lastname = filter_input(INPUT_POST,'lname');
 $address = filter_input(INPUT_POST,'address');
 $city = filter_input(INPUT_POST,'city');
 $state = filter_input(INPUT_POST,'state');
 $zip = filter_input(INPUT_POST,'zip');
 $email = filter_input(INPUT_POST,'email');
 $phone = filter_input(INPUT_POST,'phone');

else{
	$sql = "INSERT INTO customers (fname, lname, address, city, state, zip, email, phone)
	values ('$firstname','$lastname','$address','$city','$state','$zip','$email','$phone')";
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
	echo "Last name should not be empty";
	die();
}
 }
 else{
	 echo "First name should not be empty";
	 die();
 }
?>
