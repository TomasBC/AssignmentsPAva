package ist.meic.pa;

public class DebuggerCLI {
	static Object _runningClass = null;
	static String _arguments[] = null;
	
	public static void main(String[] args) throws Exception {
		_runningClass = args[0];
		
		for(int i=1; i<args.length; i++){
			_arguments[i-1]=args[i];
		}
		
		
	}
}
