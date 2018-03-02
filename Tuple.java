


public class Tuple {
	private int k;
	private String v;
	
	public Tuple(int keyP, String valueP){
		k = keyP;
		v = valueP;
	}
	
	public int getKey(){
		return k;
	}

	public String getValue(){
		return v;
	}
	
	public boolean equals(Tuple t){
		return (t.k == this.k) && (t.v == this.v);
	}
	
	// reference next
	Tuple next;
}
