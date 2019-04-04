<?php



$serverName = "haiproject.database.windows.net";

$connectionOptions = array(

        "Database" => "Inventory",

        "Uid" => "haiadmin",

        "PWD" => "HAIpassw0rd"

                );



$conn = sqlsrv_connect($serverName, $connectionOptions) or DIE("Failed to connect to database");



if ($conn)->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


?>
