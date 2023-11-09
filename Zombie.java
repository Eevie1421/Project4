public class Zombie extends Enemy{
    public Zombie(){
        super(5);
    }

    @Override
    public int attackPlayer(int players) {
        return (int)(Math.random()*(players-1));
    }

    @Override
    public int attack1(int ac) {
        int attackRoll = Dice.rollD20(1,0);
        int damageRoll = 0;
        if(attackRoll >= ac){
            damageRoll = Dice.rollD6(1,0);
        }
        return damageRoll;
    }
}
