import java.util.Objects;

public class Item {
    int uses;
    private String type;
    private int typeNum;

    /* An items type is given by a typeNum when item is initialized.
     * Differentiating a typeNum allows for making methods exclusive to certain item types
     */
    public Item(int typeNum){
        this.typeNum = typeNum;
        if(typeNum == 0){
            type = "Key";
            uses = 1;
        }
        else if(typeNum == 1){
            type = "Health pot";
            uses = 2;
        }
    }

    /**
     * useItem - calls the corresponding items method and returns the value
     */
    public int use(){
        if(uses > 0){
            if(typeNum == 0){
                return unlock();
            }
            else if(typeNum == 1){
                return drinkPot();
            }
        }
        return 0;
    }

    /**
     * Key - Unlocks a door
     * @return - returns 1 to signify unlocking
     */
    public int unlock(){
        uses--;
        return 1;
    }

    /**
     * Health Pot - Heals player for 2d6+2 worth of health.
     * @return - returns the sum of two random numbers between 1-6 if the item has a charge
     */
    public int drinkPot(){
        uses --;
        return -1 * Dice.rollD6(2,2);
    }

    /**
     * Checks if an item has charges
     * @return - true if an item is out of charges false if it still has some.
     */
    public boolean outOfCharges(){
        return uses <= 0;
    }

    public String getType() {
        return type;
    }

    //need implementation
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uses, type, typeNum);
    }
}
