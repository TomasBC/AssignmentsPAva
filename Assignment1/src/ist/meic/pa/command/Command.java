package ist.meic.pa.command;

import ist.meic.pa.DebuggerCLI;

public abstract class Command {
	private DebuggerCLI _debugger;
	
	Command(DebuggerCLI debugger) {
		_debugger = debugger;
	}
	
	public DebuggerCLI getDebugger() {
		return _debugger;
	}
	
	public abstract boolean canExecute();
	public abstract void execute();
}
