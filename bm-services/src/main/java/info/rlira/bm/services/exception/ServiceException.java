package info.rlira.bm.services.exception;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 7787168910772920065L;

	public ServiceException() {
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
