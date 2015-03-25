package ist.meic.pa.command;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SetCommand extends Command {

	private String fieldName;
	private Object newValue;

	private ArrayList<Field> possibleFields = new ArrayList<Field>();
	
	public SetCommand(Class<?> rClass, String fName, Object nValue) {
		super(rClass);
		fieldName=fName;
		newValue=nValue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canExecute() {
		ArrayList<Field> fields = getPossibleFields();
		if (fields.size() < 0)
			return false;
		assert newValue != null;
		
		return assignableFrom(fields.get(0).getType(), newValue.getClass());
	}

	@Override
	public void execute() {
		ArrayList<Field> fields = getPossibleFields();
		assert fields.size() > 0;
		Field field = fields.get(0);
		assert assignableFrom(field.getType(), newValue.getClass());
		
		field.setAccessible(true);
		try {
			field.set(getRunningClass().newInstance(), newValue);
			field.setAccessible(true);
			System.out.println(field.get(getRunningClass().newInstance()));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private ArrayList<Field> getPossibleFields() {
		Class<?> currentClass = getRunningClass();

		while (currentClass != Object.class) {
			try {

				Field currentField = currentClass.getDeclaredField(fieldName);

	
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
	
	static final Class<?>[] wrappers = { Integer.class, Boolean.class,
		Character.class, Float.class, Double.class, Byte.class,
		Short.class, Long.class };
	static final Class<?>[] primitives = { int.class, boolean.class,
		char.class, float.class, double.class, byte.class, short.class,
		long.class };

	public static boolean assignableFrom(Class<?> destination, Class<?> instanceType) {
		boolean normalAssignable = destination.isAssignableFrom(instanceType);
		if (normalAssignable)
			return true;
		if (!destination.isPrimitive())
			return false;

		for (int i = 0; i < wrappers.length; i++) {
			if (instanceType.equals(wrappers[i]) && destination.equals(primitives[i]))
				return true;
		}
		return false;
	}
	
}
