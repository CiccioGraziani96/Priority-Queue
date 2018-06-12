package priorityQueueMethodTester;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import priorityQueueMethodTester.PriorityQueueMethodsTester;

public class PriorityQueue_TestRunner 
{
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(PriorityQueueMethodsTester.class);
		for(Failure failure : result.getFailures())
			System.out.print(failure.toString());
		
		System.out.println(result.wasSuccessful());

	}
}
