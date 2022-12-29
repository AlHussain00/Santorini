package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class MoveAfterMoveException extends InvalidMoveException {
	public MoveAfterMoveException() {
		super();
	}
	
	public MoveAfterMoveException(String s) {
		super(s);
	}
}
