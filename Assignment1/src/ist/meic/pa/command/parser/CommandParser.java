package ist.meic.pa.command.parser;

import ist.meic.pa.DebuggerCLI;
import ist.meic.pa.command.Command;

public abstract class CommandParser {
	protected DebuggerCLI _debugger;
	
	public CommandParser(DebuggerCLI debugger) {
		_debugger = debugger;
	}
	
	public abstract Command parseCommand(String line);
}
