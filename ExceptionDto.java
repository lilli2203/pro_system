package dev.lilli
    .productservice_sst.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private String resolution;
    private String errorCode;
    private LocalDateTime timestamp;
    private List<String> details;
    private String path;

    public ExceptionDto() {
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionDto(String message, String resolution) {
        this.message = message;
        this.resolution = resolution;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionDto(String message, String resolution, String errorCode) {
        this.message = message;
        this.resolution = resolution;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public ExceptionDto(String message, String resolution, String errorCode, List<String> details) {
        this.message = message;
        this.resolution = resolution;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public ExceptionDto(String message, String resolution, String errorCode, List<String> details, String path) {
        this.message = message;
        this.resolution = resolution;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
        this.path = path;
    }

    public String getFormattedTimestamp() {
        return this.timestamp.toString();
    }

    @Override
    public String toString() {
        return "ExceptionDto{" +
                "message='" + message + '\'' +
                ", resolution='" + resolution + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                ", details=" + details +
                ", path='" + path + '\'' +
                '}';
    }

    public static ExceptionDto createSimple(String message) {
        return new ExceptionDto(message, "No resolution available");
    }

    public static ExceptionDto createWithErrorCode(String message, String errorCode) {
        return new ExceptionDto(message, "No resolution available", errorCode);
    }

    public static ExceptionDto createDetailed(String message, List<String> details) {
        return new ExceptionDto(message, "Check details for more information", null, details);
    }

    public static ExceptionDto createFull(String message, String resolution, String errorCode, List<String> details, String path) {
        return new ExceptionDto(message, resolution, errorCode, details, path);
    }

    public void addDetail(String detail) {
        this.details.add(detail);
    }

    public void clearDetails() {
        this.details.clear();
    }

    public boolean hasDetails() {
        return this.details != null && !this.details.isEmpty();
    }

    public String getFirstDetail() {
        return this.details != null && !this.details.isEmpty() ? this.details.get(0) : null;
    }
}
