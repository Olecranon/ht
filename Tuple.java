


public class Tuple {
	private int k;
	private String v;
	private int freq;
	
	
	public Tuple(int keyP, String valueP){
		k = keyP;
		v = valueP;
		freq = 1;
	}
	
	public int getKey(){
		return k;
	}

	public String getValue(){
		return v;
	}
	
	public boolean equals(Tuple t){
		return (t.getKey() == this.k) && (t.getValue().equals(this.v));
	}
	
	public void addFrequency(){
		freq++;
	}

	public void decreaseFrequency(){
		freq--;
	}
	
	public int getFrequency(){
		return freq;
	}
	
	// reference next
	Tuple next;
}
