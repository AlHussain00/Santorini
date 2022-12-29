package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class DeadCellPlacementException extends InvalidPlacementException {
	public DeadCellPlacementException() {
		super();
	}
	public DeadCellPlacementException(String e) {
		super(e);
	}
}
