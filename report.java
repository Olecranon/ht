import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class report {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String shak1Path = "./shak1";
		String shak2Path = "./shak2";
		
//		String s1 = new Scanner(new File(shak1Path)).useDelimiter("\\Z").next().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
//		String s2 = new Scanner(new File(shak2Path)).useDelimiter("\\Z").next().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
//		int k = 8;
		
		String s1 = "aroseisaroseisarose"; //aroseisaroseisarose 1268264612
		String s2 = "aroseisaflowerwhichisarose"; //aroseisaflowerwhichisarose 251188438
		int k = 4;
		
//		System.out.println(s1);
//		System.out.println(s2.length());
		
		
		HashStringSimilarity hss = new HashStringSimilarity(s1, s2, k);
		System.out.println(hss.lengthOfS1() * hss.lengthOfS1());
		System.out.println(hss.lengthOfS2() * hss.lengthOfS2());
		System.out.println(hss.similarity());
		
		System.out.println("------");
		HashCodeSimilarity hcs = new HashCodeSimilarity(s1, s2, k);
		System.out.println(hcs.lengthOfS1() * hcs.lengthOfS1());
		System.out.println(hcs.lengthOfS2() * hcs.lengthOfS2());
		System.out.println(hcs.similarity());
		
		
//		System.out.println("------");
//		BruteForceSimilarity bfs = new BruteForceSimilarity(s1, s2, k);
//		System.out.println(bfs.lengthOfS1() * bfs.lengthOfS1());
//		System.out.println(bfs.lengthOfS2() * bfs.lengthOfS2());
//		System.out.println(bfs.similarity());
		
	}

}
