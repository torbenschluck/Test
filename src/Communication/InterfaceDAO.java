package Communication;

import java.sql.Connection;

public interface InterfaceDAO {
	
	public abstract void setValue(Object vo);
	
	public abstract Object getValue(Object vo);
	
	public abstract String[][] getAll();
	
	public abstract void deleteValue(Object vo);
}
