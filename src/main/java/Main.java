public class Main {
    public static void main(String[] args) {
        SmokingAgent agent = new SmokingAgent();
        Smoker tobaccoSmoker = new Smoker("Tobacco smoker", agent, Ingredients.TOBACCO);
        Smoker paperSmoker = new Smoker("Paper smoker", agent, Ingredients.PAPER);
        Smoker matchesSmoker = new Smoker("Matches smoker", agent, Ingredients.MATCHES);

        agent.start();
        tobaccoSmoker.start();
        paperSmoker.start();
        matchesSmoker.start();
    }
}
