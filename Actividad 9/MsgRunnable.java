/*MsgRunnable prints a user message at random
*intervals.*/
class MsgRunnable implements Runnable {
	
	/* message to be printed. */
	private String message;
	
	/* number of times the message is printed. */
	private static final int TIMES = 10;

	/* constructor for the MsgRunnable class. */
	public MsgRunnable(String aMessage) {
		message = aMessage;
	}

	/* run() is the method that does the thread task. */
	public void run() {
		/*
		 * the for loop will iterate TIMES times to print the message.
		 */
		for (int i = 0; i < TIMES; i++) {
	
			try {
			
				/* print the message. */
				System.out.println(message);
				
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
} // End MsgRunnable class