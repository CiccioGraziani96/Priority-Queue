package priorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class BinaryHeap 
{
	protected static <P,E> Element<P,E> extractMinimum(ArrayList<Element<P, E>> priorityQueue, Comparator<Element<P,E>> priorityComparator, HashMap<E, Integer> mapping) throws NoSuchElementException {
        if (priorityQueue.size() == 0) 
            throw new NoSuchElementException();
        if (priorityQueue.size() == 1) 
            return priorityQueue.remove(0);
       
        Element<P,E> first = priorityQueue.get(0);
        mapping.remove(first.getElement());
        
        Element<P,E> newFirst = priorityQueue.get(priorityQueue.size()-1);
        mapping.remove(newFirst.getElement());
        
        priorityQueue.set(0, priorityQueue.remove(priorityQueue.size()-1));
        mapping.put(newFirst.getElement() , 0);
        heapify(priorityQueue, priorityComparator, mapping,0);
        return first;
    }
	
    protected static <P,E> void sort (ArrayList<Element<P, E>> priorityQueue, Comparator<Element<P,E>> priorityComparator, HashMap<E, Integer> mapping, int index) 
    {
        int i = index;
        while(i > 0)
        {
            int middle = (i - 1)/2;
            Element<P,E> element = priorityQueue.get(i);
            Element<P,E> parent = priorityQueue.get(middle);
            if(priorityComparator.compare(element, parent)<0) {
            	mapping.replace(element.getElement(), middle);
            	mapping.replace(parent.getElement(), i);
                priorityQueue.set(i, parent);
                priorityQueue.set(middle, element);
                i = middle;
            } 
            else 
                break;
        }
    }

    protected static <P,E> void heapify(ArrayList<Element<P, E>> priorityQueue, Comparator<Element<P,E>> priorityComparator, HashMap<E,Integer> map, int index) 
    {
        int left = (2*index) + 1;
        while (left < priorityQueue.size()) 
        {
            int max=left;
            int right = left+1;
            if (right < priorityQueue.size()) 
            { // there is a right child
                if (priorityComparator.compare(priorityQueue.get(right), priorityQueue.get(left)) < 0) 
                    max = max + 1;
            }
            if (priorityComparator.compare(priorityQueue.get(index), priorityQueue.get(max)) > 0) 
            {
                // switch
                Element<P,E> temp = priorityQueue.get(index);
                map.replace(priorityQueue.get(index).getElement(), max);
                map.replace(priorityQueue.get(max).getElement(), index);
                priorityQueue.set(index, priorityQueue.get(max));
                priorityQueue.set(max, temp);
                index = max;
                left = (2*index) + 1;
            } 
            else 
                break;
        }
    }
    
    protected static <P,E> boolean insert(ArrayList<Element<P, E>> priorityQueue, Element<P, E> element, Comparator<Element<P,E>> priorityComparator, HashMap<E, Integer> mapping)
    {
        if(mapping.containsKey(element.getElement()) == false) 
        {
        	priorityQueue.add(element);
        	mapping.put(element.getElement(), priorityQueue.size()-1);
            sort(priorityQueue, priorityComparator, mapping, priorityQueue.size()-1);
            return true;
        }
        else
            return false;
    }

    protected static <P,E> ArrayList<Element<P,E>> heapSort(ArrayList<Element<P,E>> priorityQueue, Comparator<Element<P,E>> priorityComparator, HashMap<E, Integer> mapping)
    {
        ArrayList<Element<P,E>> minimumHeap = new ArrayList<>();
        while(priorityQueue.isEmpty() == false)
        	minimumHeap.add(BinaryHeap.extractMinimum(priorityQueue, priorityComparator, mapping));
        return minimumHeap;
    }

    protected static <T,E> void changePriority(ArrayList<Element<T,E>> priorityQueue, Element<T, E> element, Element<T,E> newElementPriority, HashMap<E, Integer> map, Comparator<Element<T,E>> priorityComparator) throws NoSuchElementException{
        if(map.containsKey(element.getElement()) == false)
            throw new NoSuchElementException();
        
        int index = map.get(element.getElement());
        boolean topDown = priorityComparator.compare(priorityQueue.get(index), newElementPriority) > 0;
        priorityQueue.get(index).setPriority(newElementPriority.getPriority());
        
        if(topDown) 
        	sort(priorityQueue, priorityComparator, map, index);
        else 
        	heapify(priorityQueue, priorityComparator, map, index);
    }
}
