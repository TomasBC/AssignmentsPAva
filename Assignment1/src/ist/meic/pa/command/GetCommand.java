package ist.meic.pa.command;

import java.lang.reflect.Field;
import java.util.ArrayList;

import ist.meic.pa.DebuggerCLI;

public class GetCommand extends Command {

	private String fieldName;
	private ArrayList<Field> possibleFields = new ArrayList<Field>();
	
	public GetCommand(DebuggerCLI debugger, String fieldName) {
		super(debugger);
		this.fieldName = fieldName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		return getPossibleFields().size() > 0;
	}

	private ArrayList<Field> getPossibleFields() {
		Object curr = getDebugger().getRunningClass();
		Class<?> currentClass = curr.getClass();

		while (currentClass != Object.class) {
			try {

				Field currentField = currentClass.getDeclaredField(fieldName);
				System.out.println(currentField);
	
				possibleFields.add(currentField);
			} catch (NoSuchFieldException e) {
				//Do nothing
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			}
			currentClass = currentClass.getSuperclass();
		}
		
		return possibleFields;
	}

	@Override
	public void execute() {
		Field field = possibleFields.get(0);
		Object value;
		try {
			value = field.get(getDebugger().getRunningClass());
			System.err.println(value.toString());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
