<?php

//Server info
$serverName = "haiproject.database.windows.net";

//Database info
$connectionOptions = array(

        "Database" => "Inventory",

        "Uid" => "haiadmin",

        "PWD" => "HAIpassw0rd"

                );


//Create connection
$conn = sqlsrv_connect($serverName, $connectionOptions) or DIE("Failed to connect to database");


//Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

?>
