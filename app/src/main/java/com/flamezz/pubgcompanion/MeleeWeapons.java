package com.flamezz.pubgcompanion;

public class MeleeWeapons
{
   private String name;
    private   String type;
   private String capacity;
   private String damage;



    MeleeWeapons()
    {

    }

    MeleeWeapons(String name,String type,String capacity,String damage)

    {
        this.name=name;
        this.type=type;
        this.capacity=capacity;
        this.damage=damage;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}



