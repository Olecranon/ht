import java.util.ArrayList;



public class HashTable {
	private HashFunction hf;
	private Tuple[] ht;
	private int currentSize;
	private int numElements;
	private int numElementsWithDuplication;

	
	
	HashTable(int size) {
		
		// find Finds the smallest prime integer p whose value is at least size. 
		int p = findPrime(size);
		hf = new HashFunction(p);
		
		numElements = 0;
		currentSize = p;
		numElementsWithDuplication = 0;
		
		//Creates a hash table of size p where each cell initially is NULL
		ht = new Tuple[p];
		for (int i = 0; i < ht.length; i++) {
			ht[i] = null;
		}
	}

	
	public int maxLoad(){
		
		int maxVal = -1;
		
		for (int i = 0; i < ht.length; i++) {
			Tuple head = ht[i];
			
			// find the load for each hashtable bin
			int binSum = 0;
			while(head != null){
				binSum++;
				head = head.next;
			}
			
			// update the maxload for each bin
			if(binSum >= maxVal){
				maxVal = binSum;
			}
		}
		
		return maxVal;
	}
	
	
	
	
	public float averageLoad(){
		
		int number_of_unempty_Ht_Bins = 0;
		for (int i = 0; i < ht.length; i++) {
			Tuple head = ht[i];
			if (head != null){
				number_of_unempty_Ht_Bins++;
			}
		}
		
		return (float) (numElements * 1.0 / number_of_unempty_Ht_Bins);
	}
	
	
	
	public int size() {return currentSize;};
	
	public int numElements() {return numElements;}
	
	public float loadFactor() {
		return (float) (numElements * 1.0 /  currentSize);
	} 

	
	
	
	public void add(Tuple t) {
		
		numElementsWithDuplication++;
//		System.out.println(numElementsWithDuplication);
		// places t in the list pointed by the cell hash(t:getKey())
		int htIndex = hf.hash(t.getKey());
		
		if (htIndex >= currentSize){
			currentSize = findPrime(htIndex);
			hf = new HashFunction(currentSize);
			numElements = 0;
			
			// now reset the tuple and double its size, let each element be null
			ht = new Tuple[currentSize];
			for (int i = 0; i < ht.length; i++) {
				ht[i] = null;
			}
		}
		
		
		Tuple head = ht[htIndex];
		
		t.next = null;
		
		double loadFactorCheck = numElements * 1.0 /  currentSize;
		
		// check if head is null, just place t to such bin
		if (head == null) {
			ht[htIndex] = t;
			numElements++;
		} else {		
			// else insert the tuple to the linked list
			// first move to the last element of the chain

			boolean duplicatedTupleFlag = false;
			while(head.next != null) {		
				//the number of distinct Tuples
				if(!head.equals(t)) {
					numElements++;
//					head = head.next;
				} else {
					// duplicated tuples, just increase the freq and break;
					head.addFrequency();
					duplicatedTupleFlag = true;
					break;
				}				
				head = head.next;
			}
			
			// insert the tuple t to the last of the chain ONLY if it's not duplicated with others
			if(!duplicatedTupleFlag){
				head.next = t;	
			}			
		}
		
		
		// if the load factor greater than 0.7, double the size & re-hashing
		if(loadFactorCheck > 0.7) {			
			
			numElementsWithDuplication = 0;
			//find all tuples in the old ht first, and then re-hash them to the new table
			ArrayList<Tuple> tempTupleList = new ArrayList<>();
			for (int i = 0; i < ht.length; i++) {
				Tuple tempTuple = ht[i];

				while(tempTuple != null){
					Tuple addedTuple = tempTuple;
					
					// need to reset the next pointer
					addedTuple.next = null;
					tempTupleList.add(addedTuple);
					tempTuple = tempTuple.next;
				}
			}
			
			
			//The size of the new hash table must be: Smallest prime integer whose value is at least twice the current size
			currentSize = findPrime(currentSize * 2);
			
			// the hashfunction also need to be updated
			hf = new HashFunction(currentSize);
			
			numElements = 0;
			
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
				num = num + head.getFrequency();				
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
		
			while(head != null) {
				if(head.equals(t)) {
					break;
				}	
				prev = head;
				head = head.next;
			}
			
			//remove the tuple only if the tuple's frequency is 0, otherwise, just numElements--
			
			int tupleFrequency = head.getFrequency();
			if (tupleFrequency >= 2){
				head.decreaseFrequency();
				
				numElementsWithDuplication--;

				return;
			} else {
				
				// relink the node to remove such tuple
				if(prev != null) {
					prev.next = head.next;
				} else {
					ht[kHashIndex] = head.next;
				}
				
				// since it's the unique tuple, we want decrease the number of elements by 1
				numElements--;
				numElementsWithDuplication--;
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
