package ist.meic.pa.command.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.GetCommand;

public class GetCommandParser extends CommandParser {
	
	private static Pattern pattern = Pattern.compile("Get[\\s]+("+IDENTIFIER+")");
	
	public GetCommandParser(Class<?> rClass) {
		super(rClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parseCommand(String line) {
		Command com = null;
		Matcher matcher = pattern.matcher(line);

		if (!matcher.matches())
			return null;

		String fieldName = matcher.group(1);
		com = new GetCommand(runningClass, fieldName);

		return com;
	}

}
