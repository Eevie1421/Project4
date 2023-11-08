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
            type = "key";
            uses = 1;
        }
        else if(typeNum == 1){
            type = "health pot";
            uses = 2;
        }
    }

    /**
     * Key - If the item is a key it returns true and burns a use. Used to unlock locked doors.
     * @return - true if item is a key and still has a charge
     */
    public boolean unlock(){
        if(typeNum == 0 && !outOfCharges()){
            uses--;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Health Pot - Heals player for 2d6 worth of health.
     * @return - returns the sum of two random numbers between 1-6 if the item has a charge
     */
    public int drinkPot(){
        if(typeNum == 1 && !outOfCharges()){
            uses--;
            return (int)((1 + Math.random() * (5)) + (1 + Math.random() * (5)));
        }
        else return 0;
    }

    /**
     * Checks if an item has charges
     * @return - true if an item is out of charges false if it still has some.
     */
    public boolean outOfCharges(){
        if(uses <= 0){
            return true;
        }
        else{
            return false;
        }
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
