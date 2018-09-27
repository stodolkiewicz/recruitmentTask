package github.info.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RepositoryNotFoundException extends Exception{

    public RepositoryNotFoundException(String owner, String repositoryName){
        super("REPOSITORY: " + repositoryName + " for username: " + owner + " NOT FOUND.");
    }

}
