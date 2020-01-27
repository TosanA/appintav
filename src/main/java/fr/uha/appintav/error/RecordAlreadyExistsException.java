package fr.uha.appintav.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordAlreadyExistsException extends RuntimeException {
	public RecordAlreadyExistsException(String exception) {
        super(exception);
    }
}
