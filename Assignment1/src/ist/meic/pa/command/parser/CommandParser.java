package ist.meic.pa.command.parser;

import ist.meic.pa.DebuggerCLI;
import ist.meic.pa.command.Command;

public abstract class CommandParser {
	protected DebuggerCLI debugger;
	protected static final String IDENTIFIER = "[\\w]+";
	
	public CommandParser(DebuggerCLI debugger) {
		this.debugger = debugger;
	}
	
	public abstract Command parseCommand(String line);
}
