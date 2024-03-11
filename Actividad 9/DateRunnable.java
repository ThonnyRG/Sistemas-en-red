
/*DateRunnable does the current date and time printing*/
/*MsgRunnable does the user message printing*/
import java.util.Date;

/*Prints date and time at random intervals.*/
class DateRunnable implements Runnable {
	
	/* current date. */
	private Date date;
	
	/* number of times the message is printed. */
	private static final int TIMES = 10;

	/* constructor for the DateRunnable class. */
	public DateRunnable(Date aDate) {
		date = aDate;
	}

	/* run() is the method that does the thread task. */
	public void run() {
	
		/* the for loop prints the message TIMES times. */
		for (int i = 0; i < TIMES; i++) {
		
			try {
			
				/* create a new Date object */
				/* containing the current date. */
				Date nowDate = new Date();
				
				/* prints the date provided (date) */
				/* and the current date. */
				System.out.println("started:" + date + " now:" + nowDate);
				
				/* generate a random wait interval. */
				int pause = (int) (Math.random() * 3000);
				
				/* the thread will sleep. */
				Thread.sleep(pause);
			
			} catch (InterruptedException e) {
			
				/* print the exception message. */
				System.out.println(e.toString());
			
			} // End try-catch
		} // End for
	} // End run method
} // End DateRunnable class