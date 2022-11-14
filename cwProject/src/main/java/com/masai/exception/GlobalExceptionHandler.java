package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CabException.class)
	public ResponseEntity<MyErrorDetails> CabExceptionHandler(CabException se, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(se.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(DriverException.class)
	public ResponseEntity<MyErrorDetails> DriverExceptionHandler(DriverException de, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(de.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(TripBookingException.class)
	public ResponseEntity<MyErrorDetails> TripBookingExceptionHandler(TripBookingException te, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(te.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> CustomerExceptionHandler(CustomerException ce, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(ce.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException le, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(le.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception ex, WebRequest re) {

		MyErrorDetails med = new MyErrorDetails();
		med.setTimestamp(LocalDateTime.now());
		med.setMessage(ex.getMessage());
		med.setDescription(re.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);

	}

}
