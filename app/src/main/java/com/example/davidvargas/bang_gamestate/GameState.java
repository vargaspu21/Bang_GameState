package com.example.davidvargas.bang_gamestate;

import com.example.davidvargas.bang_gamestate.objects.Card;
import com.example.davidvargas.bang_gamestate.objects.PlayableCard;
import com.example.davidvargas.bang_gamestate.objects.PlayerInfo;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameState {

    //initializes variables:
    protected ArrayList <Card> drawPile;
    protected ArrayList <Card> discardPile;
    protected int playerTurn, bangsPlayed;
    protected PlayerInfo [] players;
    public static Random rand = new Random ();

    //constructor for GameState:
    public GameState ()
    {
        drawPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        players = new PlayerInfo[4];//can fit four players max
        players[0] = new PlayerInfo();//four new players inserted
        players[1] = new PlayerInfo();
        players[2] = new PlayerInfo();
        players[3] = new PlayerInfo();
        playerTurn = 0;//current players turn
        bangsPlayed = 0;//prevents more than one bang card to be played per turn
        rand.setSeed(System.currentTimeMillis());
        shuffle();
    }

    //a copy constructor:
    public GameState(GameState gs)
    {
        //creates a deep copy of each card in the array list:
        drawPile = new ArrayList<Card>();
        for(Card c: gs.drawPile) this.drawPile.add(c);
        //creates a deep copy of each card in the array list:
        discardPile = new ArrayList<Card>();
        for(Card c: gs.discardPile) this.discardPile.add(c);
        ////creates a deep copy of each card in the array:
        players = new PlayerInfo[4];
        for(int i = 0; i< players.length; i++) this.players[i]= gs.players[i];

        this.playerTurn = gs.playerTurn;
        this.bangsPlayed = gs.bangsPlayed;
    }

    public boolean drawTwo(int player)//draws two cards; player number as identifier
    {
        Random rng = new Random();
        players[player].setCardsInHand(new PlayableCard(false,rng.nextInt(1)));//random card; for now, either bang or beer
        players[player].setCardsInHand(new PlayableCard(false,rng.nextInt(1)));//^
        return true;
    }

    public boolean endTurn(int player)//ends the turn, determines next player
    {
        if(playerTurn != 4) playerTurn ++;
        else playerTurn = 1;
        return true;
    }

    public boolean useAbility(int player, int Ability) //not used yet
    {
        return true;
    }

    public boolean examineCard(Card card)//prints out card description
    {
        System.out.println(card.toString());//for now;prototype
        return true;
    }

    public void quitGame()
    {
        System.exit(0);//exists program, for now;prototype
    }

    public boolean discardCard(Card card,int player)
    {
        if (players[player].getCardsInHand().contains(card))
        {
            players[player].getCardsInHand().remove(card);//delete an instance of card if exists
            discardPile.add(card);//adds card into discard pile
            return true;
        }
        else
        {
            return false;
        }
    }



    //BANG card function:
    public boolean playBANG(int attacker, int target)
    {
        if(bangsPlayed > 1) return false; //checks that player has not previously played BANG card
        for(PlayableCard p: players[attacker].getCardsInHand())//iterates through entire hand of player
        {
            if(p.getCardNum()==0)//if particular card is the cardnumber for bang, use it
            {
                players[target].setHealth(players[target].getHealth()-1); //decreases health of target player
                bangsPlayed++; //increases the count of bangsPlayed by 1

                return true; //returns true, showing that the move was successful
            }
        }
        return false;//after searching through entire hand, if bang card not found, exits

    }

    //Beer card function:
    public boolean playBeer(int player)
    {
        //This card lets a player regain one life point - slide the card so that one more bullet is shown.
        // A player cannot gain more life points than his starting amount! The Beer cards cannot be used to help other players.
        if(players[player].health >= players[player].getMaxHealth()) return false; //checks that user does not surpass the max health
        for(PlayableCard p: players[player].getCardsInHand())//iterates through entire players hand
        {
            if(p.getCardNum()==1)//if cardnum for beer, uses it
            {
                players[player].setHealth(players[player].getHealth()+1); //adds one life point to user
                return true; //returns true, showing that the move was successful
            }
        }
        return false;//else, returns false

    }

    //toString method:
    public String toString()
    {
        String string = "\tDraw pile:\n";
        for(Card c: drawPile) string += "\t\t" + c.toString(); //concatenates strings of the draw pile
        string += "\tDiscard pile:\n";
        for(Card c: discardPile) string += "\t\t" + c.toString(); //concatenates strings of the discard pile
        string += "\tPlayers:\n";
        for(PlayerInfo p: players) string += p.toString() + "\n"; //concatenates strings of players
        string += "\t\tCurrent player turn: "+playerTurn+"\n"; ///concatenates player turn
        string += "\t\tBANGs played: "+bangsPlayed+"\n"; //concatenates current BANGs played
        return string;

    }

    //shuffle method for the drawPile
    public void shuffle()
    {
        Collections.shuffle(drawPile, rand);//makes use of collections object to shuffle arraylist
    }

    //playing a card
    public boolean playFromHandAction(int player, PlayableCard c)
    {
        //check if the player has a card
        if(!(players[player].getCardsInHand().contains(c))) return false;
        //if this is a blue card
        if(c.getIsActive())
        {
            //check if its a duplicate card
            if(players[player].getActiveCards().contains(c)) return false;
            else
            {
                players[player].setActiveCards(c); //add card to active cards
                players[player].getCardsInHand().remove(c); //remove card from hand
                endTurn(player); //end turn
                return true;
            }
        }
        else { //if it is not a blue card
            c.playCard(); //play card using method from playable card class
            players[player].getCardsInHand().remove(c); //remove the card from players hand
            endTurn(player); //end turn
            return true;
        }
    }

}
