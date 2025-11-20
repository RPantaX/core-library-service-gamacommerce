package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.util;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions.TypeException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions.TypeException.E;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Boolean error;
    private String code;
    private String title;
    private String message;
    private TypeException type;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date = LocalDateTime.now();
    private Object data;

    public LocalDateTime getDate() {
        return date;
    }

    public static ApiResponse ok(String message, Object data) {
        return ok("", message, data);
    }

    public static ApiResponse ok(String title, String message, Object data) {
        return load(false, title, message, "200", data, null);
    }

    public static ApiResponse create(String message, Object data) {
        return create("", message, data);
    }

    public static ApiResponse create(String title, String message, Object data) {
        return load(false, title, message, "201", data, null);
    }

    public static ApiResponse error(String message, String code, TypeException type) {
        return ApiResponse.error("", message, code, type);
    }

    public static ApiResponse error(String title, String message, String code, TypeException type) {
        return load(true, title, message, code, null, type);
    }

    public static ApiResponse load(boolean isError, String title, String message, String code, Object data,
                                   TypeException type) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(isError);
        apiResponse.setCode(code);
        apiResponse.setTitle(title);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        apiResponse.setType(type);
        return apiResponse;
    }

    public static ApiResponse error(String message) {
        return ApiResponse.error(message, "500", E);
    }
}

