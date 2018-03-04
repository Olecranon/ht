import java.util.ArrayList;

public class HashStringSimilarity {

	private final int ALPHA = 31;
	
	private String S;
	private String T;
	private int k;
	
	private HashTable htS;
	private HashTable htT;
	
	private ArrayList<Tuple> rollingTuple_S;
	private ArrayList<Tuple> rollingTuple_T;
	
	HashStringSimilarity(String s1, String s2, int sLength){
		S = s1;
		T = s2;
		k = sLength;
		
		rollingTuple_S = getRollOverHashing(s1, sLength);
		rollingTuple_T = getRollOverHashing(s2, sLength);
		
		// size must be larger, otherwise, there's bug
		htS = new HashTable(3506511); //43506511
		htT = new HashTable(3506511); //43506511
		
		for(Tuple t: rollingTuple_S){
			htS.add(t);
		}
		
		for(Tuple t: rollingTuple_T){
			htT.add(t);
		}
	}
	
	
	
	public float lengthOfS1(){
		double ans = getVectorLength(rollingTuple_S, htS);
		return  (float) Math.sqrt(ans);
	}
	
	
	public float lengthOfS2(){
		double ans = getVectorLength(rollingTuple_T, htT);
		return (float) Math.sqrt(ans);
	}
	
	
	private double getVectorLength(ArrayList<Tuple> tList, HashTable ht){
		
		ArrayList<Integer> processedHashCode = new ArrayList<>();
		double VectorLength = 0;
		for (Tuple t:tList) {
			if(!processedHashCode.contains(t.getKey())){
				processedHashCode.add(t.getKey());
				
				int f_s_i = ht.search(t);
				VectorLength = VectorLength + f_s_i * f_s_i;
			}
		}
		
		return VectorLength;
	}
	
	
	
	public float similarity(){
		double num = numerator();
		return (float) (num / (lengthOfS1() * lengthOfS2()));
	}
	
	
	// calculate the numerator of similarity function i.e. U be the union of S1 and S2
	private double numerator(){
		ArrayList<Integer> processedHashCode = new ArrayList<>();
		double num = 0;
		
		for (Tuple t: rollingTuple_S){
			if(!processedHashCode.contains(t.getKey())){
				processedHashCode.add(t.getKey());
	
				int s_i = htS.search(t);
				int t_i = htT.search(t);
				num = num + s_i * t_i;
			}	
		}		
		return num;
	}
	
	
	
	
	// get the tuple list using rolling hashing
	public ArrayList<Tuple> getRollOverHashing(String str, int m){
		
		ArrayList<Tuple> ans = new ArrayList<>();
		
		// calcualte a^(m-1)
		int lastPower = 1;
		for (int i = 1; i <= m-1; i++) {
			lastPower = lastPower * ALPHA;
		}
		
		String subStr = str.substring(0, m);
		int N_i_1 = StringHashCode(subStr, m);
		
		ans.add(new Tuple(N_i_1, subStr));
		
		for (int i = 1; i < str.length() - m + 1; i++) {
			int N_i = (N_i_1 - str.charAt(i-1)*lastPower) * ALPHA + str.charAt(i+m-1);
			ans.add(new Tuple(N_i, str.substring(i, i + m)));
			
			N_i_1 = N_i;
		}
		
		return ans;
	}
	
	
	// Compute hash for key[0..m-1]. 
	// https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
	//https://stackoverflow.com/questions/15518418/whats-behind-the-hashcode-method-for-string-in-java
    public int StringHashCode(String key, int m) { 
        int h = 0; 
        for (int j = 0; j < m; j++) 
            h = ALPHA * h + key.charAt(j);
        return h;
    }
}
