package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.aws.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions.AppException;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.aws.IBucketUtil;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.BucketParams;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.BucketResponse;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util.GlobalErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

@Component
public class AwsBucketUtil implements IBucketUtil {

    @Autowired(required = false)
    AmazonS3 clientS3;

    private static final String INVALID_ACCESS_KEY_ID = "InvalidAccessKeyId";
    private static final String ERROR_INVALID_ACCESS_KEY_ID = "No se pudo conectar al repositorio con las credenciales asignadas";

    @Override
    public Boolean addFile(BucketParams bucketParams) {
        try {
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(bucketParams.getFile().getContentType());
            data.setContentLength(bucketParams.getFile().getSize());
            clientS3.putObject(bucketParams.getBucketName(), bucketParams.getFilePath(),
                    bucketParams.getFile().getInputStream(), data);
            return true;
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00001,
                        "Error al subir archivo a AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00001,
                        "Error al subir archivo a AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00001, "Error al subir archivo a AWS S3: " + e.getMessage());
        }
    }

    @Override
    public BucketResponse getFile(BucketParams bucketParams) {
        try {
            GetObjectRequest request = new GetObjectRequest(bucketParams.getBucketName(), bucketParams.getFilePath());
            S3Object s3Object = clientS3.getObject(request);
            byte[] data = IOUtils.toByteArray(s3Object.getObjectContent());
            return new BucketResponse("1", data);
        } catch (AmazonS3Exception e) {
            switch (e.getErrorCode()) {
                case INVALID_ACCESS_KEY_ID:
                    throw new AppException(GlobalErrorEnum.ERB00002,
                            "Error al obtener archivo del AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
                case "NoSuchKey":
                    throw new AppException(GlobalErrorEnum.ERB00003,
                            "No existe el archivo solicitado en AWS S3: " + e.getMessage());
                default:
                    throw new AppException(GlobalErrorEnum.ERB00004,
                            "No se pudo descargar el archivo solicitado del AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00004,
                    "No se pudo descargar el archivo solicitado del AWS S3: " + e.getMessage());
        }
    }

    @Override
    public void createFolder(BucketParams bucketParams) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(0);
            InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketParams.getBucketName(),
                    bucketParams.getFilePath(), emptyContent, metadata);
            clientS3.putObject(putObjectRequest);
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00005,
                        "Error al crear carpeta en el AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00005,
                        "Error al crear carpeta en el AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00005,
                    "Error al crear carpeta en el AWS S3: " + e.getMessage());
        }
    }

    @Override
    public Boolean renameFile(BucketParams bucketParams) {
        try {
            if (clientS3.doesObjectExist(bucketParams.getBucketName(), bucketParams.getFilePath())) {
                if (!clientS3.doesObjectExist(bucketParams.getBucketName(), bucketParams.getNewFilePath())) {
                    clientS3.copyObject(bucketParams.getBucketName(), bucketParams.getFilePath(),
                            bucketParams.getBucketName(), bucketParams.getNewFilePath());
                    clientS3.deleteObject(bucketParams.getBucketName(), bucketParams.getFilePath());
                    return true;
                } else {
                    throw new AppException(GlobalErrorEnum.ERB00008,
                            "Ya existe un archivo con el nuevo nombre indicado");
                }
            } else {
                throw new AppException(GlobalErrorEnum.ERB00007, "Archivo buscado no existe");
            }
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00006,
                        "Error al renombrar archivo en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00006,
                        "Error al renombrar archivo en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00006,
                    "Error al renombrar archivo en AWS S3: " + e.getMessage());
        }
    }

    @Override
    public Boolean deleteFile(BucketParams bucketParams) {
        try {
            clientS3.deleteObject(bucketParams.getBucketName(), bucketParams.getFilePath());
            return true;
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00009,
                        "Error al eliminar archivo en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00009,
                        "Error al eliminar archivo en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00009,
                    "Error al eliminar archivo en AWS S3: " + e.getMessage());
        }
    }

    @Override
    public Boolean isExistBucket(BucketParams bucketParams) {
        try {
            return clientS3.doesBucketExistV2(bucketParams.getBucketName());
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00010,
                        "Error al verificar la existencia del bucket en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00010,
                        "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00010,
                    "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
        }
    }

    @Override
    public String pathFile(String... paths) {
        StringBuilder sb = new StringBuilder("");
        for (String path : paths) {
            if (sb.length() != 0) {
                sb.append("/");
            }
            sb.append(path);
        }
        return sb.toString();
    }

    @Override
    public void setPublic(BucketParams bucketParams, boolean isPublic) {
        try {
            CannedAccessControlList cacl = isPublic ? CannedAccessControlList.PublicRead
                    : CannedAccessControlList.Private;
            clientS3.setObjectAcl(bucketParams.getBucketName(), bucketParams.getFilePath(), cacl);
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00011,
                        "Error al verificar la existencia del bucket en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00011,
                        "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00011,
                    "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
        }
    }

    @Override
    public String getUrl(BucketParams bucketParams) {
        try {
            URL url = clientS3.getUrl(bucketParams.getBucketName(), bucketParams.getFilePath());
            if (isExistObject(bucketParams)) {
                return url.toExternalForm();
            } else {
                throw new AppException(GlobalErrorEnum.ERB00013, "Error al buscar el objeto en AWS S3");
            }
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00012,
                        "Error al verificar la existencia del bucket en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00012,
                        "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00012,
                    "Error al verificar la existencia del bucket en AWS S3: " + e.getMessage());
        }
    }

    @Override
    public Boolean isExistObject(BucketParams bucketParams) {
        try {
            return clientS3.doesObjectExist(bucketParams.getBucketName(), bucketParams.getFilePath());
        } catch (AmazonS3Exception e) {
            if (e.getErrorCode().equals(INVALID_ACCESS_KEY_ID)) {
                throw new AppException(GlobalErrorEnum.ERB00013,
                        "Error al buscar el objeto en AWS S3: " + ERROR_INVALID_ACCESS_KEY_ID);
            } else {
                throw new AppException(GlobalErrorEnum.ERB00013,
                        "Error al buscar el objeto en AWS S3: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new AppException(GlobalErrorEnum.ERB00013,
                    "Error al buscar el objeto en AWS S3: " + e.getMessage());
        }
    }

}
