package priorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import priorityQueueInterface.PriorityQueueInterface;


public class PriorityQueue<P,E> extends BinaryHeap implements PriorityQueueInterface<P, E>
{
    private ArrayList<Element<P, E>> priorityQueue;
    private Comparator<Element<P, E>> priorityComparator;
    private HashMap<E, Integer> mapping = new HashMap<>();

    public PriorityQueue(Comparator<Element<P, E>> priorityComparator)
    {
    	this.priorityQueue = new ArrayList<>();
        this.priorityComparator = priorityComparator;
    }
    
    /**
     * Checks if the 
     * @param element of type E is contained in the priority queue
     * @returns true if it is, else otherwise
     */
    public boolean containsElement(E element)
    {
        for(Element<P,E> elem : this.priorityQueue)
            if(elem.getElement().equals(element))
                return true;
        return false;
    }
    
    /**
     * Returns the size of the priority-queue.
     * @return current size.
     */
    public int size()
    {
        return this.priorityQueue.size();
    }
    
    /**
    * Tests if the priority queue is logically empty.
    * @return true if empty, false otherwise.
    * @throws PriorityQueueException
    */
    public boolean isEmpty()
    {
        return this.priorityQueue.isEmpty();
    }

    /**
     * @returns the arrayList of all the keys contained within the priority queue
     */
    public ArrayList<P> convertToArrayList()
    {
        ArrayList<P> arrayKeys = new ArrayList<>();
        for (int i = 0; i < this.priorityQueue.size(); i++)
        	arrayKeys.add(this.priorityQueue.get(i).getPriority());
        return arrayKeys;
    }

    /**
	 * Methods which inserts an element into the priority-queue
	 * @param Element<P,E> to insert in the priority-queue
	 * with {@code Priority P} and {@code Element} E
	 * @return may return a Position for decreasing the key
	 * @throws PriorityQueueException
	 */
    public void insert(Element<P, E> element) throws PriorityQueueException 
    {
        if(element.getPriority()==null) 
        	throw new PriorityQueueException("Key must be not null");
        if(!BinaryHeap.insert(this.priorityQueue, element, priorityComparator, mapping))
            throw new PriorityQueueException("Element already exist");
    }
    
    /**
     * @param array non-sorted arrayList that will be converted into a minHeap
     */
    public void buildMinHeap(ArrayList<Element<P, E>> array) 
    {
        for(Element<P, E> elem : array)
            BinaryHeap.insert(this.priorityQueue, elem, priorityComparator, mapping);
    }
    
    /**
	 * Methods which executes the heap-sort algorithm.
	 * Before that, the methods transforms the current heap structure into a min-heap
	 * @returns the arraylist with the sorted priority queue
	 */
    public ArrayList<Element<P,E>> heapSort()
    {
        return BinaryHeap.heapSort(this.priorityQueue, priorityComparator, mapping);
    }
    
    /**
    * @return the element with the minimum key contained into the priority queue
   	*/
    public Element<P, E> extractMin() throws PriorityQueueException 
    {
        if(this.priorityQueue.size() == 0) 
        	throw new PriorityQueueException("PriorityQueue is empty");
        return BinaryHeap.extractMinimum(this.priorityQueue, priorityComparator, mapping);
    }
    
    /**
     * @param element that will be modified
     * @param priority new priority of the element
     */
    public void changePriority(E element, P priority) throws NoSuchElementException
    {
        Element<P,E> elem = this.priorityQueue.get(mapping.get(element));
        if(elem!=null)
            BinaryHeap.changePriority(this.priorityQueue, elem, new Element<>(priority, null), mapping, priorityComparator);
        else
            throw new NoSuchElementException();
    }
    
    /**
	 * Returns the element with the minimum key, without removing it from the
     * queue.
	 * @return element with minimum key, or null if the queue is empty
	 * @throws PriorityQueueException
	 */
    public Element<P, E> getMinimum() throws PriorityQueueException 
    {
        if(this.priorityQueue.size() == 0) 
        	throw new PriorityQueueException("PriorityQueue is empty");
        return this.priorityQueue.get(0);
    }
    
    @Override
    public String toString() 
    {
        return this.priorityQueue.toString();
    }
}
