package ist.meic.pa.command.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ist.meic.pa.DebuggerCLI;
import ist.meic.pa.command.Command;
import ist.meic.pa.command.GetCommand;

public class GetCommandParser extends CommandParser {
	
	private static String FIELDNAME;
	private static Pattern pattern = Pattern.compile("Get+("+FIELDNAME+")");
	
	public GetCommandParser(DebuggerCLI debugger) {
		super(debugger);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parseCommand(String line) {
		Command com = null;
		/*Matcher matcher = pattern.matcher(line);
		if (matcher.matches())
			com = new GetCommand(debugger, );*/
		return com;
	}

}
