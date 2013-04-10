import java.util.*;


/**
 * Implementarea Interfetei MyHashMap, reprezinta o tabela de dispersie 
 * ce contine functiile put, get, remove, size care au acelasi efect cu cele 
 * din HashMapul din Java.
 * @author Marius Avram
 *
 * @param <K> tipul cheilor
 * @param <V> tipul valorilor
 */
public class MyHashMapImpl<K,V> implements MyHashMap<K,V> {
	
	private int numBuckets;	// numarul de buckets
	private List<MyBucket<K,V>> bucketList; //lista de buckets
	
	
	public MyHashMapImpl(){
		this(20);
	}
	
	/**
	 * Constructorul care primeste ca parametru numarul de bucketuri din tabela.
	 * Instantiaza bucketList si instantiaza fiecare MyBucket din bucketList.
	 * @param numBuckets numarul de bucketuri
	 */
	public MyHashMapImpl(int numBuckets){
		
		this.numBuckets = numBuckets;
		bucketList = new ArrayList<MyBucket<K,V>>();
		for (int i =0; i<numBuckets; i++){ 
			bucketList.add(new MyBucket<K,V>());
		}
	}
	
	/**
	 * Transforma o cheie data in indexul unui MyBucket din bucketList.
	 * Indexul se gaseste in intervalul 0 - numBuckets-1.
	 * @param key cheia care se doreste a fi transformata
	 * @return indexul obtinut
	 */
	public int translateHashCode(K key){ 
		int hashCode = key.hashCode();
		int index = Math.abs(hashCode) % numBuckets;
		return index;
	}
	
	/* (non-Javadoc)
	 * @see MyHashMap#get(java.lang.Object)
	 */
	@Override
	public V get(K key) {
		int index = translateHashCode(key);
		for (Entry<K,V> e: bucketList.get(index).getEntries()){ 
			if ( e.getKey().equals(key) ) { 
				return e.getValue();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see MyHashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = new MyEntry<K,V>(key,value);
		int index = translateHashCode(key);
		MyBucket<K,V> bucket = bucketList.get(index);
		
		// Stergem Entryul daca cheia exista deja
		// Si o memoram intr-o variabila pentru a o returna ulterior
		V oldValue = remove(key);
		
		bucket.addEntry(entry);
		
		return oldValue;
	}

	/* (non-Javadoc)
	 * @see MyHashMap#remove(java.lang.Object)
	 */
	@Override
	public V remove(K key) {
		
		int index = translateHashCode(key);
		MyBucket<K,V> bucket = bucketList.get(index);
		int indexOfKey = -1;
		V oldValue = null;
		
		for (Entry<K, V> e : bucket.getEntries()){ 
			indexOfKey++;
			if (e.getKey().equals(key)){ 
				oldValue = e.getValue();
				bucket.getEntries().remove(indexOfKey);
				break;
			}
		}
		
		
		return oldValue;
		
	}

	/* (non-Javadoc)
	 * @see MyHashMap#size()
	 */
	@Override
	public int size() {
		int size = 0;
		for (int i=0; i<numBuckets; i++){ 
			for (Entry<K,V> e: bucketList.get(i).getEntries()){ 
				size++;
			}
		}
		return size;
	}

	/* (non-Javadoc)
	 * @see MyHashMap#getBuckets()
	 */
	@Override
	public List<? extends Bucket<K, V>> getBuckets() {
		return this.bucketList;
	}
	
	/**
	 * Afiseaza continutul lui MyHashMapImpl. Am folosit metoda pentru a ma 
	 * asigura ca implementarea functioneaza asa cum trebuie (debugging).
	 */
	@Override
	public String toString(){
		String rezultat = " ";
		String ls = System.lineSeparator();
		for (Bucket<K,V> bucket : this.getBuckets()){ 
			for (Entry<K,V> entry : bucket.getEntries()) { 
				rezultat += entry.toString() + " ";
			}
			rezultat +=ls;
		}
		return rezultat;
	}
	
	
	/**
	 * Reprezinta un element din MyHashMap(intrare in tabela de dispersie). 
	 * Elementul este o pereche de tip cheie - valoare.
	 * @author Marius Avram
	 *
	 * @param <K> cheie 
	 * @param <V> valoare
	 */
	public static class MyEntry<K,V> implements Entry<K,V> {
		
		private K key;
		private V value;
		
		/**
		 * Constructor parametrizat
		 * @param key cheia
		 * @param value valoarea
		 */
		public MyEntry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		} 
		
		/**
		 * Afiseaza continutul unei intrari in tabela de dispersie. 
		 * S-a folosit in debugging.
		 */
		@Override
		public String toString(){
			return key.toString() + " " + value.toString();
		}
		
	}
	
	/**
	 * Reprezinta lista cu toate elementele definite de acelasi cod 
	 * de dispersie.
	 * @author Marius Avram
	 *
	 * @param <K> cheie
	 * @param <V> valoare
	 */
	public static class MyBucket<K,V> implements Bucket<K,V> {
		
		private List<Entry<K,V>> entryList;// lista in care tinem toate elemente
		
		/**
		 * Constructor: Se instantiaza lista.
		 */
		public MyBucket(){ 
			this.entryList = new ArrayList<Entry<K,V>>();
		}
		
		/**
		 * Adaugare de element in lista.
		 * @param entry Un element de tip cheia-valoare.
		 */
		public void  addEntry(Entry<K,V> entry){ 
			entryList.add(entry);
		}
		
		/**
		 * Returneaza un element de la un anumit index.
		 * @param index 
		 * @return element de tip cheie - valoare.
		 */
		public Entry<K,V> getEntry(int index){ 
			return entryList.get(index);
		}
		
		/**
		 * Sterge un element de la un anumit index
		 * @param index
		 * @return elementul eliminat de tip cheie - valoare.
		 */
		public Entry<K,V> removeEntry(int index){ 
			Entry<K,V> toBeRemoved = entryList.get(0);
			entryList.remove(index);
			return toBeRemoved;
		}
		
		@Override
		public List<? extends Entry<K, V>> getEntries() {
			return entryList;
		} 
		
	}



}
