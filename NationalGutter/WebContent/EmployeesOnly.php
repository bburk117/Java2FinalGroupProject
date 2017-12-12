<?php
/* Php script for our employees only section of the site
 * Written by: Dylan Kennedy
 */
session_start();
require 'database.php';

if(isset($_SESSION['Identity'])){
	$records = $conn->prepare('SELECT id,user,password FROM users WHERE id = :id' );
	records->bindParam(':id',$_SESSION['Identity']);
	records->execute();
	$results = $records->fetch(PDO::FETCH_ASSOC);
	$user = NULL;

	if(count($results)>0){
		$user = $results;
	}

}
?>
<!DOCTYPE html>
<html>
<head>
	<title>Employees Only</title>
</head>

<link rel ="stylesheet" type = text/css href = "assets/style.css">

<header>
    <div class="container">
      <h1 class="logo"></h1>
      <!-- This is pointing to a file stored on your local machine  -->
      <img src="siteImages/Company_Logo.png" alt="Logo" />

      <nav>
        <ul>
          <li><a href="Main.html">Home</a></li>
          <li><a href="About.html">About</a></li>
          <li><a href="Contact.html">Contact Us</a></li>
          <li><a href ="logout.php">Logout</a>
        </ul>
      </nav>
    </div>
  </header>
  <br>
<?php if (!empty ($user)): ?>
<br/> Welcome <?=$user['user'];?>
<a href = "App.html">"Applications"</a>
<a href = "EmailForm.html">Auto Mailer</a>
<?php else:?>
	<h1>Please Login</h1>
	<a href="login.php">Login</a>
<?php endif; ?>
</html>