package main.java.fr.pantheonsorbonne.miage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Player {
    protected static final int handSize=52-3;
    protected final String[] cardToPlay = {null,null,null,null};
    static HashMap<String, Integer> probLie = new HashMap<>();
    boolean lier = false;
    String actualCard = HoteDeck.actualCard;



    public Player(String name){
    } 

    private String[] hand = new String[49];

    public void setHand(String[] main){
        this.hand=main;
    }

    protected int countCardPlayed(){
        int count = 0;
        for(String c:cardToPlay){
            if(c!=null){
                count++;
            }
        }
        return count;
    }


    protected int indexCard(String str){
        int res = -1;
        for(int i=0;i<HoteDeck.valeur.length;i++){
            if(Objects.equals(HoteDeck.valeur[i], str)){
                res = i;
            }
        }
        return res;
    }
    
    protected String[] handToString(){
        String[] handString = new String[49];
        int index = 0;
        for(String c: hand){
            handString[index++] = c;
        }
        return handString;
    }

    public void addCard(String[] newCard){
        for(String c : newCard){
            for(int i =0;i<this.hand.length;i++){
                if(this.hand[i]==null){
                    this.hand[i]=c;
                    break;
                }
            }
        }
    }

    public String getHandString(){
        String main="";
        for(String c : this.hand){
                main+= c+" ";
        }
        return main;       
    }

    protected void reflexion(String CP){
        cardToPlay[0]=null;
        cardToPlay[1]=null;
        cardToPlay[2]=null;
        cardToPlay[3]=null;
        lier =false;
        int nbCardPlayed = Integer.parseInt(CP.split(" ")[0]);
        String typeCardPlayed = CP.split(" ")[1];
        int prob = 20;
        for(String c: hand){
            if (Objects.equals(c, typeCardPlayed)){
                prob+=20*nbCardPlayed;
            }
        }
        if(probLie.containsKey(typeCardPlayed)){
            int alreadyProb = probLie.get(typeCardPlayed);
            probLie.remove(typeCardPlayed);
            probLie.put(typeCardPlayed,prob+alreadyProb);
        }
        else{
            probLie.put(typeCardPlayed,prob);
        }
    }

    private int nextIndexHand(){
        int i=0;
        while(hand[i]!=null){
            i++;
        }
        return i;
    }

    protected  boolean isLier(){
        int problying = 0;
        int size = 1;
        for (int i:probLie.values()){
            if(i>=100){
                lier=true;
            }
            size++;
            problying+=i;
        }
        problying=problying/size;
        if(problying>80){
            lier=true;
        }
        return lier;
    }


    private void deleteCard(String str){
        for(int i=0;i<nextIndexHand();i++){
            if(Objects.equals(hand[i], str)){
                hand[i]=null;
                break;
            }
        }
    }

    protected String[] cardsPlayed(){
        int index = 0;
        int actualIndexCard = HoteDeck.indexActualCard;
        List<String> handStr = Arrays.asList(handToString());

        if(handStr.contains(actualCard)){
            for(String s:handStr){
                if(Objects.equals(s,actualCard)){
                    cardToPlay[(index++)] = s;
                    deleteCard(s);
                }
            } 
            return cardToPlay;               
        }
        else{
            index=actualIndexCard;
            while(true){
                if(++index>=12){
                index = 0;
                }
                if(handStr.contains(HoteDeck.valeur[index])){
                    for(String s:handStr){
                        if(Objects.equals(s,actualCard)){
                            cardToPlay[0] = s;
                            deleteCard(s);
                            return cardToPlay;
                        }
                    }  
                }
            }
        }
    }

    protected void receiveCardString(String[] RC){
        int index = nextIndexHand();
        for(String s : RC){
            hand[index++] = s;
        }
        
    }

    
}
