package com.qa.musicstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Store not found.")
public class StoreNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6011026487018170968L;

	public StoreNotFoundException() {
		super();
	}

	public StoreNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StoreNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StoreNotFoundException(String message) {
		super(message);
	}

	public StoreNotFoundException(Throwable cause) {
		super(cause);
	}

}
