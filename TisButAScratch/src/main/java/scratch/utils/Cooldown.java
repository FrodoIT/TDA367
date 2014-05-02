package scratch.utils;

import java.util.concurrent.*;

/**
 * Created by Anna on 2014-05-02.
 */
public class Cooldown {
	private static ScheduledExecutorService schdoodle = Executors.newScheduledThreadPool(1);

	/**
	 * After waiting specified time, it executes the run method in he included runnable class.
	 * @param millis time in millis to wait
	 * @param runnable the class to run after timeout
	 */
	public static void cooldown(int millis, Runnable runnable) {
		schdoodle.schedule(runnable,
				millis,
				TimeUnit.MILLISECONDS
		);
	}
}
