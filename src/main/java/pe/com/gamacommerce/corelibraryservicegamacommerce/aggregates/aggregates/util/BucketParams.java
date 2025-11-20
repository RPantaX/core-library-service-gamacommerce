package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BucketParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bucketName;
    private String bucketRegion;
    private MultipartFile file;
    private String filePath;
    private String newFilePath;
    private String user;
    private String key;
}
