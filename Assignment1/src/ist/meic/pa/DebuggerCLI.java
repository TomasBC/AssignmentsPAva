package ist.meic.pa;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.parser.AbortCommandParser;
import ist.meic.pa.command.parser.CommandParser;
import ist.meic.pa.test.TestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale.Category;

public class DebuggerCLI {
	private Class<?> runningClass;
	private String arguments[];
	private ArrayList<CommandParser> parsers;
	
	/** Input stream reader for the Debugger.*/
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 *  True if an exception was caught when running the testing class
	 */
	private boolean catchedException = false;
	
	/**
	 *  True if line received from user is a legal command
	 */
	private boolean commandFound = false;
	
	/**
	 * Instantiates the array of parsers and adds ALL the parsers
	 */
	DebuggerCLI(){
		parsers = new ArrayList<CommandParser>();
		parsers.add(new AbortCommandParser(this));
	}
	
	/**
	 * Finds if there is a Command that can parse the line
	 * Returns the Command if it exists and null if doesn't
	 * 
	 * @param line
	 * @return parsedCommand null
	 */
	private Command parseCommand(String line) {
		Command parsedCommand = null;
		for (CommandParser parser : parsers) {
			parsedCommand = parser.parseCommand(line);
			if(parsedCommand != null)
				break;
		}
		System.out.println(parsedCommand.getClass().getName());
		return parsedCommand;
	}
	
	/**
	 * Prints the prompt to receiver the user input after an exception was caught
	 * 
	 * @param messsage - message to be shown in the prompt
	 * @return line - input from the user
	 * @throws IOException
	 */
	private String promptUser(String message) throws IOException {
		System.err.print(message);
		String line = in.readLine();
		return line;
	}
	
	
	public static void main(String[] args) throws IOException{
		DebuggerCLI debugger = new DebuggerCLI();
		try{
			debugger.setRunningClass( Class.forName("ist.meic.pa.test.TestClassThrowsException"));
		}catch(ClassNotFoundException e){
			System.out.println("Could not find class: "+e.getMessage());
		}
		
		for(int i=1; i<args.length; i++){
			debugger.getArguments()[i-1]=args[i];
		}
		
		try { 
			debugger.getRunningClass().getMethod("main", String[].class).invoke(debugger.getRunningClass(), new String[]{ null });
		}
		catch (InvocationTargetException e) { 
			debugger.setCatchedException(true);
			try { 
				throw e.getCause(); 
			}
			catch (TestException te) { 
				/*
				 * catches this exception
				 */
			}
			catch(Throwable what){
				System.out.println("What?");
			}
		}
		catch(NoSuchMethodException e){
			System.out.println(e.getMessage());
		}
		catch(IllegalAccessException e){
			System.out.println(e.getMessage());
		}
		
		if(!debugger.isCatchedException()){
			System.out.println("No exception was thrown. Class is ok");
			System.exit(1);
		}
		
		String inputLine = debugger.promptUser(">:");
		Command commmand = debugger.parseCommand(inputLine);
		if(commmand != null && commmand.canExecute()) {
			debugger.setCommandFound(true);
			commmand.execute();
		}
		if (!debugger.isCommandFound())
			System.err.println("No command was executed.");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Class<?> getRunningClass() {
		return runningClass;
	}

	public void setRunningClass(Class<?> runningClass) {
		this.runningClass = runningClass;
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
		this.parsers = parsers;
	}

	public BufferedReader get_in() {
		return in;
	}

	public void set_in(BufferedReader _in) {
		this.in = _in;
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

	public void setCommandFound(boolean commandFound) {
		this.commandFound = commandFound;
	}
  
	
	
	
}