package com.mmjitu.taskmanager.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mmjitu.taskmanager.response.ApiError;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 400 Missing request header 'Authorization' for method parameter of type
	 * String Example: latLng value is missing on header.
	 **/
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleServletRequestBindingException()", ex.getLocalizedMessage());

		String error = ex.getLocalizedMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * 400 Malformed JSON request. Example Date format mismatch.
	 **/
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String error = ex.getRootCause() + " Malformed JSON request";
		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleHttpMessageNotReadable()", ex.getLocalizedMessage());

		CustomExceptionHandler.handleException("CustomRestExceptionHandler.handleHttpMessageNotReadable()", ex.getLocalizedMessage());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * 405 HttpRequestMethodNotSupportedException – which occurs when you send a
	 * requested with an unsupported HTTP method Example: GET method is not
	 * supported for this request. Supported methods are POST
	 **/
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleHttpRequestMethodNotSupported()", ex.getLocalizedMessage());

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * 415 HttpMediaTypeNotSupportedException – which occurs when the client send a
	 * request with unsupported media type Example: Content-type is missing.
	 **/
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleHttpMediaTypeNotSupported()", ex.getLocalizedMessage());

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * Exception to be thrown when validation on an argument annotated with @Valid fails.	 
	 */
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleMethodArgumentNotValid()", ex.getLocalizedMessage());

		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	/**
	 * ServletRequestBindingException subclass that indicates a missing parameter.
	 */
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleMissingServletRequestParameter()", ex.getLocalizedMessage());
		String error = ex.getParameterName() + " parameter is missing";

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	/**
	 * A TypeMismatchException raised while resolving a controller method argument.
	 * Provides access to the target MethodParameter.
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

		CustomExceptionHandler.handleException("CustomResponseEntityExceptionHandler.handleMethodArgumentTypeMismatch()", ex.getLocalizedMessage());
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}