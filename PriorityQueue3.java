import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQueue3 implements PriorityQueueIF<LabelledPoint> {

    // Internal Priority Queue to manage the elements
    private PriorityQueue<LabelledPoint> priorityQueue;
    private int size;
    private int k;

    // Constructor to create an empty priority queue with the specified capacity
    public PriorityQueue3(int initialCapacity) {
        priorityQueue = new PriorityQueue<>(initialCapacity, new CustomComparator());
        this.size = 0;
        this.k = initialCapacity;
    }

    // Constructor to create a priority queue from an ArrayList
    public PriorityQueue3(ArrayList<LabelledPoint> elements, int initialCapacity) {
        this.size = 0;
        this.k = initialCapacity;
        priorityQueue = new PriorityQueue<>(initialCapacity, new CustomComparator());
        for(int i = 0; i<elements.size();i++){
            offer(elements.get(i));    
        }
    }

    @Override
    public boolean offer(LabelledPoint e) {
        if (priorityQueue.size() == k) {
            if (priorityQueue.peek().getKey() > e.getKey()) {
                priorityQueue.poll();
            }
            else{
                return false;
            }
        }
        priorityQueue.offer(e);
        return true;
    }

    @Override
    public LabelledPoint poll() {
        return priorityQueue.poll();
    }

    @Override
    public LabelledPoint peek() {
        return priorityQueue.peek();
    }

    @Override
    public int size() {
        size = priorityQueue.size();
        return size;
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }
}
