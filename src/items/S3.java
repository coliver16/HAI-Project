package items;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.auth.*;
import java.io.File;
import java.util.List;

public class S3 {
    AWSCredentials creds = new BasicAWSCredentials("AKIAIYFKLSDUVF4A7LPQ","eW+3TrHGlcV1NqR6f2xnFTSQ7sU3DtpBo6PKWk2R");
    AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.US_EAST_2).build();

    private String bucketName = "cis454project2";
    private String key;
    private String pathname;

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
    public void deleteObject(String key)
    {
        { s3.deleteObject(bucketName, key); }
    }
}
