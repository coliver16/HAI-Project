<?php
//Server name
$serverName = "haiproject.database.windows.net";

//Array containing db info
$connOptions = array
(
  "Database" => "Inventory",
  "Uid" => "haiadmin",
  "PWD" => "HAIpassw0rd"
);

//Establish connection
$conn = sqlsrv_connect($serverName, $connOptions) or DIE("Failed to connect to database");

//Doublecheck connection
if($conn === false){ die( print_r( sqlsrv_errors(), true)); }
?>
