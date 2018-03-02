import java.util.ArrayList;

public class testing {

	public static void main(String[] args) {
		HashTable ht = new HashTable(4);
//		ht.add(new Tuple(1, "shefang"));
//		ht.add(new Tuple(1, "shefang"));
		
		
		for (int i = 0; i < 20; i++) {
			
			ht.add(new Tuple(1, "shefang"));
			System.out.println(i + ": " + ht.loadFactor() + ", " + ht.size() + ", " + ht.numElements());
			
			System.out.println("------------------");
			
		}
		
		while(ht.numElements() > 0) {
			ht.remove(new Tuple(1, "shefang"));
			System.out.println(ht.loadFactor() + ", " + ht.size() + ", " + ht.numElements());
		}

		
		
	}

}
