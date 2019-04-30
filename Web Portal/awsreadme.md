# AWS (Amazon Web Services) Use and Access

## How to Connect to EC2 Instance through PuTTy
	- Install PuTTY (includes PuTTYgen) if you haven't already here -> https://www.putty.org/
	### Open PuTTYgen
		- Click Load
		- Click file type drop down menu and select All Files option
		- Find the project2.pem in the repository file and select it
		- Click Save Private Key, and select Yes on the warning message
		- Save the .ppk file
	### Open PuTTY
		#### In Session
			- Host Name (or IP Address): ubuntu@ec2-18-217-117-132.us-east-2.compute.amazonaws.com
			- Port: 22
		#### In Connection->SSH->Auth	
			- Click Browse to find private key file (.ppk) you just created and select it
			- Click Open
			- If a warning message appears, click yes 
	### Once Connected
		- You can view our Web Portal using this command `cd /var/www/CIS_454_Project2/'Web Portal'`
		- Each user has their own folder within the bucket. You can view objects in our S3 bucket using the following commands
			- `aws s3 ls cis454project2` //Shows the contents of the bucket
			- `aws s3 ls cis454project2/folderName/` //Show contents of folderName 
