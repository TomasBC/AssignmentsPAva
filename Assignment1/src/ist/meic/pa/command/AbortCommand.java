package ist.meic.pa.command;

public class AbortCommand extends Command {

	public AbortCommand(Class<?> rClass) {
		super(rClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public void execute() {
		System.out.println("Exited the application");
		System.exit(1);
	}

}
