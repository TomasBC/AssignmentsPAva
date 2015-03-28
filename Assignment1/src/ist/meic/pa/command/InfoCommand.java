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
		// as per java.lang.Object spec.
		// See: http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString%28%29
		
		System.err.println("Called Object: " + getRunningClass().getName() + "@" + Integer.toHexString(getRunningClass().hashCode()));

	}

}
