package dev.lilli.productservice_sst.exceptionhandlers;

import dev.lilli.productservice_sst.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(ArithmeticException ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("An arithmetic error occurred: " + ex.getMessage());
        dto.setResolution("Check your mathematical operations");
        dto.setErrorCode("ARITHMETIC_ERROR");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Array index is out of bounds: " + ex.getMessage());
        dto.setResolution("Check your array indices");
        dto.setErrorCode("ARRAY_INDEX_OUT_OF_BOUNDS");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Invalid productId " + ex.getProductId() + " passed: " + ex.getMessage());
        dto.setResolution("ProductNotFoundException caught");
        dto.setErrorCode("PRODUCT_NOT_FOUND");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("A null pointer exception occurred: " + ex.getMessage());
        dto.setResolution("Ensure that no variables are null before calling methods on them");
        dto.setErrorCode("NULL_POINTER_EXCEPTION");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("An illegal argument was passed: " + ex.getMessage());
        dto.setResolution("Verify the arguments passed to methods");
        dto.setErrorCode("ILLEGAL_ARGUMENT_EXCEPTION");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGlobalException(Exception ex, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("An unexpected error occurred: " + ex.getMessage());
        dto.setResolution("Contact support with the error details");
        dto.setErrorCode("GLOBAL_EXCEPTION");
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

 private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(ArithmeticException ex, WebRequest request) {
        return buildErrorResponse(ex, "ARITHMETIC_ERROR", "Check your mathematical operations", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex, WebRequest request) {
        return buildErrorResponse(ex, "ARRAY_INDEX_OUT_OF_BOUNDS", "Check your array indices", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex, "PRODUCT_NOT_FOUND", "Invalid productId " + ex.getProductId() + " passed", HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException(NullPointerException ex, WebRequest request) {
        return buildErrorResponse(ex, "NULL_POINTER_EXCEPTION", "Ensure that no variables are null before calling methods on them", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return buildErrorResponse(ex, "ILLEGAL_ARGUMENT_EXCEPTION", "Verify the arguments passed to methods", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGlobalException(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, "GLOBAL_EXCEPTION", "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ExceptionDto> buildErrorResponse(Exception ex, String errorCode, String resolution, HttpStatus status, WebRequest request) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(ex.getMessage());
        dto.setErrorCode(errorCode);
        dto.setResolution(resolution);
        dto.setTimestamp(LocalDateTime.now());
        dto.setPath(request.getDescription(false));
        
        logError(ex, status, request);

        return new ResponseEntity<>(dto, status);
    }

    private void logError(Exception ex, HttpStatus status, WebRequest request) {
        logger.error("Exception caught - status: {}, message: {}, path: {}", status, ex.getMessage(), request.getDescription(false));
    }
}
