package ist.meic.pa.command.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ist.meic.pa.command.Command;
import ist.meic.pa.command.GetCommand;
import ist.meic.pa.command.SetCommand;

public class SetCommandParser extends CommandParser {
	
	protected static final String INTEGER ="\\-?[\\d]+";
	protected static final String STRING = "\"[^\"]*\"";
	protected static final String VALUE = INTEGER + "|" + STRING + "|" + CommandParser.IDENTIFIER;
	private static Pattern pattern = Pattern.compile("Set[\\s]+("+IDENTIFIER+")[\\s]+("+VALUE+")[\\s]*");
	
	public SetCommandParser(Class<?> rClass) {
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
		Object nValue = matcher.group(2);
		com = new SetCommand(runningClass, fieldName, nValue);

		return com;
	}

}
