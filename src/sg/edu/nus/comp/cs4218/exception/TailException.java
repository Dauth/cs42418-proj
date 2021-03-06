package sg.edu.nus.comp.cs4218.exception;

public class TailException extends AbstractApplicationException {

	private static final long serialVersionUID = -8243374226204516244L;

	public TailException(String message) {
		super("tail: " + message);
	}

	public TailException(Exception excep, String message) {
		super("tail: " + message);
	}

	public TailException(Exception excep) {
		super("tail: " + excep.getMessage());
	}
}