package items;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.auth.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;

public class S3 {
    //Establish Credentials to connect to our S3 storage
    AWSCredentials creds = new BasicAWSCredentials("AKIAIYFKLSDUVF4A7LPQ","eW+3TrHGlcV1NqR6f2xnFTSQ7sU3DtpBo6PKWk2R");
    //Creates an AmazonS3 object with our S3 credentials to create a connection
    AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.US_EAST_2).build();

    //Only have 1 bucket in our S3 that contains folders for each user
    private String bucketName = "cis454project2";

    //A test method to List the Buckets in our S3
    public void listBuckets()
    {
        List<Bucket> buckets = s3.listBuckets(); //Create a list of buckets in our S3
        {for(Bucket bucket: buckets) //iterate through all buckets in the list
        {
            System.out.println(bucket.getName()); //print out the bucket name in each iteration
        }
        }
    }

    //A method to upload Objects(images, files, etc) into a specific area of our bucket
    public void putObject(String key, String pathname)
    {
        //This uploads an object to our 1 bucket, with the name key.
        //The third parameter is the actual local location of the object to be uploaded
        { s3.putObject(new PutObjectRequest(bucketName, key, new File(pathname))); }
    }

    //A test method to List the Objects in our S3 bucket
    public void listObjects()
    {
        {
            ObjectListing objList = s3.listObjects(bucketName); //creates a list of objects in our 1 bucket
            for (S3ObjectSummary objSum : objList.getObjectSummaries()) //iterates through each object in list
            {
                System.out.println(objSum.getKey()); //prints out the name of the object
            }
        }
    }

    //Delete Object from the bucket
    public void deleteObject(String folder, String key)
    {
        { s3.deleteObject(folder, key); }
    }

    //Create Folder, used when a new profile is created so when they upload images they have a place to store them
    public void createFolder (String foldName)
    {
        //Creates an empty directory
        ObjectMetadata md = new ObjectMetadata();
        md.setContentLength(0);
        InputStream emp = new ByteArrayInputStream(new byte[0]);
        //Upload this directory named foldName (which is the user's email)
        PutObjectRequest p = new PutObjectRequest(bucketName, foldName + "/", emp, md);
        s3.putObject(p);
    }

    //Download Objects so images can be seen on right side when Items Menu
    public void downloadObject (String filepath, String key)
    {
        if (!s3.doesObjectExist(bucketName, key)) //checks if object does not exist in the S3, if so do nothing. Meaning user never uploaded an image
        {
            return;
        }
        else
        {
            //creates new local file with proper name
            File download = new File("src\\local\\images\\" + filepath);
            ObjectMetadata obj = new ObjectMetadata();
            //takes S3 object and places it in the newly created local file
            obj = s3.getObject(new GetObjectRequest(bucketName, key), download);
        }
    }
}
