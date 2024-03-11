// Executing two Runnables to add elements to a shared SimpleArray.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest {
	public static void main(String[] arg) throws InterruptedException {
		// construct the shared object
		SimpleArray sharedSimpleArray = new SimpleArray(6);

		// create two tasks to write to the shared SimpleArray
		Runnable writer1 = new ArrayWriter(1, sharedSimpleArray);
		Runnable writer2 = new ArrayWriter(11, sharedSimpleArray);

		// Create Threads
		Thread thread1 = new Thread(writer1);
		Thread thread2 = new Thread(writer2);
		
		//Execute the tasks
		thread1.start();
		thread2.start();

		while( thread1.isAlive() || thread2.isAlive() ) {
			Thread.sleep(500);
		}
				
		System.out.printf("%nContents of SimpleArray:%n");
		System.out.println(sharedSimpleArray); // print contents

	} // end main
} // end class SharedArrayTest