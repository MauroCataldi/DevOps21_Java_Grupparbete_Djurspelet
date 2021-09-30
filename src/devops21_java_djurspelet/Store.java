package devops21_java_djurspelet;

import java.util.ArrayList;


/**
* Holds foods and animals for sale
*/
public class Store
{
	static final float ATSTART_QUANTITY_PER_FOOD = 2000.0f; // in kilograms

	String mName;                          // Initialized in the constructor
	public ArrayList<AnimalBase> mAnimals; // Sellable to players
	public ArrayList<FoodBase> mFoods;     // Sellable to players


	/**
	* Creates a store with a name
	*
	* @param pName What is the store called? Is displayed when played enters this store
	*
	* @author P.S.
	*/
	public Store( String pName )
	{
		this.mName = pName;
		this.mAnimals = new ArrayList<>();
		this.mFoods = new ArrayList<>();

		// Create data
		this.mAnimals.add( new Cat() );
		this.mAnimals.add( new Dog() );
		this.mAnimals.add( new Rabbit() );
		this.mAnimals.add( new Cattle() );
		this.mAnimals.add( new Horse() );
		this.mFoods.add( new Forage( ATSTART_QUANTITY_PER_FOOD ) );
		this.mFoods.add( new Carrots( ATSTART_QUANTITY_PER_FOOD ) );
		this.mFoods.add( new DogFood( ATSTART_QUANTITY_PER_FOOD ) );
		this.mFoods.add( new CatFood( ATSTART_QUANTITY_PER_FOOD ) );
	}


	/**
	* Simplifies adding multiple animals of same kind and gender
	*
	* @param pKind    Kind of animals to create
	* @param pGender  Gender of all animals
	* @param pNum     Count
	*
	* @author P.S.
	*/
	public void addAnimalsOfSameKind( AnimalKind pKind, AnimalGender pGender, int pNum )
	{
		if ( pNum > 0 ) switch ( pKind )
		{
			case CAT -> {
				for ( int i = 0; i < pNum; i++ ) this.mAnimals.add( new Cat( pGender ) );
			}
			case CATTLE -> {
				for ( int i = 0; i < pNum; i++ ) this.mAnimals.add( new Cattle( pGender ) );
			}
			case DOG -> {
				for ( int i = 0; i < pNum; i++ ) this.mAnimals.add( new Dog( pGender ) );
			}
			case HORSE -> {
				for ( int i = 0; i < pNum; i++ ) this.mAnimals.add( new Horse( pGender ) );
			}
			case RABBIT -> {
				for ( int i = 0; i < pNum; i++ ) this.mAnimals.add( new Rabbit( pGender ) );
			}
		}

	}


	/**
	 * Greets a player
	 *
	 * @author P.S.
	 */
	protected void displayGreeting()
	{
		System.out.println( "\nVälkommen till " + this.mName );
	}


	/**
	* Displays a nicely formated list
	* Loops through the list av animals available in store
	*
	* @author P.S.
	*/
	protected void displayAnimalInventory()
	{
		// Write to screen kind, health and price of animals
		System.out.println( "\nVi har dessa djur till försäljning :" );

		// Following lines are used to get largest string length of every property in the list, used for formatting
		int lNumLength = 0, lKindLength = 0, lPriceLength = 0;
		for ( int i = 0; i < this.mAnimals.size(); i++ )
		{
			AnimalBase a = this.mAnimals.get( i );
			if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
			if ( a.getKindStr().length() > lKindLength ) lKindLength = a.getKindStr().length();
			if ( Integer.toString( a.getPrice() ).length() > lPriceLength )
				lPriceLength = Integer.toString( a.getPrice() ).length();
		}

		for ( int i = 0; i < this.mAnimals.size(); i++ )
		{
			AnimalBase a = this.mAnimals.get( i );
			String lStr = String.format( "%" + lNumLength + "d  art: %-" + lKindLength + "s   pris: %" + lPriceLength + "d kr", i, a.getKindStr(), a.getPrice() );
			System.out.println( lStr );
		}
	}


	/**
	* Displays a nicely formated list
	* Loops through the list av foods available in store
	*
	* @author P.S.
	*/
	protected void displayFoodInventory()
	{
		// Write to screen name of food, price and how much there is left
		System.out.println( "\nVi har dessa djurfoder till försäljning:" );

		// Following for loop is used to get largest string length of every property in the list, used for formatting
		int lNumLength = 0, lNameLength = 0, lPriceLength = 0;
		for ( int i = 0; i < this.mFoods.size(); i++ )
		{
			FoodBase f = this.mFoods.get( i );
			if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
			if ( f.getName().length() > lNameLength ) lNameLength = f.getName().length();
			if ( f.getPriceStr().length() > lPriceLength ) lPriceLength = f.getPriceStr().length();
		}

		for ( int i = 0; i < this.mFoods.size(); i++ )
		{
			FoodBase f = this.mFoods.get( i );
			String lStr = String.format( "%" + lNumLength + "d  namn: %-" + lNameLength + "s   pris: %" + lPriceLength + "s kr/kg", i, f.getName(), f.getPriceStr() );
			System.out.println( lStr );
		}
	}


	/**
	* Show what animals the player owns
	* Ask if the player wants to buy food for the animals
	* Show a message and wait for a valid input
	* Calls a method that does the actual movement of data
	*
	* @param pPlayer The player object who enters the store
	*
	* @author P.S.
	*/
	protected void playerEntersAnimalBuyStore( Player pPlayer )
	{
		// Say Hi
		this.displayGreeting();

		if ( this.mAnimals.isEmpty() )
		{
			System.out.println( "Affären har inga djur till salu för tillfället." );
		} else
		{
			boolean lPlayerDoneFlag = false;

			do
			{
				// Show what animals the player owns
				pPlayer.printLivestock();
				pPlayer.printFoodOwned();
				pPlayer.printCredits();

				// Show what is available in the store
				this.displayAnimalInventory();

				// Show a message and wait for a valid input
				int lPlayerChoiceInt;
				lPlayerChoiceInt = Game.askForValidNumber( pPlayer.getName() + ", vad vill du köpa?", 0, this.mAnimals.size() - 1 );
				AnimalBase lChosenAnimal = this.mAnimals.get( lPlayerChoiceInt );

				// Create new animal of specified gender
				AnimalBase lNewAnimal;
				if ( Game.askForValidNumber( "Hane(0) eller Hona(1)?", 0, 1 ) == 0 )
					lNewAnimal = lChosenAnimal.createNewWithGender( AnimalGender.MALE );
				else
					lNewAnimal = lChosenAnimal.createNewWithGender( AnimalGender.FEMALE );

				// Show player's choice
				System.out.println( "Spelarens val: " + lNewAnimal.getKindStr() + "(" + lNewAnimal.getGenderStr()+ ")" );

				System.out.println( lNewAnimal.getPrice() + " kommer att dras från ditt konto." );

				if ( Game.askForValidChar( "Vill du fortsätta?", "JN" ).equalsIgnoreCase( "j" ) )
				{
					// Do the actual buy
					pPlayer.buyAnimal( lNewAnimal ); //.getKind()
				}

				// Show what animals the player owns
				pPlayer.printLivestock();
				pPlayer.printCredits();

				// Ask if the player wants to buy an animal
				if ( Game.askForValidChar( pPlayer.getName() + ", vill du köpa fler djur?", "JN" ).equalsIgnoreCase( "n" ) )
					lPlayerDoneFlag = true;

			} while ( !lPlayerDoneFlag );
		}
	}


	/**
	* Show what animals the player owns
	* Ask if the player wants to buy food for the animals
	* Show a message and wait for a valid input
	* Calls a method that does the actual movement of data
	*
	* @param pPlayer The player object who enters the store
	*
	* @author P.S.
	*/
	protected void playerEntersFoodStore( Player pPlayer )
	{
		// Say Hi
		this.displayGreeting();

		if ( this.mFoods.isEmpty() )
		{
			System.out.println( "Affären har ingen mat till salu för tillfället." );
		}
		else
		{
			boolean lPlayerDoneFlag = false;

			do
			{
				// Show what animals the player owns
				pPlayer.printLivestock();
				pPlayer.printFoodOwned();
				pPlayer.printCredits();

				// Show what is available in the store
				this.displayFoodInventory();

				// Show a message and wait for a valid input
				int lPlayerChoiceInt;
				lPlayerChoiceInt = Game.askForValidNumber( "Vad vill du köpa?", 0, this.mFoods.size() - 1 );
				FoodBase lChosenFood = this.mFoods.get( lPlayerChoiceInt );

				lPlayerChoiceInt = Game.askForValidNumber( pPlayer.getName() + ", hur mycket foder vill du köpa?", 0, (int)lChosenFood.getQuantity() );

				FoodBase lNewFood = lChosenFood.createNewWithQuantity( lPlayerChoiceInt );

				// Show player's choice
				System.out.println( "Spelarens val: " + lNewFood.getName() );

				System.out.println( lNewFood.getPriceTotal() + " kommer att dras från ditt konto." );

				if ( Game.askForValidChar( "Vill du fortsätta?", "JN" ).equalsIgnoreCase( "j" ) )
				{
					// Do the actual buy
					pPlayer.buyFood( lNewFood );
				}

				// Show what foods the player owns
				pPlayer.printFoodOwned();

				// Ask if the player wants to buy more food
				if ( Game.askForValidChar( pPlayer.getName() + ", vill du köpa mer djurmat?", "JN" ).equalsIgnoreCase( "n" ) )
					lPlayerDoneFlag = true;

			} while ( !lPlayerDoneFlag );
		}
	}


	/**
	* Show what animals and food the player owns
	* Ask if the player wants to sell an animal
	* Show a message and wait for a valid input
	* Calls a method that does the actual movement of data
	*
	* @author P.S.
	*/
	protected void playerEntersAnimalSellStore( Player pPlayer )
	{
		// Say Hi
		this.displayGreeting();

		// Show what animals the player owns
		pPlayer.printLivestock();

		//pPlayer.printFoodOwned(); irrelevant
		pPlayer.printCredits();

		if ( pPlayer.mAnimals.size() > 0 )
		{
			boolean selling = true;
			while ( selling && pPlayer.mAnimals.size() > 0 )
			{
				// Show a message and wait for a valid input
				int lPlayerChoiceInt = Game.askForValidNumber( "Vad vill du sälja?", 0, pPlayer.mAnimals.size() - 1 );
				AnimalBase lChosenAnimal = pPlayer.mAnimals.get( lPlayerChoiceInt );
				System.out.println( "Spelarens val: " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")" );

				// Do the actual sale
				pPlayer.sellAnimal( lChosenAnimal );

				// Show what's changed
				pPlayer.printCredits();
				pPlayer.printLivestock();

				if ( Game.askForValidChar( "Vill du sälja fler djur?", "jn" ).equals( "N" ) )
				{
					selling = false;
				}
			}
		} else System.out.println( pPlayer.getName() + ", du äger inga djur." );
	}

}
