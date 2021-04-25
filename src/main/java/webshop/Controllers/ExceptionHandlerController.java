package webshop.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import webshop.exception.WebshopException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(WebshopException.class)
	public ResponseEntity<List<String>> handleWebshopException(WebshopException ex){
		ResponseEntity<List<String>> retVal = new ResponseEntity<>(ex.getErrorMessages(), HttpStatus.BAD_REQUEST);

		return retVal;
	}
	
}
