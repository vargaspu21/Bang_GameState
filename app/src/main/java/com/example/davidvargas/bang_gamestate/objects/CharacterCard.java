package com.example.davidvargas.bang_gamestate.objects;

public class CharacterCard extends Card {

    //initialize variables
    int baseHealth;
    int ability;

    //constructor:
    public CharacterCard()
    {
        baseHealth = 4;
        ability = -1;
    }

    //constructor that passes in health
    public CharacterCard(int health)
    {
        baseHealth = health;
        ability = -1;
    }

    //getter method for ability
    public int getAbility()
    {
        return ability;
    }

    //setter method for ability
    public void setAbility(int ability)
    {
        this.ability = ability;
    }

    //getter method for base health
    public int getBaseHealth()
    {
        return baseHealth;
    }

    //setter method for base health
    public void setBaseHealth(int baseHealth)
    {
        this.baseHealth = baseHealth;
    }

    //method for the different abilities
    public boolean ability()
    {
        switch(ability)
        {
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                break;

            case 8:
                break;

            case 9:
                break;

            case 10:
                break;

            case 11:
                break;

            case 12:
                break;

            case 13:
                break;

            case 14:
                break;

            case 15:
                break;

            case 16:
                break;

            case 17:
                break;

            case 18:
                break;

            case 19:
                break;

            case 20:
                break;
            default:
                return false;
        }
        return false;
    }


    //toString method:
    public String toString()
    {
        return super.toString()+"Base Health: "+baseHealth+"\n"+ "Ability: "+ability+"\n";
    }
}
