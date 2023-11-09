/* Dice - Simulates rolling of dice.
 * has static methods for d 4,6,8,10,12,20,100.
 */

public class Dice {
    private int type;

    public Dice(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
    public int rollDie(int times){
        int val = 0;
        if(type == 4){
            val = rollD4(times, 0);
        }
        else if(type == 6){
            val = rollD6(times, 0);
        }
        else if(type == 8){
            val = rollD8(times, 0);
        }
        else if(type == 10){
            val = rollD10(times, 0);
        }
        else if(type == 12){
            val = rollD12(times, 0);
        }
        else if(type == 20){
            val = rollD20(times, 0);
        }
        else if (type == 100){
            val = rollD100(times, 0);
        }
        return val;
    }

    public static int rollD4(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 3);
        }
        return val;
    }
    public static int rollD6(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 5);
        }
        return val;
    }
    public static int rollD8(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 7);
        }
        return val;
    }
    public static int rollD10(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 9);
        }
        return val;
    }
    public static int rollD12(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 11);
        }
        return val;
    }
    public static int rollD20(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 19);
        }
        return val;
    }
    public static int rollD100(int number, int modifier){
        int val = 0;
        for(int i = 0; i < number; i++){
            val = val + modifier + (int)(1 + Math.random() * 99);
        }
        return val;
    }
}