package dev.lilli.productservice_sst.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(detail);
    }

    public void clearDetails() {
        if (this.details != null) {
            this.details.clear();
        }
    }

    public boolean hasDetails() {
        return this.details != null && !this.details.isEmpty();
    }

    public String getFirstDetail() {
        return this.details != null && !this.details.isEmpty() ? this.details.get(0) : null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    // New methods

    public void appendDetails(List<String> additionalDetails) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.addAll(additionalDetails);
    }

    public void appendDetail(String additionalDetail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(additionalDetail);
    }

    public void removeDetail(String detail) {
        if (this.details != null) {
            this.details.remove(detail);
        }
    }

    public int getDetailCount() {
        return this.details != null ? this.details.size() : 0;
    }

    public boolean hasErrorCode() {
        return this.errorCode != null && !this.errorCode.isEmpty();
    }

    public boolean hasResolution() {
        return this.resolution != null && !this.resolution.isEmpty();
    }

    public boolean hasPath() {
        return this.path != null && !this.path.isEmpty();
    }

    public boolean hasMessage() {
        return this.message != null && !this.message.isEmpty();
    }
}
