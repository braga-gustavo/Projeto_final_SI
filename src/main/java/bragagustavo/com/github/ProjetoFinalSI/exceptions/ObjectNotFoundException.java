package bragagustavo.com.github.ProjetoFinalSI.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L; //RuntimeException ja possui o "Serializable"

    public ObjectNotFoundException(String message ){
        super(message);

    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
