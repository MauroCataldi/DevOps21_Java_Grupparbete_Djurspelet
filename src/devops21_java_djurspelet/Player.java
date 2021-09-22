package devops21_java_djurspelet;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private static final int ATSTART_CREDITS = 10000;
    private String mName;
    public int mCredits;
    public ArrayList<AnimalBase> mAnimals;
    public ArrayList<FoodBase> mFoods;

    Player(String name){
        mName = name;
        mCredits = ATSTART_CREDITS;
        mAnimals = new ArrayList<>();
        mFoods = new ArrayList<>();
    }

		public String getName()
		{
			return mName;
		}


    /**
     * Stores any AnimalBase child object sent into the player ArrayList
     * @param pAnimal animal to add to local AnimalBase ArrayList
     * @param pStore store to remove the animal from
     */
    public void buyAnimal(AnimalBase pAnimal, Store pStore){
        if(mCredits >= pAnimal.getPrice()){
            mCredits -= pAnimal.getPrice();
            mAnimals.add(pAnimal);
            pStore.mAnimals.remove(pAnimal);
        }else{
            System.out.println("Du har inte råd att köpa detta djur!");
        }
    }

    /**
     * method sells an animal adding it to store sent and removing it from player
     * @param pAnimal player animal to sell
     * @param pStore store for list adress to send animal to
     */
    public void sellAnimal(AnimalBase pAnimal, Store pStore){
        mCredits += pAnimal.getPrice();
        pStore.mAnimals.add(pAnimal);
        mAnimals.remove(pAnimal);
    }

    public void buyFood(FoodBase pFood){
        if(pFood.getQuantity() != 0){
            System.out.print("Hur mycket "+pFood.getName()+" Vill du köpa?\nAnge mängd i kg:");
            int lQuantity = playerIntChoice();
            if(lQuantity <= pFood.getQuantity()){
                System.out.println("Det kommer kosta: "+(lQuantity * pFood.getPrice())+" Credits");
                System.out.println("Är du säker? 1:fortsätt , allt annat:avbryt");
                if (playerIntChoice()==1) {
                    /*
                      adds obj food into list if not present and sets quantity to amount bought
                      else adds quantity bought onto existing obj in list
                     */
                    if (!mFoods.contains(pFood)) {
                        mFoods.add(pFood);
                        int temp = mFoods.indexOf(pFood);
                        mFoods.get(temp).setQuantity(lQuantity);
                        pFood.removeQuantity(lQuantity);
                    } else {
                        int temp = mFoods.indexOf(pFood);
                        mFoods.get(temp).addQuantity(lQuantity);
                        pFood.removeQuantity(lQuantity);
                    }
                }else{
                        System.out.println("För dyrt för dig?");
                }
            }
        }else{
            System.out.println("Det finns ingen mat att köpa!");
        }
    }

    /**
     * filters out int from players choice through console input
     * catches any other wrongful inputs
     * @return returns chosen integer
     */
    public int playerIntChoice(){
        Scanner scan = new Scanner(System.in);
        boolean badInput = true;
        String lTemp;
        int result = 0;
        while(badInput){
            lTemp = scan.nextLine();
            try{
                result = Integer.parseInt(lTemp);
                badInput = false;
            } catch (Exception e) {
                System.out.println("Endast heltal!\nförsök igen:");
            }
        }
        return result;
    }

    /**
     * prints animal in a horizontal format
     * includes index animal is in
     */
    public void printLivestock(){
        int index = 0;
			if ( mAnimals.isEmpty() ) System.out.println( getName() + " har inga djur." );
        for (AnimalBase temp:mAnimals) {
            System.out.println(index++ +"."+ temp.getName() +"\t");
        }
    }

    /**
     * prints to console the current amount this Player holds
     */
    public void printCredits(){
        System.out.println(mName +" has "+ mCredits + " Credit(s)");
    }

}
