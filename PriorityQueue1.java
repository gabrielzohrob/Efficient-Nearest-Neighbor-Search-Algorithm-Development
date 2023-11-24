import java.util.ArrayList;


public class PriorityQueue1 implements PriorityQueueIF<LabelledPoint> {

    private int k; //k used for nearest kNN
    private LabelledPoint[] PQ; // Priority Queue
    private int size;
    
 //Point set


    PriorityQueue1(int k){
        this.k = k;
        this.size = 0;
        this.PQ = new LabelledPoint[k];
    }

    PriorityQueue1(int k, ArrayList<LabelledPoint> elements){
        this.k = k;
        this.size = 0;
        this.PQ = new LabelledPoint[k];

        for(int i = 0; i<elements.size();i++){
            offer(elements.get(i));
        }

    }


    // Inserts the specified element into this queue if it is possible to do so immediately 
    // without violating capacity restrictions.
    public boolean offer(LabelledPoint e) {
        if(isEmpty()){
            PQ[0]=e;
            size++;
            return true;
        }
        if (size < k) {
            // The array is not full, add the element and sort the array
            PQ[size] = e;
            size++;
    
            // Sort the array using insertion sort
            for (int i = 1; i < size; i++) {
                LabelledPoint key = PQ[i];
                int j = i - 1;
                while (j >= 0 && key.getKey() < PQ[j].getKey()) {
                    PQ[j + 1] = PQ[j];
                    j--;
                }
                PQ[j + 1] = key;
            }
            return true;
        } else {
            // The array is full, compare with the last element
            LabelledPoint lastElement = PQ[size - 1];
            if (e.getKey() >= lastElement.getKey()) {
                // The element is not smaller than the last element, so we don't add it
                return false;
            } else {
                // Replace the last element with the new element
                PQ[size - 1] = e;
    
                // Sort the array using insertion sort
                for (int i = size - 1; i > 0 && PQ[i].getKey() < PQ[i - 1].getKey(); i--) {
                    // Swap elements to ensure the array is sorted
                    LabelledPoint temp = PQ[i];
                    PQ[i] = PQ[i - 1];
                    PQ[i - 1] = temp;
                }
                return true;
            }
        }
    }
    
    
    
            
        
    
    
        
// Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public LabelledPoint poll(){
        if(isEmpty()){
            return null;
        }
        LabelledPoint e = PQ[size-1];
        PQ[size-1]=null;
        size--;
        return e;
    }

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    public LabelledPoint peek(){
        return PQ[size-1];
    }



    // Returns the number of elements in this queue.
    public int size(){
        return size;
    }

    // Returns true if this queue contains no elements.
    public boolean isEmpty(){
        return size == 0;
    }
}
   