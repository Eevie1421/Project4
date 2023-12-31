/**
 * Enemy is the parent class for any ai monsters in dungeon
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public abstract class Enemy implements Creature{
    private int health;
    private int maxHealth;
    //status is true for alive false for dead
    private boolean status;
    //armor class: higher value = harder to hit
    private int enemyAc;
    //attackMod: modification added to attack rolls
    private int attackMod;

    /**
     * Sets the health, attack mod, and armor class of the creature
     * @param maxHealth - maxHealth of creature
     * @param ac - armor class of creature
     * @param attackMod - attack modifier for creature
     */
    public Enemy(int maxHealth, int ac, int attackMod){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        status = true;
        enemyAc = ac;
        this.attackMod =attackMod;
    }
    public Enemy(){
        this(10, 10, 0);
    }

    //getter methods
    @Override
    public int getAc() {
        return enemyAc;
    }

    public int getAttackMod() {
        return attackMod;
    }

    //setter methods
    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * updateHealth - creature loses health = to the amount given
     * - if health drops to zero or below status is set to false signifying the creature dying.
     * @param damage - the amount of damage taken
     * @return - returns the status of the creature after the damage calculation
     */
    public boolean updateHealth(int damage){
        health = health - damage;
        if(health <= 0){
            status = false;
        }
        return status;
    }

    /**
     * isAlive - checks if the creature is alive
     * @return - returns the current status of the creature
     */
    public boolean checkStatus(){
        return status;
    }

    /**
     * getHealth - checks health of the creature
     * @return - health
     */
    public int getHealth(){
        return health;
    }

    /**
     * attackPlayer will choose which player to attack and return that players number
     */
    public abstract int attackPlayer(int players);

}
