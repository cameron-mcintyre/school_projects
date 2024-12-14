package CSC372_projects.Module1_discussion;

public class PowerSword extends Weapon {

    private int damageLight = 10;
    private int damageHeavy = 30;
    private int damageSpecialAttack = 70;
    
    public void weaponAttack(int attackType){

        if(attackType == 1){
            System.out.println("You attack for " + damageLight + "!");
        } else if(attackType == 2){
            System.out.println("You attack for " + damageHeavy + "!");
        } else if(attackType == 3){
            System.out.println("You attack for " + damageSpecialAttack + "!");
        } else {
            //add an error or something in here
        }
    }
    
}
