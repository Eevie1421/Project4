/** Creature - creatures are anything that has the ability to participate in combat
 * players, mobs, etc.
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */
public interface Creature {
    //Creatures immplementing interface will have an armor class to return which decides how hard they are to hit
    int getAc();
    //returns damage of an attack
    int attack(int ac);
    //returns name of creature
    String getName();
    //rollInitiative returns a value based on the individual creature that decides turn order in combat
    int rollInitiative();
    //logic for deciding targets
    int attackPlayer(int players);
    //checks if creature is alive
    boolean isALive();
}
