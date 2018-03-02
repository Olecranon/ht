import java.util.ArrayList;

public class BruteForceSimilarity {
	
	
	private String S;
	private String T;
	private int k;
	
	private ArrayList<String> subStringList_S;
	private ArrayList<String> uniqueSubStringList_S;
	private ArrayList<String> subStringList_T;
	private ArrayList<String> uniqueSubStringList_T;	
	
	public BruteForceSimilarity(String s1, String s2, int sLength){
		S = s1;
		T = s2;
		k = sLength;
		
		
		subStringList_S = new ArrayList<>();
		uniqueSubStringList_S = new ArrayList<>();
		
		for (int i = 0; i <= s1.length() - sLength; i++) {
			String subStr = s1.substring(i, i + sLength);
			subStringList_S.add(subStr);
			
			if (!uniqueSubStringList_S.contains(subStr)) {
				uniqueSubStringList_S.add(subStr);
			}			
		}
		
		
		subStringList_T = new ArrayList<>();
		uniqueSubStringList_T = new ArrayList<>();
		for (int i = 0; i <= s2.length() - sLength; i++) {
			String subStr = s2.substring(i, i + sLength);
			subStringList_T.add(subStr);
			
			if (!uniqueSubStringList_T.contains(subStr)) {
				uniqueSubStringList_T.add(subStr);
			}			
		}				
	}
	
	
	public float lengthOfS1(){
		return calculateVectorLength(subStringList_S, uniqueSubStringList_S);
	}
	
	public float lengthOfS2(){	
		return calculateVectorLength(subStringList_T, uniqueSubStringList_T);
	}

	
	public float similarity(){
		
		
		// combine all the element in S and T
		ArrayList<String> copy = new ArrayList<>();
		
		for (String s: uniqueSubStringList_S) {
			copy.add(s);
		}
				
		for (String s:uniqueSubStringList_T) {
			copy.add(s);
		} 		

		// find the union of S1 and S2 after removing the duplicates
		ArrayList<String> U = new ArrayList<>();
		for(String s:copy) {
			if (!U.contains(s)) {
				U.add(s);
			}			
		}
		
		
		// then compute numerators 
		float numerators = 0;
		for (String u:U) {
			
			float f1_i = 0;
			float f2_i = 0;
			
			for (String s1_i: subStringList_S) {
				if(u.equals(s1_i)) {
					f1_i++;
				}
			}
			
			
			for (String s2_i: subStringList_T) {
				if(u.equals(s2_i)) {
					f2_i++;
				}
			}
			
			numerators = numerators + f1_i * f2_i;			
		}
		
		return numerators/(lengthOfS1() * lengthOfS2());
	}
	
	
	private float calculateVectorLength(ArrayList<String>subStringList, 
			ArrayList<String>uniqueSubStringList) {
		
		float val = 0;
		for (int i = 0; i < uniqueSubStringList.size(); i++) {			
			String uniqueStr = uniqueSubStringList.get(i);
			
			int fs_i = 0;
			for (int j = 0; j < subStringList.size(); j++) {
				if (subStringList.get(j).equals(uniqueStr)) {
					fs_i++;
				}
			}
			val = val + fs_i * fs_i;
		}
		
		return (float)Math.sqrt(val);	
	}
	
}
