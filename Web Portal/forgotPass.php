<?php
  //Background image; local path, needs to be changed depending on machine
  include_once('background.html');

  //Connect to database (see connect.php to replace db credentials)
  require('connect.php');

  //Check for form submission
  if (isset($_POST['email']))
  {
    //Assigning submitted value to variable
    $email = $_POST['email'];

    //Query database to confirm email exists
    $query = "SELECT * FROM Profile_454 WHERE profile_email='$email' and profile_password='$password'";

    $result = sqlserv_query($conn, $query) or die(sqlserv_error($conn));
    $count = sqlserv_num_rows($result);

    //If email exists, email sends
    if ($count >= 1)
    {
      //Set beginning of message, to have password added
      $msg = "Your password: ";

      //Extract data from mysql_result object, ignoring emails and ending when null
      while($row = sqlsrv_fetch_array($result, SQLSRV_FETCH_ASSOC))
      {
        $i = 0;
        while($row[$i] != NULL)
        {
          if($row[$i] != $email)
          {
            $msg = $msg . " " . $row[$i];
            $i++;
          }
          else { $i++; }
         }
      }
    }

    //Use wordwrap() if lines are longer than 70 characters
    $msg = wordwrap($msg,70);

    //Send email
    mail($email,"Your insurance list",$msg);

    //Alert box with success message
    echo "<script type ='text/javascript'>
    window.alert('Email Sent!')
    window.location = 'index.html'
    </script>";

  }
  else
  {
    //Error for invalid credentials + redirect to login
    echo "<script type ='text/javascript'>
      var answer = window.alert('Invalid Credentials, Please Try again')
      window.location = 'forgotPass.html'
        </script>";
  }
  ?>
