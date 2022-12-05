package main.java.fr.pantheonsorbonne.miage;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class HoteDeck {

    final static String[] couleur = {"COEUR","CARREAU","PIQUE","TREFLE"};
    protected static final String[] valeur = {"ACE","ROI","REINE","VALET","10","9","8","7", "6", "5", "4", "3", "2"};
    static List<String> repetition=new ArrayList<>() ;
    static String[] stackOfCards = new String[51]; 
    static int indexActualCard = 12;
    static String actualCard = valeur[indexActualCard];


    public static Card[] randomHand(){
        Random random = new Random();
        Card[] hand = new Card[Player.handSize];

        for(int i =0; i<Player.handSize; i++){
            int indexCouleur = random.nextInt(4);
            int indexcardvaleur = random.nextInt(13);
             
            while(repetition.contains(valeur[indexcardvaleur]+couleur[indexCouleur])){
                indexCouleur = random.nextInt(4);
                indexcardvaleur = random.nextInt(13);
            }
            
            Card carte = new Card(valeur[indexcardvaleur],couleur[indexCouleur]);
            hand[i] = carte;
            repetition.add(valeur[indexcardvaleur]+couleur[indexCouleur]);
            }
       
        return hand;
    }
 
    protected static String[] cardsForYou(){
        Random random = new Random();
        String[] hand = new String[Player.handSize];

        for(int i =0; i<Player.handSize; i++){
            
            int indexCouleur = random.nextInt(4);
            int indexcardvaleur = random.nextInt(13);

            while(repetition.contains(valeur[indexcardvaleur]+couleur[indexCouleur])){
                indexCouleur = random.nextInt(4);
                indexcardvaleur = random.nextInt(13);
            }

            String newCarte = valeur[indexcardvaleur];
            hand[i] = newCarte;
            repetition.add(valeur[indexcardvaleur]+couleur[indexCouleur]);
        }

        return hand;
    }



    protected static String nextActualCard(){
        indexActualCard--;
        if(indexActualCard<0){
            indexActualCard=12;
        }
        actualCard = valeur[indexActualCard];
        return actualCard;
    }


    private static int nextIndexSoC(){
        int i=0;
        while(stackOfCards[i]!=null){
            i++;
        }
        return i;
    }

    protected static String[] addCardsToList(String[] CP){
            for (String c: CP){
                if(c!=null){
                    stackOfCards[nextIndexSoC()]=c;
                }
            }
        return stackOfCards;
    }


    protected static String[] lastCardPlayed(String CP){
        String[] lastCardPlayed = new String[4];
        int nbCardPlayed = Integer.parseInt(CP.split(" ")[0]);
        int index = 0;
        for(int i=nextIndexSoC()-1;i>=nextIndexSoC()-nbCardPlayed-1;i--){
            lastCardPlayed[index++] = stackOfCards[i];
        }
        return lastCardPlayed;
    }



}
    