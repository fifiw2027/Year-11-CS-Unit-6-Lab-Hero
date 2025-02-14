public class Hero {
    //properties (name and hit points)
    private String name;
    private int hitPoints;

    //constructor
    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }

    //getter method getName and getHitPoints
    public String getName(){
        return this.name;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }

    //toString method that returns current state
    public String toString(){
        return "Hero{name=" + "'" + this.name + "'" + ", hitPoints=" + this.hitPoints + "}";
    }

    //attack method
    public void attack(Hero opponent){
        double randomNumber = Math.random();
        if(randomNumber < 0.5){
            opponent.hitPoints -= 10;
        }
        else if(randomNumber >= 0.5){
            this.hitPoints -= 10;
        }
        if(opponent.hitPoints < 0) opponent.hitPoints = 0;
        if (this.hitPoints < 0) this.hitPoints = 0;
    }

    //senzuBean method
    public void senzuBean(){
        hitPoints = 100;
    }

    //fight until the death helper method (private)
    private void fightUntilTheDeathHelper(Hero opponent){
        while (this.hitPoints>0 && opponent.hitPoints>0){
            this.attack(opponent);
        }
    }

    //fight until the death
    public String fightUntilTheDeath(Hero opponent){
        this.senzuBean();
        opponent.senzuBean();
        this.fightUntilTheDeathHelper(opponent);
        return this.name + ": " + this.hitPoints + "   " + opponent.name + ": " + opponent.hitPoints;
    }

    //n fights to the death helper (private)
    private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
        int[] wins = new int[2]; // wins[0] = this Hero's wins, wins[1] = opponent's wins
        for (int i = 0; i < n; i++) {
            this.senzuBean();
            opponent.senzuBean();
            this.fightUntilTheDeathHelper(opponent);
            if (this.hitPoints > 0) {
                wins[0]++;
            } else {
                wins[1]++;
            }
        }
        return wins;
    }

    // n fights to the death
    public String nFightsToTheDeath(Hero opponent, int n) {
        int[] wins = this.nFightsToTheDeathHelper(opponent, n);
        String result = this.name + ": " + wins[0] + " wins    " + opponent.name + ": " + wins[1] + " wins\n";
        if (wins[0] > wins[1]) {
            result += this.name + " wins!";
        } else if (wins[1] > wins[0]) {
            result += opponent.name + " wins!";
        } else {
            result += "OMG! It was actually a draw!";
        }
        return result;
    }

    // Dramatic fight to the death
    public void dramaticFightToTheDeath(Hero opponent) {
        this.senzuBean();
        opponent.senzuBean();
        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            this.attack(opponent);
            System.out.println(this.name + ": " + this.hitPoints + "    " + opponent.name + ": " + opponent.hitPoints);
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.hitPoints > 0) {
            System.out.println(this.name + " wins!");
        } else {
            System.out.println(opponent.name + " wins!");
        }
    }
}
