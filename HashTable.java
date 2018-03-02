import java.util.ArrayList;



public class HashTable {
	private HashFunction hf;
	private Tuple[] ht;
	private int currentSize;
	private int numElements;

	
	
	HashTable(int size) {
		
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
		return (float) (numElements * 1.0 /  currentSize);
	} 

	
	
	
	public void add(Tuple t) {
		// places t in the list pointed by the cell hash(t:getKey())
		int htIndex = hf.hash(t.getKey());
		Tuple head = ht[htIndex];
		
		double loadFactorCheck = numElements * 1.0 /  currentSize;
		
		// check if head is null, just place t to such bin
		if (head == null) {
			ht[htIndex] = t;
			numElements++;
		} else {		
			// else insert the tuple to the linked list
			// first move to the last element of the chain
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
		
		
		// if the load factor greater than 0.7, double the size & re-hashing
		if(loadFactorCheck > 0.7) {			
			numElements = 0;	
			
			//The size of the new hash table must be: Smallest prime integer whose value is at least twice the current size
			currentSize = findPrime(currentSize * 2);
			
			// the hashfunction also need to be updated
			hf = new HashFunction(currentSize);
			
			
			//find all tuples in the old ht first, and then re-hash them to the new table
			ArrayList<Tuple> tempTupleList = new ArrayList<>();
			for (int i = 0; i < ht.length; i++) {
				Tuple tempTuple = ht[i];
				if(tempTuple != null) {
					tempTupleList.add(tempTuple);
					
					while(tempTuple.next != null) {
						
						Tuple addedTuple = tempTuple.next;
						addedTuple.next = null;
						tempTupleList.add(addedTuple);
						
						tempTuple = tempTuple.next;
					}					
				}
			}
			
			
			// now reset the tuple and double its size, let each element be null
			ht = new Tuple[currentSize];
			for (int i = 0; i < ht.length; i++) {
				ht[i] = null;
			}
			
			for (int i = 0; i < tempTupleList.size(); i++) {
				add(tempTupleList.get(i));
			}			
		}
					
	}
	

	
	public ArrayList<Tuple> search(int k){
		ArrayList<Tuple> result = new ArrayList<Tuple>();
		
		int kHashIndex = hf.hash(k);
		Tuple head = ht[kHashIndex];
		
		while(head != null) {
			if(head.getKey() == k) {
				result.add(head);				
			}
			head = head.next;
		}
		
		return result;
	}
	
	
	
	public int search(Tuple t) {
		int num = 0;		
		int kHashIndex = hf.hash(t.getKey());
		Tuple head = ht[kHashIndex];
		
		while(head != null) {
			if(head.equals(t)) {
				num++;				
			}
			head = head.next;
		}
		return num;
	}
	
	
	
	public void remove(Tuple t) {
		
		int kHashIndex = hf.hash(t.getKey());
		Tuple head = ht[kHashIndex];
		
		Tuple prev = null;
		
		if(head == null) {
			return;
		} else {
		
			//TODO
			while(head != null) {
				if(head.equals(t)) {
					break;
				}	
				prev = head;
				head = head.next;
			}
			
			//remove the tuple
			if(prev != null) {
				prev.next = head.next;
			} else {
				ht[kHashIndex] = head.next;
			}
			
			
			// check the rest of the linkedlist
			// if there is no duplication numElements--, else no change		
			boolean duplicationFlag = false;
			while(head.next != null) {
				if(head.next.equals(t)) {
					duplicationFlag = true;
				}
				head = head.next;
			}
			
			if(!duplicationFlag) {
				numElements--;
			}			
		}
		
	}
	
	
	
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
