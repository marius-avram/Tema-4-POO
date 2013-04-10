import java.util.*;

/**
 * Clasa contine cateva metode statice de generare aleatoare a numelor, 
 * varstelor si notelor studentilor.
 * @author Marius Avram
 *
 */
class RandomGenerator { 
	/**
	 * Genereaza un nume aleator. Numele vor fi stringuri de 5 litere
	 * (in intervalul a .. z), fiecare caracter fiind generat aleator 
	 * individual.
	 * @return un string ce poate fi folosit pentru numele unui Student
	 */
	public static String generateNume(){
		int value;
		String rezultat = "";
		// Vom stabili dimensiunea Stringului la 5 
		for (int i=0; i<5; i++){ 
			value = (int)(Math.random() * 26 ) + 97; 
			// codul ascii pentru caracterele din intervalul a..z 
			rezultat += Character.toString(((char)value));
			// transformam din ascii in char si apoi in String.
		}
		return rezultat;
	}
	
	/**
	 * Genereaza o varsta in intervalul 19 - 27.
	 * @return un intreg ce poate fi folosit pentru varsta unui Student
	 */
	public static int generateVarsta(){
		int varsta = (int)(Math.random() * 8) + 19;
		return varsta;
	}
	
	/**
	 * Genereaza o nota in intervalul 1 - 10.
	 * @return un intreg ce poate fi folosit ca nota pentru un Student
	 */
	public static int generateNota(){
		int nota = (int)(Math.random()*9) +1;
		return nota;
	}
}
/**
 * Clasa main. Se fac cele doua teste de performanta.
 * @author Marius Avram
 *
 */
public class MainClass {
	public static void main(String[] args){ 
		List<Student> studentList = new ArrayList<Student>();
		MyHashMapImpl<Student,Integer> map1 = new MyHashMapImpl<Student,Integer>();
		int nrMare = 1500;			// Numarul de studenti generati
		int nrMareAccesari = 10000; // Numarul de accesari
		Student cheie;
		long beforeTime, duration;	
		
		
		System.out.println("---Test1---");
		// Generare lista cu Studenti 
		for(int i=0; i<nrMare; i++){ 
			studentList.add(new Student(RandomGenerator.generateNume(), RandomGenerator.generateVarsta()));
		}
		
		//Introducere elementele din lista in MyHashMap
		for(int i=0; i<nrMare; i++){ 
			map1.put(studentList.get(i), RandomGenerator.generateNota());
		}
		
		//Accesarile pentru Student
		beforeTime = System.currentTimeMillis();
		for(int i=0; i<nrMareAccesari; i++){ 
			cheie = studentList.get((int)(Math.random()*nrMare));
			map1.get(cheie);
		}
		duration = System.currentTimeMillis() - beforeTime;
		// Afisam rezultatul primul test
		System.out.println(nrMareAccesari + 
						   " de accesari ale unui MyHashMap de Student dureaza: " + duration + "ms");
		
		//Aici se pot face afisari pentru a verifica daca introducerile in MyHashMapImpl au avut loc
		//System.out.println(map.toString());
		//System.out.println(map.size());
		
		//System.out.println(studentList);
		
		List<LazyStudent> lazyStudentList = new ArrayList<LazyStudent>();
		MyHashMapImpl<Student,Integer> map2 = new MyHashMapImpl<Student,Integer>();
		
		
		System.out.println("---Test2---");
		// Generare lista cu LazyStudent's
		for(int i=0; i<nrMare; i++){ 
			lazyStudentList.add(new LazyStudent(RandomGenerator.generateNume(), RandomGenerator.generateVarsta()));
		}
		
		// Introducere elementele din lista in MyHashMap
		for(int i=0; i<nrMare; i++){ 
			map2.put(lazyStudentList.get(i), RandomGenerator.generateNota());
		}
		
		//Accesarile pentru Student
		beforeTime = System.currentTimeMillis();
		for(int i=0; i<nrMareAccesari; i++){ 
			cheie = lazyStudentList.get((int)(Math.random()*nrMare));
			map2.get(cheie);
		}
		duration = System.currentTimeMillis() - beforeTime;
		// Afisam rezultatele celui de-al doilea test.
		System.out.println(nrMareAccesari + 
						   " de accesari ale unui MyHashMap de LazyStudent dureaza: " + duration + "ms");
	}
}
