package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class PieceInMovementException extends InvalidMoveException {
	public PieceInMovementException() {
		super();
	}
	public PieceInMovementException(String e) {
		super(e);
	}

}


