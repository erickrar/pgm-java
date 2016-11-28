package pagarme.exception;

public class InvalidOperationException extends Exception {
	private static final long serialVersionUID = -1171690318754877605L;

	public InvalidOperationException(String message){
		super(message);
	}
}
