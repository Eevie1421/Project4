/** Zombie - most basic enemy type.
 *  Zombie has higher health but deals low damage and is very easy to hit
 *  @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public class Zombie extends Enemy{
    private String name;
    public Zombie(){
        super(12, 10, 1);
        name = "Zombie";
    }

    /**
     * attackPlayer - randomly decides which player to attack
     * @param players - the number of players
     * @return int - the number of the player being attacked
     */
    @Override
    public int attackPlayer(int players) {
        return (int)(Math.random()*(players));
    }

    @Override
    public boolean isALive() {
        return checkStatus();
    }

    /**
     * attack - Makes an attack roll with a d20. if the number is higher than the given ac zombie
     * deals 1d6 damage
     * @param ac - the armor class of the player being attacked.
     * @return int - the damage of the attack. 0 if it missed
     */
    @Override
    public int attack(int ac) {
        int attackRoll = Dice.rollD20(1, getAttackMod());
        int damageRoll = 0;
        if(attackRoll >= ac){
            damageRoll = Dice.rollD10(1,getAttackMod());
        }
        return damageRoll;
    }

    /**
     * rollInitiative - Rolls for turn order.
     * @return int - rolls for initiative with -1 modifier.
     */
    @Override
    public int rollInitiative() {
        return Dice.rollD20(1, -1);
    }

    /**
     * returns name of the creature
     * @return String - creatures name.
     */
    @Override
    public String getName() {
        return name;
    }
}
