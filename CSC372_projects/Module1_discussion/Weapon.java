package CSC372_projects.Module1_discussion;

public class Weapon {

    protected String weaponType;
    protected String wielderClass;
    protected boolean isRanged;
    
    public void wieldWeapon(){
        System.out.print("You wield the " + weaponType + " weapon!");
    }

    public void unableToWeild(){
        //add method to prevent some weapon weilders from weilding weapons they can't handle
    }




}