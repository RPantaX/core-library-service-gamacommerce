package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.aws;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.BucketParams;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.BucketResponse;

public interface IBucketUtil {
    Boolean addFile(BucketParams bucketParams);

    Boolean deleteFile(BucketParams bucketParams);

    Boolean renameFile(BucketParams bucketParams);

    BucketResponse getFile(BucketParams bucketParams);

    String getUrl(BucketParams bucketParams);

    void setPublic(BucketParams bucketParams, boolean isPublic);

    void createFolder(BucketParams bucketParams);

    Boolean isExistBucket(BucketParams bucketParams);

    Boolean isExistObject(BucketParams bucketParams);

    String pathFile(String... paths);

}
