package ist.meic.pa;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.parser.AbortCommandParser;
import ist.meic.pa.command.parser.CommandParser;

import java.util.ArrayList;

public class DebuggerCLI {
	static Object runningClass = null;
	static String arguments[] = null;
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
	
	public static void main(String[] args) throws Exception {
		
		runningClass = args[0];
		
		for(int i=1; i<args.length; i++){
			arguments[i-1]=args[i];
		}
		DebuggerCLI debugger = new DebuggerCLI();
		Command commmand = debugger.parseCommand(args[1]);
	}
  
}
