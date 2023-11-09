public class Boss extends Enemy{
    private boolean specialAttk;
    public Boss(){
        super(75);
        specialAttk = false;
    }
    @Override
    public int attackPlayer(int players) {
        int player = (int)(Math.random() * players);
        if(player == players){
            specialAttk = true;
            player = -1;
        }
        return player;
    }

    @Override
    public int attack1(int ac) {
        int attackRoll = Dice.rollD20(1, 3);
        int damageRoll = 0;
        if(!specialAttk && attackRoll > ac){
            damageRoll = Dice.rollD8(2, 0);
        }
        else if(specialAttk){
            damageRoll = Dice.rollD6(5,0);
            specialAttk = false;
        }
        return damageRoll;
    }
}
