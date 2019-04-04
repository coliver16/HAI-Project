<?php
  //Background image; local path, needs to be changed depending on machine
  include_once('/Users/coliver16/School/Spring 2019/CIS 454/Web Portal/background.html');

  //Connect to database (see connect.php to replace db credentials)
  require('connect.php');

  //Check for form submission
  if (isset($_POST['email']))
  {
    //Assigning submitted value to variable
    $email = $_POST['email'];

    //Query database to confirm email exists
    $query = "SELECT * FROM `users` WHERE email='$email' and password='$password'";

    $result = sqlserv_query($conn, $query) or die(sqlserv_error($conn));
    $count = sqlserv_num_rows($result);

    //If email exists, email sends
    if ($count >= 1)
    {
      //Set beginning of message, to have password added
      $msg = "Your password: ";

      //Extract data from mysql_result object, ignoring emails and ending when null
      while($row = sqlserv_fetch_row($result))
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
    window.location = '../index.html'
    </script>";

  }
  else
  {
    //Error for invalid credentials + redirect to login
    echo "<script type ='text/javascript'>
      var answer = window.alert('Invalid Credentials, Please Try again')
      window.location = '../forgotPass.html'
        </script>";
  }
  ?>
