
/**
 * Extinde clasa student. Toate instantele clasei vor returna 
 * acelasi hashCode. => Tabela de dispersie va avea un singur bucket.
 * => Studentii vor fi dispusi intr-un singur ArrayList.
 * @author Marius Avram
 *
 */
public class LazyStudent extends Student {
	
	/**
	 * Constructor parametri
	 * @param nume
	 * @param varsta
	 */
	public LazyStudent(String nume, int varsta){ 
		super(nume,varsta);
	}
	
	// Definim o constanta
	private static final int CONSTANT = 5;
	
	/* (non-Javadoc)
	 * @see Student#hashCode()
	 */
	@Override
	public int hashCode(){
		return CONSTANT;
	}


}
