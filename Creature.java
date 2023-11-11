/* Creature - creatures are anything that has the ability to participate in combat
 * players, mobs, etc.
 */
public interface Creature {
    //Creatures immplementing interface will have an armor class to return which decides how hard they are to hit
    int getAc();
    //returns damage of an attack
    int attack(int ac);
    //rollInitiative returns a value based on the individual creature that decides turn order in combat
    int rollInitiative();
}
