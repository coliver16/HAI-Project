<head>
  <?php
  //For a nice background on our page, to continue soothing the victim
  include_once('background.html');
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
   $query = "SELECT * FROM Profile_454 WHERE profile_email='$email' AND profile_password='$password'";

   $result = sqlsrv_query($conn, $query) or die(print_r( sqlsrv_errors(), true));

   //If credentials are valid, email is sent
   if(sqlsrv_has_rows($result))
   {
     // SELECT * FROM kitchen_items
     //         JOIN bedroom_items
     //         ON kitchen_items.user_email = bedroom_items.user_email
     //         JOIN dining_items
     //         ON dining_items.user_email = kitchen_items.user_email
     //         WHERE kitchen_items.user_email = '$email'

     //Build query to select items from each table belonging to given user
     //Once we have proper tables setup, need a join statement for each table
     //queried, in order to ensure the items belong to given user
     $query="SELECT * FROM Item_454 WHERE email_own='$email'";

     //Execute query
     $result=sqlsrv_query($conn,$query);

     //Check Query
     if($result === false)
     {
     echo "Query Error.\n";
     die(print_r(sqlsrv_errors(), true));
     }

     //Define message variable
     $msg = "Your insurance list:\n";

     //Extract data from associative array, ignoring emails
     while($row = sqlsrv_fetch_array($result, SQLSRV_FETCH_ASSOC))
     {
       $msg = $msg . "Type: " . $row['item_type'] . " " . "Price: " . $row['item_price'] . "\n";
       /* Previously used parsing method
       $i = 0;
       while($row[$i] != NULL)
       {
         if($row[$i] != $email)
         {
           $i++;
         }
         else { $i++; }
       }*/
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
   //Error for invalid credentials + redirect to login
   else
   {
     echo "<script type ='text/javascript'>
       var answer = window.alert('Invalid Credentials, Please Try again')
       window.location = 'index.html'
         </script>";
   }
   // Used to debug
   // else
   // {
   //   {
   //       if( ($errors = sqlsrv_errors() ) != null)
   //       {
   //           foreach( $errors as $error )
   //           {
   //               echo "SQLSTATE: ".$error[ 'SQLSTATE']."<br />";
   //               echo "code: ".$error[ 'code']."<br />";
   //               echo "message: ".$error[ 'message']."<br />";
   //           }
   //       }
   //   }
   // }
  }
?>
