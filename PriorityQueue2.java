import java.util.ArrayList;

public class PriorityQueue2 implements PriorityQueueIF<LabelledPoint> {

    // Internal array to represent the max-heap
    private LabelledPoint[] heap;
    private int size;
    private int k;


    // Constructor to create an empty max-heap with the specified capacity
    public PriorityQueue2(int initialCapacity) {
        heap = new LabelledPoint[initialCapacity];
        size = 0;
        this.k=initialCapacity;
    }

    // Constructor to create a max-heap from an ArrayList and with the specified capacity
    public PriorityQueue2(ArrayList<LabelledPoint> elements, int initialCapacity) {
        heap = new LabelledPoint[initialCapacity];
        size =0;
        this.k=initialCapacity;

        for (int i = 0; i < elements.size(); i++) {
           offer(elements.get(i));
           //System.out.println(elements.get(i).getKey());
        }

    }

    @Override
    public boolean offer(LabelledPoint point) {
        if(isEmpty()){
            size++;
            heap[size-1]=point;
            return true;
        }
        if (size == k) {
            // The priority queue is full, so we can't add the element.
            if (point.getKey() < heap[0].getKey()){
                poll(); 
            }
            else {
                return false;
            }
        }

        size++;
        heap[size-1] = point;

        // Perform the up-heap operation to maintain the min-heap property.
        upHeap(size-1);

        return true;
    }

    @Override
    public LabelledPoint poll() {
		if( isEmpty() )
			return null;

	    LabelledPoint ret = heap [ 0 ];

		if( size-1 == 0 ) {
			size=0;
			heap [ 0 ] = null;
            return ret;
		}
        

		heap [ 0 ]      = heap [ size-1 ];
		heap [size-1] = null;
        size--;

		downHeap ( 0 );
        return ret;
	} 

    @Override
    public LabelledPoint peek() {
        if (isEmpty()) {
            return null;
        }
        return heap[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Helper method to percolate a new element up in the max-heap
    private void upHeap(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index].getKey() > heap[parent].getKey()) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Helper method to percolate an element down in the max-heap
    private void downHeap(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && heap[leftChild].getKey() > heap[largest].getKey()) {
            largest = leftChild;
        }
        if (rightChild < size && heap[rightChild].getKey() > heap[largest].getKey()) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(index, largest);
            downHeap(largest);
        }
    }

    // Helper method to swap two elements in the max-heap
    private void swap(int i, int j) {
        LabelledPoint temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    
}