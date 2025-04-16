package loan.management.module.lms.controller;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.transport.http.HTTPException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(SoapFault.class)
    public ResponseEntity<String> handleSoapFault(SoapFault ex) {
        return handleDefault(ex);
    }

    @ExceptionHandler(HTTPException.class)
    public ResponseEntity<String> handleHTTPException(HTTPException ex) {
        return handleDefault(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleDefault(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
