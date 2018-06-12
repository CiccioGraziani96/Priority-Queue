package priorityQueue;
/**
 * 
 * @author Marji-Marchisio-Restaino
 * @param <P> element's priority
 * @param <E> element itself
 */

public class Element<P,E>
{
    private E elememt;
    private P priority;

    public Element(P priority, E elememt) 
    {
        this.elememt = elememt;
        this.priority = priority;
    }

    public E getElement() 
    {
        return this.elememt;
    }

    public P getPriority() 
    {
        return this.priority;
    }

    public void setPriority(P priority) 
    {
        this.priority = priority;
    }

    @Override
    public String toString()
    {
        return "{" + this.priority + "," + this.elememt + "}";
    }
}
    