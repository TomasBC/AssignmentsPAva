package ist.meic.pa.command;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GetCommand extends Command {

	private String fieldName;
	private ArrayList<Field> possibleFields = new ArrayList<Field>();
	
	public GetCommand(Class<?> rClass, String fieldName) {
		super(rClass);
		this.fieldName = fieldName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		return getPossibleFields().size() > 0;
	}

	private ArrayList<Field> getPossibleFields() {
		Class<?> currentClass = getRunningClass();

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
			value = field.get(getRunningClass());
			System.err.println(value.toString());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
