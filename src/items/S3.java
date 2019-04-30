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
    AWSCredentials creds = new BasicAWSCredentials("AKIAIYFKLSDUVF4A7LPQ","eW+3TrHGlcV1NqR6f2xnFTSQ7sU3DtpBo6PKWk2R");
    AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.US_EAST_2).build();

    private String bucketName = "cis454project2";
    private String folder;

    //List Buckets
    public void listBuckets()
    {
        List<Bucket> buckets = s3.listBuckets();
        {for(Bucket bucket: buckets)
        {
            System.out.println(bucket.getName());
        }
        }
    }

    //Put Objects or Files
    public void putObject(String key, String pathname)
    {
        { s3.putObject(new PutObjectRequest(bucketName, key, new File(pathname))); }
    }

    //List Objects
    public void listObjects()
    {
        {
            ObjectListing objList = s3.listObjects(bucketName);
            for (S3ObjectSummary objSum : objList.getObjectSummaries())
            {
                System.out.println(objSum.getKey());
            }
        }
    }

    //Delete Object
    public void deleteObject(String folder, String key)
    {
        { s3.deleteObject(folder, key); }
    }

    //Create Folder
    public void createFolder (String foldName)
    {
        ObjectMetadata md = new ObjectMetadata();
        md.setContentLength(0);
        InputStream emp = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest p = new PutObjectRequest(bucketName, foldName + "/", emp, md);
        s3.putObject(p);
    }

    //Download Object
    public void downloadObject (String filepath, String key)
    {
        File download = new File("src\\local\\images\\" + filepath);
        ObjectMetadata obj = new ObjectMetadata();
        obj = s3.getObject(new GetObjectRequest(bucketName, key), download);
    }
}
