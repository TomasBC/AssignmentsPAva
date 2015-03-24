package ist.meic.pa.command;

public abstract class Command {
	private Class<?> runningClass;
	
	Command(Class<?> rClass) {
		runningClass = rClass;
	}
	
	public Class<?> getRunningClass() {
		return runningClass;
	}
	
	public abstract boolean canExecute();
	public abstract void execute();
}
