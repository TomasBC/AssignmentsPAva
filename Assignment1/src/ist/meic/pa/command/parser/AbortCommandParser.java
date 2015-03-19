package ist.meic.pa.command.parser;

import ist.meic.pa.DebuggerCLI;
import ist.meic.pa.command.AbortCommand;
import ist.meic.pa.command.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbortCommandParser extends CommandParser {

	private static Pattern pattern = Pattern.compile("Abort");
	
	public AbortCommandParser(DebuggerCLI debugger) {
		super(debugger);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parseCommand(String line) {
		Command com = null;
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches())
			com = new AbortCommand(debugger);
		return com;
	}

}
