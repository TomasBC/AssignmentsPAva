package ist.meic.pa;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.parser.AbortCommandParser;
import ist.meic.pa.command.parser.CommandParser;
import ist.meic.pa.test.TestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DebuggerCLI {
	static Class<?> runningClass;
	static String arguments[];
	private ArrayList<CommandParser> parsers;
	
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
	
	public static void main(String[] args){
		try{
			runningClass = Class.forName("ist.meic.pa.test.TestClass");
		}catch(ClassNotFoundException e){
			System.out.println("Could not find class: "+e.getMessage());
		}
		for(int i=1; i<args.length; i++){
			arguments[i-1]=args[i];
		}
		/*DebuggerCLI debugger = new DebuggerCLI();
		Command commmand = debugger.parseCommand(args[1]);*/
		try { 
			runningClass.getMethod("main", String[].class).invoke(runningClass, new String[]{ null });		
		}
		catch (InvocationTargetException e) { 
			try { 
				throw e.getCause(); 
			}
			catch (TestException te) { 
				System.out.println("Ola");
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
		
	}
  
}
