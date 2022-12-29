package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class PieceInPlacementException extends InvalidPlacementException {
	public PieceInPlacementException() {
		super();
	}
	public PieceInPlacementException(String e) {
		super(e);
	}

}
