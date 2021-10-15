package com.qa.musicstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Not enough stock.")
public class InsufficientStockException extends RuntimeException {

	private static final long serialVersionUID = 5904674632428528341L;

	public InsufficientStockException() {
		super();
	}

	public InsufficientStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsufficientStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientStockException(String message) {
		super(message);
	}

	public InsufficientStockException(Throwable cause) {
		super(cause);
	}

}
