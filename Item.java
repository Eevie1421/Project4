/**
 * Item objects that can be collected by player. Each item has its own unique functionality
 * @author Evelyn Totman, Salim Jday, Jonathan Murphy
 */

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

    public Item(){
        this(0);
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

    /**
     * prints item to string
     * @return String containing items data
     */
    @Override
    public String toString() {
        return "Item{" +
                "uses=" + uses +
                ", type='" + type + '\'' +
                ", typeNum=" + typeNum +
                '}';
    }

    /**
     * checks if an item is equal to another item
     * @param obj - the object to compare
     * @return boolean - true if objects are equal, false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass().isInstance(new Item())){
            Item temp = (Item) obj;
            return uses == temp.uses && typeNum == temp.typeNum && type.equals(temp.type);
        }
        return false;
    }

    /**
     * returns unique hashcode for item
     * @return int - hashcode for item
     */
    @Override
    public int hashCode() {
        return Objects.hash(uses, type, typeNum);
    }
}
