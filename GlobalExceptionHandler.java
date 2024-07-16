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
}
