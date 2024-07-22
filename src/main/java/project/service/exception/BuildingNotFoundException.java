package project.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BuildingNotFoundException extends RuntimeException {

    public BuildingNotFoundException(String message) {
        super(message);

    }

}
