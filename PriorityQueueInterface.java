package priorityQueueInterface;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import priorityQueue.Element;
import priorityQueue.PriorityQueueException;

/**
 *  @author Marji-Marchisio-Restaino
 * @param <E>
 */
public interface PriorityQueueInterface<P,E> 
{
	/**
     * Checks if the 
     * @param element of type E is contained in the priority queue
     * @returns true if it is, else otherwise
     */
    boolean containsElement(E element);
    
    /**
     * Returns the size of the priority-queue.
     * @return current size.
     */
    int size();
    
    /**
     * Tests if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     * @throws PriorityQueueException
     */
     boolean isEmpty();
     

     /**
      * @returns the arrayList of all the keys contained within the priority queue
      */
     ArrayList<P> convertToArrayList();
     
     /**
 	 * Methods which inserts an element into the priority-queue
 	 * @param Element<P,E> to insert in the priority-queue
 	 * with {@code Priority P} and {@code Element} E
 	 * @return may return a Position for decreasing the key
 	 * @throws PriorityQueueException
 	 */
     void insert(Element<P, E> element) throws PriorityQueueException;
     
     /**
 	 * Methods which executes the heap-sort algorithm.
 	 * Before that, the methods transforms the current heap structure into a min-heap
 	 * @returns the arraylist with the sorted priority queue
 	 */
     ArrayList<Element<P,E>> heapSort();
     
     /**
      * @param array non-sorted arrayList that will be converted into a minHeap
      */
     void buildMinHeap(ArrayList<Element<P, E>> array);
     
     /**
 	 * Returns the element with the minimum key, without removing it from the
      * queue.
 	 * @return element with minimum key, or null if the queue is empty
 	 * @throws PriorityQueueException
 	 */
     Element<P, E> getMinimum() throws PriorityQueueException;
     
     /**
      * @param element that will be modified
      * @param priority new priority of the element
      */
     void changePriority(E element, P priority) throws NoSuchElementException;
    
}
