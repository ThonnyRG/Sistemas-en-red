import java.util.Date;

/*main thread application.*/
public class RunnableMsgDate {
	
	public static void main(String[] args) {
	
		/* create runnable objects */
		MsgRunnable mr = new MsgRunnable("Hello!");
		DateRunnable dr = new DateRunnable(new Date());
		
		/* create thread objects */
		Thread mt = new Thread(mr);
		Thread dt = new Thread(dr);
		
		/* start threads */
		mt.start();
		dt.start();
	
	} // End main method
} // End RunnableMsgDate