public class Smoker extends Thread {

    private String name;
    private SmokingAgent agent;
    private String missingIngredients;

    public Smoker(String name, SmokingAgent agent, Ingredients ingredient) {
        this.name = name;
        this.agent = agent;

        switch (ingredient) {
            case PAPER:
                missingIngredients = "Tobacco and Matches";
                break;
            case MATCHES:
                missingIngredients = "Paper and Tobacco";
                break;
            case TOBACCO:
                missingIngredients = "Paper and Matches";
                break;
        }
    }

    public void run() {
        while (true) {
            try {
                agent.semaphoreLatchStart.acquire();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            synchronized (agent.latch) {
                agent.latch.countDown();
            }
            try {
                agent.semaphoreIngredient.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (agent.disposedIngredients.equals(missingIngredients)) {
                System.out.println(name + " makes cigarette and starts smoking...");
                try {
                    sleep(1000);
                    System.out.println(name + " stops smoking.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            agent.semaphoreSmoked.release();
        }
    }
}