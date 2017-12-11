<?php  
/*
*Php script to verify user name and password and give access to restricted parts of our site
* Written by: Dylan Kennedy
*/

//Start session
session_start();

//Get DataBase Connection
require 'database.php';

//Fields are not empty
if(!empty($_POST['uName']) && !empty($_POST['pass'])):

//Stores record from database
$records = $conn->prepare('SELECT id,user,password FROM users WHERE user = :user' );
$records->bindParam(':user', $_POST['user']);
$records->execute();
//Fetch results from database
$results = $records->fetch(PDO::FETCH_ASSOC);
$message = '';

//query our data base for a user
if(count($results) > 0 && password_verify($_POST['pass'])){

  //Opening a session
  $SESSION['Identity'=results[id]];

  //Move me to employees only section of site
  header("Location:/NationalGutter/EmployeesOnly.php")
}else{
  $message ="User not found";
}

//Ending our if statement
endif;


?>
<!DOCTYPE html>
<html>
<header>
    <div class="container">
      <h1 class="logo"></h1>
      <!-- This is pointing to a file stored on your local machine  -->
      <img src="file:///Users/ZachMcKEE/Desktop/Company_Logo.png" alt="Logo" />

      <nav>
        <ul>
          <li><a href="Main.html">Home</a></li>
          <li><a href="About.html">About</a></li>
          <li><a href="Contact.html">Contact Us</a></li>
          <li><a href ="login.php">Login</a>
        </ul>
      </nav>
    </div>
  </header>
  <?php
    if(!empty($message)):?>
    <p><?=$message ?><p>
    <?php endif; ?>
  <br>
		<h1>Employee login</h1>
			<form action="login.php" method="POST">

				<input type ="text" placeholder="User Name" name = "uName" required>
				<input type ="passWord" placeholder="Password" name="pass" required>
				<input type = submit>
			</form>
	</body>
</html>
