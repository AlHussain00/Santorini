package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class OutOfTurnMoveException extends InvalidMoveException  {
	public OutOfTurnMoveException() {
		super();
	}
	public OutOfTurnMoveException(String e) {
		super(e);
	}
}
