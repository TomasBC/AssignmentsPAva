package ist.meic.pa.command.parser;

import ist.meic.pa.command.Command;

public abstract class CommandParser {
	protected Class<?> runningClass;
	protected static final String IDENTIFIER = "[\\w]+";
	
	public CommandParser(Class<?> rClass) {
		this.runningClass = rClass;
	}
	
	public abstract Command parseCommand(String line);
}
