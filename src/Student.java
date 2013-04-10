
/**
 * 
 * Clasa student ce contine numele si varsta unui student.
 * @author Marius Avram
 *
 */
public class Student {
	
	private String nume;
	private int varsta;
	
	/**
	 * Constructor cu parametri.
	 * @param nume
	 * @param varsta
	 */
	public Student(String nume, int varsta){ 
		this.setNume(nume);
		this.setVarsta(varsta);
	}
	
	public Student(){ 
		this("Gigel", 20);
	}
	
	/**
	 * Suprascriem metoda hashCode astfel incat aceasta sa returneze 
	 * un cod obtinut dupa algoritmul dat in enuntul temei.
	 * @return codul calculat
	 */
	@Override
	public int hashCode(){
		int h=17;
		h = 37 * h + getVarsta();
		h = 37 * h + getNume().hashCode(); 
		return h;
	}
	

	/**
	 * Determina daca 2 instante ale clasei Student sunt egale. 
	 * Egale inseamna ca au acelasi nume si aceeasi varsta.
	 * @return adevarat/fals daca sunt egale sau nu
	 */
	@Override
	public boolean equals(Object o){
		Student s2 = null;
		// folosim o clauza try catch pentur ca e posibil ca 
		// metoda equals sa primeasca un obiect ce nu poate 
		// fi transformat in Student si sa genereze o exceptie
		try{
			s2 = (Student)o;
			if (this.getNume().compareTo(s2.getNume()) == 0 && 
				this.getVarsta() == s2.getVarsta()) {
				
				return true;
				
			}
		}
		catch (Exception e) { 
			System.out.println("Comparatie tipuri incompatibile");
		}
		// daca s-a generat exceptie sau dupa conversie numele si varsta nu sunt egale 
		// => cele doua instante nu sunt egale
		return false;
	}

	/**
	 * Getter nume
	 * @return numele Studentului
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * Setter nume
	 * @param nume noul nume al Studentului
	 */
	public void setNume(String nume) {
		this.nume = nume;
	}

	/**
	 * Getter varsta
	 * @return varsta Studentului.
	 */
	public int getVarsta() {
		return varsta;
	}

	/**
	 * Setter varsta
	 * @param varsta noua varsta a Studentului.
	 */
	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override 
	public String toString(){ 
		return this.getNume() + " " + this.getVarsta();
	}
	

}
