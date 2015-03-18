package ist.meic.pa.command;

import ist.meic.pa.DebuggerCLI;

public class AbortCommand extends Command {

	public AbortCommand(DebuggerCLI debugger) {
		super(debugger);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public void execute() {
		getDebugger().removeCurrent();
	}

}
