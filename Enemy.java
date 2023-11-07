public abstract class Enemy {
    private int health;
    private int maxHealth;
    private boolean status;

    public Enemy(int maxHealth){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        status = true;
    }
    public Enemy(){
        this(10);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean updateHealth(int damage){
        health = health - damage;
        if(health <= 0){
            status = false;
        }
        return status;
    }

    public boolean isAlive(){
        return status;
    }

    public int checkHealth(){
        return health;
    }

    /**
     * attackPlayer will choose which player to attack and return that players number
     */
    public abstract int attackPlayer(int players);
    public abstract int attack1();
}
