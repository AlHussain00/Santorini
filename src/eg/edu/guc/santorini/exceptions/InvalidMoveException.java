package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public InvalidMoveException() {
		super();
	}
	public InvalidMoveException(String e) {
		super("Cannot Move here");
	}

}
