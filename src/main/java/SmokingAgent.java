import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class SmokingAgent extends Thread {

    public Semaphore semaphoreSmoked = new Semaphore(0);
    public Semaphore semaphoreIngredient = new Semaphore(0);
    public String disposedIngredients;

    public CountDownLatch latch;
    public Semaphore semaphoreLatchStart = new Semaphore(0);

    public void run() {
        Random random = new Random();
        int currentIngredients;
        while (true) {
            latch = new CountDownLatch(3);
            semaphoreLatchStart.release(3);
            try {
                latch.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            currentIngredients = random.nextInt(3);
            if (currentIngredients == 0) {
                disposedIngredients = "Paper and Matches";
            }
            if (currentIngredients == 1) {
                disposedIngredients = "Tobacco and Matches";
            }
            if (currentIngredients == 2) {
                disposedIngredients = "Paper and Tobacco";
            }
            System.out.println("Agent puts " + disposedIngredients + " on the table.");
            semaphoreIngredient.release(3);
            try {
                semaphoreSmoked.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}