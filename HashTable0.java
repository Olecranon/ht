import java.util.ArrayList;


// https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
public class HashTable0 {

	private HashFunction hf;
	private Tuple[] ht;
	private int currentSize;
	private int numElements;
	
	HashTable0(int size) {
		
		// find Finds the smallest prime integer p whose value is at least size. 
		int p = findPrime(size);
		hf = new HashFunction(p);
		
		numElements = 0;
		currentSize = p;
		
		//Creates a hash table of size p where each cell initially is NULL
		ht = new Tuple[p];
		for (int i = 0; i < ht.length; i++) {
			ht[i] = null;
		}
	}
	
	
//	maxLoad()	
//	averageLoad()
	public int size() {return currentSize;};
	
	public int numElements() {return numElements;}
	
	public float loadFactor() {
//		return ((float)numElements())/((float)size());
		return (float) (numElements * 1.0 /  currentSize);
	} 

	
	public void	add(Tuple t) {
		
		// places t in the list pointed by the cell hash(t:getKey())
		int htIndex = hf.hash(t.getKey());
		Tuple head = ht[htIndex];
		
		// check if key is already present
		double loadFactorCheck = numElements * 1.0 /  currentSize;
		
		if (head == null) {
			// if not exist, add the tuple t to this index			
			if(loadFactorCheck <= 0.7) {
				ht[htIndex] = t;
				numElements++;
			} else {
				
				//store the tuples to the list
				ArrayList<Tuple> oldTupleList0 = new ArrayList<>();
				for (int i = 0; i < ht.length; i++) {
					Tuple tempTuple = ht[i];
					if(tempTuple != null) {
						oldTupleList0.add(tempTuple);
					}
				}
				
				// now reset the tuple and double its size, let each element be null
				ht = new Tuple[currentSize];
				for (int i = 0; i < ht.length; i++) {
					ht[i] = null;
				}

				
				
			}
			
			
		} else {
			
			
			
			System.out.println("loadFactorCheck:" + loadFactorCheck + ", currentSize:");
			
			if(loadFactorCheck <= 0.7) {
				
				System.out.println("B4 Rehashing1:" + numElements * 1.0 /  currentSize);
				// move to the last element of the chain
				while(head.next != null) {
					
					//the number of distinct Tuples
					if(!head.equals(t)) {
						numElements++;
					}				
					head = head.next;
				}
				
				// insert the tuple t to the last of the chain
				head.next = t;	
			}			
			// if the load factor greater than 0.7, double the size
//			if(loadFactorCheck > 0.7) 
			else {
				
				System.out.println("B4 Rehashing2:" + numElements * 1.0 /  currentSize);
				numElements = 0;
				
				//The size of the new hash table must be: Smallest prime integer whose value is at least twice the current size
				currentSize = findPrime(currentSize * 2);
				
				// the hashfunction also need to be updated
				hf = new HashFunction(currentSize);
				
				
				//find all tuples in the old ht first, and then re-hash them to the new table
				ArrayList<Tuple> oldTupleList = new ArrayList<>();
				for (int i = 0; i < ht.length; i++) {
					Tuple tempTuple = ht[i];
					if(tempTuple != null) {
						oldTupleList.add(tempTuple);
						
						while(tempTuple.next != null) {
							oldTupleList.add(tempTuple.next);
							tempTuple = tempTuple.next;
						}
						
					}
				}
				
				// now reset the tuple and double its size, let each element be null
				ht = new Tuple[currentSize];
				for (int i = 0; i < ht.length; i++) {
					ht[i] = null;
				}
				
				// rehash the old tuple and add them to the new ht
				for (int i = 0; i < oldTupleList.size(); i++) {
					Tuple tuple_need_to_rehash = oldTupleList.get(i);
					tuple_need_to_rehash.next = null;
					
					int htIndex_rehashed = hf.hash(tuple_need_to_rehash.getKey());
					
					Tuple head_rehashed = ht[htIndex_rehashed];
					if (head_rehashed == null) {
						// if not exist, add the tuple t to this index
						ht[htIndex_rehashed] = tuple_need_to_rehash;
						numElements++;
						
					} else {
						
						// move to the last element of the chain
						while(head_rehashed.next != null) {
							
							//the number of distinct Tuples
							if(!head_rehashed.equals(tuple_need_to_rehash)) {
								numElements++;
							}		
							
							head_rehashed = head_rehashed.next;
						}
						
						// insert the tuple t to the last of the chain
						head_rehashed.next = tuple_need_to_rehash;
					}
										
				}
				
				System.out.println("After Rehashing:" + numElements * 1.0 /  currentSize);
			}
			
		}		
	}
//	search(int k)
//	search(Tuple t)
//	remove(Tuple t)
	
	
	
	private int findPrime(int n) {
		boolean found = false;
		int num = n;
		while(!found) {
			if (isPrime(num))
				return num;
			num++;
		}
		return -1;		
	}
	
	private boolean isPrime(int n) {
		for(int i= 2; i<=Math.sqrt(n); i++)
			if (n%i==0)
				return false;
		return true;
	}

}
