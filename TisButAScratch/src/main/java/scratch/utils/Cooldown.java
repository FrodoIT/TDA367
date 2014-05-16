package scratch.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anna on 2014-05-02.
 */
final public class Cooldown {
	private static final ScheduledExecutorService SERVICE = Executors.newScheduledThreadPool(1);

    private Cooldown() {
    }

    /**
	 * After waiting specified time, it executes the run method in he included runnable class.
	 * @param millis time in millis to wait
	 * @param runnable the class to run after timeout
	 */
	public static void cooldown(int millis, Runnable runnable) {
		SERVICE.schedule(runnable,
                millis,
                TimeUnit.MILLISECONDS
        );
	}
}
