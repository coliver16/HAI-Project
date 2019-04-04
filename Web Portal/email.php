<head>
  <?php
  //For a nice background on our page, to continue soothing the victim
  //need to change path when put onto server, this is my local path
  include_once('/Users/coliver16/School/Spring 2019/CIS 454/Web Portal/background.html');
  ?>
</head>

<?php
//Connect to database (see connect.php to replace db credentials)
require('connect.php');

//Check for form submission
if (isset($_POST['email']) and isset($_POST['password']))
{
   //Assigning submitted values to variables
   $email = $_POST['email'];
   $password = $_POST['password'];

   //Query database to confirm proper credentials
   $query = "SELECT * FROM `users` WHERE email='$email' and password='$password'";

   $result = sqlserv_query($conn, $query) or die(sqlserv_error($conn));
   $count = sqlserv_num_rows($result);

   //If credentials are valid, email is sent
   if ($count >= 1){
     //Build query to select items from each table belonging to given user
     //Once we have proper tables setup, need a join statement for each table
     //queried, in order to ensure the items belong to given userˇ
     $query="SELECT * FROM kitchen_items
             JOIN bedroom_items
             ON kitchen_items.user_email = bedroom_items.user_email
             JOIN dining_items
             ON dining_items.user_email = kitchen_items.user_email
             WHERE kitchen_items.user_email = '$email'";

     //Execute query
     $result=sqlserv_query($conn,$query);

     //Extract data from mysql_result object, ignoring emails and ending when null
     while($row = sqlsrv_fetch_array($result, SQLSRV_FETCH_ASSOC)
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

     //Use wordwrap() if lines are longer than 70 characters
     $msg = wordwrap($msg,70);

     //Send email
     mail($email,"Your insurance list",$msg);

     //Alert box with success message
     echo "<script type ='text/javascript'>
     window.alert('Email Sent!')
     </script>";

   }
   else
   {
     //Error for invalid credentials + redirect to login
     echo "<script type ='text/javascript'>
       var answer = window.alert('Invalid Credentials, Please Try again')
       window.location = '../index.html'
         </script>";
    }
  }
?>
