/* Zombie - most basic enemy type.
 *  Zombie has higher health but deals low damage and is very easy to hit
 */
public class Zombie extends Enemy{
    public Zombie(){
        super(12, 8, -1);
    }

    /**
     * attackPlayer - randomly decides which player to attack
     * @param players - the number of players
     * @return int - the number of the player being attacked
     */
    @Override
    public int attackPlayer(int players) {
        return (int)(Math.random()*(players-1));
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
            damageRoll = Dice.rollD6(1,0);
        }
        return damageRoll;
    }

    @Override
    public int rollInitiative() {
        return Dice.rollD20(1, -1);
    }
}
