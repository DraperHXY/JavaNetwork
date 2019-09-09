package client;

import java.util.Random;

/**
 * @author draper_hxy
 */
public class RandomUserSocketRunnable implements Runnable {
    private final UserSocketRunnable userSocketRunnable;

    public RandomUserSocketRunnable(NetworkInfo networkInfo) {
        UserSocketRunnable userSocketRunnable = new UserSocketRunnable(randomUsername(), networkInfo);
        this.userSocketRunnable = userSocketRunnable;
    }

    @Override
    public void run() {
        userSocketRunnable.run();
    }

    private String randomUsername() {
        Random random = new Random();
        return "用户" + random.nextInt(10000);
    }
}
