import java.util.ArrayList;

public class testing {

	public static void main(String[] args) {
//		HashTable ht = new HashTable(4);
////		ht.add(new Tuple(1, "shefang"));
////		ht.add(new Tuple(1, "shefang"));
//		
//		
//		for (int i = 0; i < 10; i++) {
//			
//			ht.add(new Tuple(1, "shefang"));
//			System.out.println(i + ": " + ht.loadFactor() + ", " + ht.size() + ", " + ht.numElements());
//			
//			System.out.println("------------------");
//			
//		}
//		
//
//		ArrayList<Tuple> sertchResult = ht.search(1);
//		System.out.println(sertchResult.size());
//		int numTuples = ht.search(new Tuple(1, "shefang"));
//		System.out.println(numTuples);
//		
//		
//		for (int i = 0; i < 15; i++) {
//			Tuple t = new Tuple(1, "shefang");
//			ht.remove(t);
//			System.out.println(i + ": " + ht.loadFactor() + ", " + ht.size() + ", " + ht.numElements() + 
//					", " + ht.search(t) + 
//					", " + ht.maxLoad() + ", " + ht.averageLoad());			
//			System.out.println("------------------");
//		}
		
		String s0 = "enterkin";
		System.out.println(s0.hashCode());
		
		String s1 = "1268264612"; //aroseisaroseisarose 1268264612
		String s2 = "251188438"; //aroseisaflowerwhichisarose 251188438
		int k = 1;
		HashStringSimilarity hss = new HashStringSimilarity(s1, s2, k);
		System.out.println(hss.lengthOfS1() * hss.lengthOfS1());
		System.out.println(hss.lengthOfS2() * hss.lengthOfS2());
		System.out.println(hss.similarity());
		
		System.out.println("------");
		HashCodeSimilarity hcs = new HashCodeSimilarity(s1, s2, k);
		System.out.println(hcs.lengthOfS1() * hcs.lengthOfS1());
		System.out.println(hcs.lengthOfS2() * hcs.lengthOfS2());
		System.out.println(hcs.similarity());
		
		
		System.out.println("------");
		BruteForceSimilarity bfs = new BruteForceSimilarity(s1, s2, k);
		System.out.println(bfs.lengthOfS1() * bfs.lengthOfS1());
		System.out.println(bfs.lengthOfS2() * bfs.lengthOfS2());
		System.out.println(bfs.similarity());
		
//		ArrayList<Tuple> ls = hcs.getRollOverHashing(s1, k);
//		for (Tuple i:ls){
//			System.out.print(i.getKey()+"; ");
//		}
//		
//		System.out.println("------");
//		
//		for (Tuple i:ls){
//			System.out.print(i.getValue()+", ");
//		}
//		System.out.println("------");
//		
//		for (int i = 0; i <= s1.length() - k; i++) {
//			System.out.print(hcs.StringHashCode(s1.substring(i,  i + k), k)+"; ");
//		}
		
		
		
	}

}
