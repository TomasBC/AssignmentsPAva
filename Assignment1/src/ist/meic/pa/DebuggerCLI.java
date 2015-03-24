package ist.meic.pa;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.parser.AbortCommandParser;
import ist.meic.pa.command.parser.CommandParser;
import ist.meic.pa.command.parser.GetCommandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class DebuggerCLI {
	private static Class<?> runningClass;
	private String arguments[];
	private static ArrayList<CommandParser> parsers;
	
	/** Input stream reader for the Debugger.*/
	static private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 *  True if an exception was caught when running the testing class
	 */
	private boolean catchedException = false;
	
	/**
	 *  True if line received from user is a legal command
	 */
	private static boolean commandFound = false;
	
	/**
	 * Instantiates the array of parsers and adds ALL the parsers
	 */
	DebuggerCLI(){
		
	}
	
	/**
	 * Finds if there is a Command that can parse the line
	 * Returns the Command if it exists and null if doesn't
	 * 
	 * @param line
	 * @return parsedCommand null
	 */
	public static Command parseCommand(String line) {
		Command parsedCommand = null;
		for (CommandParser parser : parsers) {
			parsedCommand = parser.parseCommand(line);
			if(parsedCommand != null)
				break;
		}
		System.out.println(parsedCommand.getClass().getName());
		return parsedCommand;
	}
	
	
	public static void test(){
		System.out.println("test()");
	}
	/**
	 * Prints the prompt to receiver the user input after an exception was caught
	 * 
	 * @param messsage - message to be shown in the prompt
	 * @return line - input from the user
	 * @throws IOException
	 */
	public static String promptUser(String message) throws IOException {
		System.out.print(message);
		String line = in.readLine();
		return line;
	}
	
	
	public static void addParsers(Class<?> rClass){
		parsers.add(new AbortCommandParser(rClass));
		parsers.add(new GetCommandParser(rClass));
	}
	
	public static void main(String[] args) throws IOException, NotFoundException, CannotCompileException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		parsers = new ArrayList<CommandParser>();
		
		/*for(int i=1; i<args.length; i++){
			debugger.getArguments()[i-1]=args[i];
		}
		*/
		
		ClassPool pool = ClassPool.getDefault();
		pool.importPackage("ist.meic.pa");
		pool.importPackage("ist.meic.pa.command");
		CtClass ctClass = pool.get("ist.meic.pa.test.TestClassThrowsException");
		CtMethod m = ctClass.getDeclaredMethod("main");
		CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
		m.addCatch("{ System.out.println(\"Estou no catch\");"
				+ "System.out.println($e);"
				+ "DebuggerCLI.addParsers(DebuggerCLI.getRunningClass());"
				+ "String input = DebuggerCLI.promptUser(\"DebuggerCLI>:\");"
				+ "Command commmand = DebuggerCLI.parseCommand(input);"
				+ "if(commmand != null && commmand.canExecute()) {"
				+ 	"DebuggerCLI.setCommandFound(true);"
				+ 	"commmand.execute(); }"
				+ "throw $e; }", etype);
		ctClass.toClass();
		DebuggerCLI.setRunningClass( Class.forName("ist.meic.pa.test.TestClassThrowsException"));
		Object o =DebuggerCLI.getRunningClass().newInstance();
		Method method = o.getClass().getMethod("main", String[].class);
		method.invoke(o, new String[]{ null });
		
		/*String inputLine = debugger.promptUser(">:");
		Command commmand = debugger.parseCommand(inputLine);
		if(commmand != null && commmand.canExecute()) {
			debugger.setCommandFound(true);
			commmand.execute();
		}
		if (!debugger.isCommandFound())
			System.err.println("No command was executed.");*/
	}

	
	
	
	
	
	
	
	
	

	public static Class<?> getRunningClass() {
		return runningClass;
	}

	public static void setRunningClass(Class<?> rClass) {
		runningClass = rClass;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	public ArrayList<CommandParser> getParsers() {
		return parsers;
	}
	
	public void setParsers(ArrayList<CommandParser> parsers) {
		DebuggerCLI.parsers = parsers;
	}

	public BufferedReader get_in() {
		return in;
	}

	public void set_in(BufferedReader _in) {
		DebuggerCLI.in = _in;
	}

	public boolean isCatchedException() {
		return catchedException;
	}

	public void setCatchedException(boolean catchedException) {
		this.catchedException = catchedException;
	}

	public boolean isCommandFound() {
		return commandFound;
	}

	public static void setCommandFound(boolean found) {
		commandFound = found;
	}
  
	
	
	
}
