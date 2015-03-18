package ist.meic.pa.test;

public class ExceptionFoundException extends TestException{
	
	private static final long serialVersionUID = 1L;
	public ExceptionFoundException(){
		
	}
	@Override
	public String getMessage(){
		return "An exception was found";
	}
}
