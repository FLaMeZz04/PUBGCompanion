package com.flamezz.pubgcompanion;

public class Items {

    private String name;
    private String image;
    private int capacity;
    private String type;
    private String damage;
    private String description;

Items()
{

}


Items(String name,String image)

{
    this.name=name;
    this.image=image;
}

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void setImage(String image)
    {
        this.image=image;
    }

    public String getImage()
    {
        return image;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
