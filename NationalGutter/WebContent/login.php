<?php
    if(!empty($_POST['uname'])&&!empty($_POST['pass'])):
    echo $_POST['unName'];
    die();
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
  <br>
		<h1>Employee login</h1>
			<form action="login.php" method="post">
				
				<input type ="text" placeholder="User Name" name = "uName" required>
				<input type ="passWord" placeholder="Password" name="pass" required>
				<input type = submit>
			</form>
	</body>
</html>