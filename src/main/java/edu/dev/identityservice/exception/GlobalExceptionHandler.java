package edu.dev.identityservice.exception;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.dev.identityservice.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final String MIN_ATTRIBUTE = "min";

	@ExceptionHandler(value = AppException.class)
	ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
		ErrorCode errorCode = exception.getErrorCode();

		ApiResponse<?> apiResponse = new ApiResponse<>();

		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());

		return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
	}

	@ExceptionHandler(value = AuthorizationDeniedException.class)
	ResponseEntity<ApiResponse<?>> handlingAccessDeniedException(AuthorizationDeniedException exception) {
		ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

		return ResponseEntity.status(errorCode.getStatusCode())
				.body(ApiResponse.builder().code(errorCode.getCode()).message(errorCode.getMessage()).build());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ApiResponse<?>> handlingValidation(MethodArgumentNotValidException exception) {
		String enumKey = exception.getFieldError().getDefaultMessage();

		System.out.println("enum key: " + enumKey);

		ErrorCode errorCode = ErrorCode.INVALID_KEY;

		Map<String, Object> attributes = null;

		try {
			errorCode = ErrorCode.valueOf(enumKey);

			var constraintViolation = exception.getBindingResult().getAllErrors().getFirst()
					.unwrap(ConstraintViolation.class);

			attributes = (Map<String, Object>) constraintViolation.getConstraintDescriptor().getAttributes();

			log.info("constraint attribute: {}", attributes);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		ApiResponse<?> apiResponse = new ApiResponse<>();

		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(Objects.nonNull(attributes) ? mapAttribute(errorCode.getMessage(), attributes)
				: errorCode.getMessage());

		return ResponseEntity.badRequest().body(apiResponse);
	}

	private String mapAttribute(String message, Map<String, Object> attributes) {
		String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));
		return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
	}
}
