/** Boss - Boss is the class setup to hold the strongest enemy type
 * Boss has a higher health than other enemy types as well as the ability to perform an aoe attack.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public class Boss extends Enemy{
    private boolean specialAttk;
    private String name;
    public Boss(){
        super(75, 15, 3);
        specialAttk = false;
        name = "Fluffy";
    }

    /**
     * attackPlayer - attack player takes in the number of potential targets and returns the number corresponding to that player.
     * - the player being attacked is calculated by generating a number between 0 and players.
     * - if the number generated is = to the number of players the method will set specialAttk to true to signify a special attack.
     * @param players - the number of players in the room.
     * @return int - returns the number of the player which ranges from (0 - players). if -1 is returned it is an aoe attack and hits all.
     */
    @Override
    public int attackPlayer(int players) {
        int player = (int)(Math.random() * (players + 1));
        if(player == players){
            specialAttk = true;
            player = -1;
        }
        return player;
    }

    @Override
    public boolean isALive() {
        return checkStatus();
    }

    /**
     * attack - Boss has two attacks.
     * - the first is the default and occurs when specialAttk is false.
     * - the method rolls a d20 and if it is higher than the ac given as a parameter it rolls 2d8 worth of damage
     * - the second is an aoe attack that deals 3d6 damage to all players and occurs when specialAttk is true.
     * @param ac - the armor class of the player being attacked.
     * @return int - the damage dealt by the attack. 0 if attack missed.
     */
    @Override
    public int attack(int ac) {
        int attackRoll = Dice.rollD20(1, getAttackMod());
        int damageRoll = 0;
        if(!specialAttk && attackRoll > ac){
            damageRoll = Dice.rollD10(2, getAttackMod());
        }
        else if(specialAttk){
            damageRoll = Dice.rollD8(3,0);
            specialAttk = false;
        }
        return damageRoll;
    }

    @Override
    public int rollInitiative() {
        return Dice.rollD20(1, 5);
    }

    @Override
    public String getName() {
        return name;
    }


}
