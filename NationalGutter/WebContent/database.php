<?php  
$server='localhost'
//Define your user name and password
$username = "YOUR_USERNAME";
$password = "YOU_PASSWORD";
//Give me a database
$database = 'DATABASE';

//Try catch block to connect to our database
try {
    $conn = new PDO("mysql:host=$server;dbname=$database;",$username,$password);
} catch (PDOException $e) {
    die("Conneection failed:". $e->getMessage());
}
