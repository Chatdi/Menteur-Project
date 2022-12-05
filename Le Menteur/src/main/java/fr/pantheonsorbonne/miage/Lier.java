package main.java.fr.pantheonsorbonne.miage;

import java.util.Objects;

public class Lier { 

    //To empty the stack of card
    private static void resetSoC(){
        for(int i=0;i<HoteDeck.stackOfCards.length;i++){
            HoteDeck.stackOfCards[i] = null;
        }
    }

    //create a string that represent an empty hand
    protected String emptyHand(){
        String str = "";
        for(int i=0;i<Player.handSize;i++){
            str+="null ";
        }
        return str;
    }

    //String that are used many times in the code
    private final static String player1Accuse = "Le joueur 1 accuse de mentir. ";
    private final static String player2Accuse = "Le joueur 2 accuse de mentir. ";
    private final static String player3Accuse = "Le joueur 3 accuse de mentir. ";
    private final static String player4Accuse = "Le joueur 4 accuse de mentir. ";
    private final static String player1Lied = "Le joueur 1 avait menti, il récupère la pile de carte";
    private final static String player2Lied = "Le joueur 2 avait menti, il récupère la pile de carte";
    private final static String player3Lied = "Le joueur 3 avait menti, il récupère la pile de carte";
    private final static String player4Lied = "Le joueur 4 avait menti, il récupère la pile de carte";
    private final static String lastCardPlayedString = "sont les dernières cartes joués. ";
    private final static String player1win = "Le joueur 1 a gagné !";
    private final static String player2win = "Le joueur 1 a gagné !";
    private final static String player3win = "Le joueur 1 a gagné !";
    private final static String player4win = "Le joueur 1 a gagné !";
    
    //initialising some variables
    String playingCard;
    boolean truth = true;

    public void main(){
        Player p1 = new Player("Nicolas");
        Player p2 = new Player("Elio");
        Player p3 = new Player("Flavio");
        Player p4 = new Player("Patricio");

        //give to players cards
        p1.setHand(HoteDeck.cardsForYou());
        p2.setHand(HoteDeck.cardsForYou());
        p3.setHand(HoteDeck.cardsForYou());
        p4.setHand(HoteDeck.cardsForYou());

        //the breaks in the function will break this while true
        while(true) {
            
                //announce to player 1 the actual card to play
                System.out.println("Le joueur 1 doit jouer du "+HoteDeck.actualCard);
                //The player 1 plays card/cards and these cards are added to the stack of cards
                HoteDeck.addCardsToList(p1.cardsPlayed());
                //create the string that will represent what the player one announce to play ex: "3 ROI"
                playingCard = p1.countCardPlayed()+" "+HoteDeck.actualCard;
                System.out.println("Le joueur 1 annonce jouer "+playingCard);
                
                //the other players think about accuse to lie or not
                p2.reflexion(playingCard);
                p3.reflexion(playingCard);
                p4.reflexion(playingCard);
                
                //p2 accuse to lie
                if(p2.isLier()){
                    System.out.println(player2Accuse);
                    //see if the last card played are the card that player 1 told he played, if not truth=false
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);

                    //if player 1 told the truth
                    if(truth){
                        System.out.println("Le joueur 1 avait dit la vérité, le joueur 2 récupère la pile de carte");
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    //if player 1 lied
                    else{
                        System.out.println(player1Lied);
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }


                //p3 accuse to lie
                else if(p3.isLier()){
                    System.out.println(player3Accuse);
                     //see if the last card played are the card that player 1 told he played, if not truth=false
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);

                    //if player 1 told the truth
                    if(truth){
                        System.out.println("Le joueur 1 avait dit la vérité, le joueur 3 récupère la pile de carte");
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    //if player 1 lied

                    else{
                        System.out.println(player1Lied);
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }


                //p4 accuse to lie
                else if(p4.isLier()){
                    System.out.println(player4Accuse);
                    //see if the last card played are the card that player 1 told he played, if not truth=false
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);

                    //if player 1 told the truth
                    if(truth){
                        System.out.println("Le joueur 1 avait dit la vérité, le joueur 4 récupère la pile de carte");
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    //if player 1 lied
                    else{
                        System.out.println(player1Lied);
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }
                //actualizing the actual card
                HoteDeck.nextActualCard();
                //verify if a player finish his cards and win
                if(!Objects.equals(p1.getHandString(), emptyHand())){
                    System.out.println(player1win);
                    break;
                }
                else if(!Objects.equals(p2.getHandString(), emptyHand())){
                    System.out.println(player2win);
                    break;
                }
                else if(!Objects.equals(p3.getHandString(), emptyHand())){
                    System.out.println(player3win);
                    break;
                }
                else if(!Objects.equals(p4.getHandString(), emptyHand())){
                    System.out.println(player4win);
                    break;
                }

                  





                System.out.println("Le joueur 2 doit jouer du "+HoteDeck.actualCard);
                HoteDeck.addCardsToList(p2.cardsPlayed());
                playingCard = p2.countCardPlayed()+" "+HoteDeck.actualCard;
                System.out.println("Le joueur 2 annonce jouer "+playingCard);
                
                p1.reflexion(playingCard);
                p3.reflexion(playingCard);
                p4.reflexion(playingCard);
                
                if(p1.isLier()){
                    System.out.println(player1Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 2 avait dit la vérité, le joueur 1 récupère la pile de carte");
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player2Lied);
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p3.isLier()){
                    System.out.println(player3Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 2 avait dit la vérité, le joueur 3 récupère la pile de carte");
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player2Lied);
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p4.isLier()){
                    System.out.println(player4Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 2 avait dit la vérité, le joueur 4 récupère la pile de carte");
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player2Lied);
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }

                HoteDeck.nextActualCard();
                if(!Objects.equals(p1.getHandString(), emptyHand())){
                    System.out.println(player1win);
                    break;
                }
                else if(!Objects.equals(p2.getHandString(), emptyHand())){
                    System.out.println(player2win);
                    break;
                }
                else if(!Objects.equals(p3.getHandString(), emptyHand())){
                    System.out.println(player3win);
                    break;
                }
                else if(!Objects.equals(p4.getHandString(), emptyHand())){
                    System.out.println(player4win);
                    break;
                }






                System.out.println("Le joueur 3 doit jouer du "+HoteDeck.actualCard);
                HoteDeck.addCardsToList(p3.cardsPlayed());
                playingCard = p3.countCardPlayed()+" "+HoteDeck.actualCard;
                System.out.println("Le joueur 3 annonce jouer "+playingCard);
                
                p1.reflexion(playingCard);
                p2.reflexion(playingCard);
                p4.reflexion(playingCard);
                
                if(p1.isLier()){
                    System.out.println(player1Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 3 avait dit la vérité, le joueur 1 récupère la pile de carte");
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player3Lied);
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p2.isLier()){
                    System.out.println(player2Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 3 avait dit la vérité, le joueur 2 récupère la pile de carte");
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player3Lied);
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p4.isLier()){
                    System.out.println(player4Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 3 avait dit la vérité, le joueur 4 récupère la pile de carte");
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player3Lied);
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }

                HoteDeck.nextActualCard();
                if(!Objects.equals(p1.getHandString(), emptyHand())){
                    System.out.println(player1win);
                    break;
                }
                else if(!Objects.equals(p2.getHandString(), emptyHand())){
                    System.out.println(player2win);
                    break;
                }
                else if(!Objects.equals(p3.getHandString(), emptyHand())){
                    System.out.println(player3win);
                    break;
                }
                else if(!Objects.equals(p4.getHandString(), emptyHand())){
                    System.out.println(player4win);
                    break;
                }






                System.out.println("Le joueur 4 doit jouer du "+HoteDeck.actualCard);
                HoteDeck.addCardsToList(p4.cardsPlayed());
                playingCard = p4.countCardPlayed()+" "+HoteDeck.actualCard;
                System.out.println("Le joueur 4 annonce jouer "+playingCard);
                
                p1.reflexion(playingCard);
                p2.reflexion(playingCard);
                p3.reflexion(playingCard);
                
                if(p1.isLier()){
                    System.out.println(player1Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 4 avait dit la vérité, le joueur 1 récupère la pile de carte");
                        p1.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player4Lied);
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p2.isLier()){
                    System.out.println(player2Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 4 avait dit la vérité, le joueur 2 récupère la pile de carte");
                        p2.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player4Lied);
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }



                else if(p3.isLier()){
                    System.out.println(player3Accuse);
                    for(String str: HoteDeck.lastCardPlayed(playingCard)){
                        System.out.println(str+" ");
                        if(!Objects.equals(str, HoteDeck.actualCard)){
                            truth = false;
                        }
                    }
                    System.out.println(lastCardPlayedString);


                    if(truth){
                        System.out.println("Le joueur 4 avait dit la vérité, le joueur 3 récupère la pile de carte");
                        p3.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                    else{
                        System.out.println(player4Lied);
                        p4.receiveCardString(HoteDeck.stackOfCards);
                        resetSoC();
                    }
                }

                HoteDeck.nextActualCard();  
                if(!Objects.equals(p1.getHandString(), emptyHand())){
                    System.out.println(player1win);
                    break;
                }
                else if(!Objects.equals(p2.getHandString(), emptyHand())){
                    System.out.println(player2win);
                    break;
                }
                else if(!Objects.equals(p3.getHandString(), emptyHand())){
                    System.out.println(player3win);
                    break;
                }
                else if(!Objects.equals(p4.getHandString(), emptyHand())){
                    System.out.println(player4win);
                    break;
                }
                





        }
    }
}