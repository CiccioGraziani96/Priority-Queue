package priorityQueueMethodTester;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Before;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.Assert.*;
import priorityQueue.PriorityQueue;
import priorityQueue.Element;
import priorityQueue.PriorityQueueException;

/**
 * @author: Marji-Marchisio-Restaino
 */

public class PriorityQueueMethodsTester 
{
    private PriorityQueue<Integer, String> myQueue;
    private Comparator<Element<Integer, String>> priorityComparator;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Before
    public void createClassVariables()
    {
        priorityComparator = new Comparator<Element<Integer, String>>() 
        {
            @Override
            public int compare(Element<Integer, String> o1, Element<Integer, String> o2) 
            {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        };
        myQueue = new PriorityQueue(priorityComparator);
    }
    
    @AfterClass
    public static void tearDown()
    {}
    
    @Test
    public void isEmptyQueueTester() throws PriorityQueueException
    {
        assertTrue(myQueue.isEmpty());
    }

    @Test
    public void priorityQueueSizeTester() throws PriorityQueueException
    {
    	myQueue.insert(new Element<>(47, "Barry Allen"));
        assertFalse(myQueue.isEmpty());
    }


    @Test
    public void buildMinHeapTester() throws PriorityQueueException
    {
        ArrayList<Element<Integer, String>> queue = new ArrayList<>();
        queue.add(new Element<>(26, "Tony Stark"));
        queue.add(new Element<>(58, "Thor"));
        queue.add(new Element<>(47, "Barry Allen"));
        queue.add(new Element<>(77, "Natasha Romanoff"));
        queue.add(new Element<>(19, "Dyana Prince"));
        
        PriorityQueue<Integer, String> coda = new PriorityQueue<>(priorityComparator);
        coda.buildMinHeap(queue);
        
        ArrayList<Integer> array1 = coda.convertToArrayList();
        for(int i=0; i<array1.size(); i++)
        {
            int f = 2*i+1;
            if(f<array1.size())
                assertTrue(array1.get(i)<=array1.get(f));
            int s = 2*i+2;
            if(s<array1.size())
                assertTrue(array1.get(i)<=array1.get(f));
        }
    }

    @Test
    public void getMinimumTester() throws PriorityQueueException
    {
    	myQueue.insert(new Element<>(47, "Barry Allen"));
        myQueue.insert(new Element<>(58, "Thor"));
        myQueue.insert(new Element<>(63, "Tony Stark"));
        int minActual = myQueue.getMinimum().getPriority();
        int minExpected = myQueue.getMinimum().getPriority();
        assertEquals(minExpected, minActual);
    }

    @Test
    public void extractMinimumTester() throws PriorityQueueException
    {
    	myQueue.insert(new Element<>(47, "Barry Allen"));
    	myQueue.insert(new Element<>(58, "Thor"));
    	myQueue.insert(new Element<>(63, "Tony Stark"));
    	myQueue.extractMin();
        int minActual = myQueue.getMinimum().getPriority();
        int minExpected = myQueue.getMinimum().getPriority();
        assertEquals(minExpected, minActual);
    }

    @Test
    public void heapifyTester() throws PriorityQueueException
    {
        ArrayList<Integer> minimumQueue= new ArrayList<>();
       
    	myQueue.insert(new Element<>(47, "Barry Allen"));
    	myQueue.insert(new Element<>(58, "Thor"));
    	myQueue.insert(new Element<>(26, "Tony Stark"));
    	myQueue.insert(new Element<>(77, "Natasha Romanoff"));
    	myQueue.insert(new Element<>(19, "Dyana Prince"));
    	myQueue.insert(new Element<>(17, "Steven Strange"));
    	
        int size = myQueue.size();
        for(int i=0; i < size; i++)
        	minimumQueue.add(myQueue.extractMin().getPriority());

        int arrActual[] = new int[minimumQueue.size()];
        for(int i =0;i < minimumQueue.size();i++)
        	arrActual[i] = minimumQueue.get(i);
        

        int[] arrExpected = new int[]{17,19,26,47,58,77};
        assertArrayEquals(arrExpected, arrActual);
    }

    @Test
    public void insertTester() throws PriorityQueueException
    {     
    	myQueue.insert(new Element<>(47, "Barry Allen"));
    	myQueue.insert(new Element<>(58, "Thor"));
    	myQueue.insert(new Element<>(26, "Tony Stark"));
    	myQueue.insert(new Element<>(77, "Natasha Romanoff"));
    	myQueue.insert(new Element<>(19, "Dyana Prince"));
    	myQueue.insert(new Element<>(17, "Steven Strange"));
        
        ArrayList<Integer> myPriorityArray = myQueue.convertToArrayList();
        for(int i=0; i < myPriorityArray.size(); i++)
        {
            int left = (2 * i) + 1;
            if(left < myPriorityArray.size())
                assertTrue(myPriorityArray.get(i) <= myPriorityArray.get(left));
            int right = (2 * i) + 2;
            if(right <myPriorityArray.size())
                assertTrue(myPriorityArray.get(i) <= myPriorityArray.get(right));
        }
    }

    @Test
    public void insertWithSamePriority() throws PriorityQueueException
    {  
		myQueue.insert(new Element<>(47, "Barry Allen"));
		myQueue.insert(new Element<>(58, "Thor"));
		myQueue.insert(new Element<>(26, "Tony Stark"));
		myQueue.insert(new Element<>(26, "Natasha Romanoff"));
      
		ArrayList<Integer> myPriorityArray = myQueue.convertToArrayList();
        for(int i=0; i < myPriorityArray.size(); i++)
        {
            int left = (2 * i) + 1;
            if(left < myPriorityArray.size())
                assertTrue(myPriorityArray.get(i) <= myPriorityArray.get(left));
            int right = (2 * i) + 2;
            if(right <myPriorityArray.size())
                assertTrue(myPriorityArray.get(i) <= myPriorityArray.get(right));
        }
    }
    
    @Test
    public void heapSortTester() throws PriorityQueueException
    {
    	myQueue.insert(new Element<>(47, "Barry Allen"));
        myQueue.insert(new Element<>(58, "Thor"));
        myQueue.insert(new Element<>(26, "Tony Stark"));
        myQueue.insert(new Element<>(77, "Natasha Romanoff"));
        myQueue.insert(new Element<>(19, "Dyana Prince"));
        myQueue.insert(new Element<>(17, "Steven Strange"));
        
        ArrayList<Element<Integer, String>> arraySorted = myQueue.heapSort();
        int[] arrActual = new int[arraySorted.size()];

        for(int i=0; i < arraySorted.size(); i++)
        	arrActual[i] = arraySorted.get(i).getPriority();
        
        int[] arrExpected = new int[]{17,19,26,47,58,77};
        assertArrayEquals(arrExpected, arrActual);
    }
}