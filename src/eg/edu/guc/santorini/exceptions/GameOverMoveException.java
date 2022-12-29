package eg.edu.guc.santorini.exceptions;
@SuppressWarnings("serial")
public class GameOverMoveException extends InvalidMoveException {
	public GameOverMoveException() {
		super();
	}
	
	public GameOverMoveException(String s) {
		super(s);
	}

}
