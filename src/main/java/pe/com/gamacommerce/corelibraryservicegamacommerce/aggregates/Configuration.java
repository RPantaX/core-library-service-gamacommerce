package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value("${BUCKET_USUARIOS_CLIENT_ID}")
    private String bucketUsuariosClientID;

    @Value("${BUCKET_USUARIOS_SECRET_KEY}")
    private String bucketUsuariosSecretKey;
    @Value("${AWS_S3_REGION}")
    private String region;
    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(bucketUsuariosClientID, bucketUsuariosSecretKey);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(credentials);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.fromName(region))
                .build();
    }

}
