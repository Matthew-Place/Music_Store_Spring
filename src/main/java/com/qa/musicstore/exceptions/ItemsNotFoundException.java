package com.qa.musicstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No items found.")
public class ItemsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1352524164974662983L;

	public ItemsNotFoundException() {
		super();
	}

	public ItemsNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ItemsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemsNotFoundException(String message) {
		super(message);
	}

	public ItemsNotFoundException(Throwable cause) {
		super(cause);
	}

}
