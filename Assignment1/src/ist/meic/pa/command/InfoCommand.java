package ist.meic.pa.command;

public class InfoCommand extends Command {

	public InfoCommand(Class<?> rClass) {
		super(rClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		return true; // Info command is always valid.
	}

	@Override
	public void execute() {
		System.err.println("> Stack Trace:");

	}

}
