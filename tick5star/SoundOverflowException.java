

public class SoundOverflowException extends Exception {

	private static final long serialVersionUID = 5939242526374937864L;

	public SoundOverflowException() {}

	public SoundOverflowException(String message) {
		super(message);
	}

	public SoundOverflowException(Throwable cause) {
		super(cause);
	}

	public SoundOverflowException(String message, Throwable cause) {
		super(message, cause);
	}

}
