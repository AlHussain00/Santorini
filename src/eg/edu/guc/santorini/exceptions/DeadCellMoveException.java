package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class DeadCellMoveException extends InvalidMoveException {
		public DeadCellMoveException() {
			super();
		}
		public DeadCellMoveException(String e) {
			super(e);
		}
}
