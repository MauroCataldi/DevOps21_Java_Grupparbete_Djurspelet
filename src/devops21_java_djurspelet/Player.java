package devops21_java_djurspelet;

import java.util.ArrayList;
import java.util.Iterator;


public class Player
{

	private static final int ATSTART_CREDITS = 100000;
	private String mName;
	public int mCredits;
	public ArrayList<AnimalBase> mAnimals;
	public ArrayList<FoodBase> mFoods;


	Player( String name )
	{
		this.mName = name;
		this.mCredits = ATSTART_CREDITS;
		this.mAnimals = new ArrayList<>();
		this.mFoods = new ArrayList<>();
	}


	Player( String name, int pNumCredits ) throws IllegalArgumentException
	{
		if ( pNumCredits <= 0 ) throw new IllegalArgumentException();
		this.mName = name;
		this.mCredits = pNumCredits;
		this.mAnimals = new ArrayList<>();
		this.mFoods = new ArrayList<>();
	}


	/**
	 * lets you choose how many of received animals to purchase
	 *
	 * @param pAnimal animal to purchase
	 */
	public void buyAnimal( AnimalBase pAnimal )
	{
		if ( this.mCredits >= pAnimal.getPrice() )
		{
			this.mCredits -= pAnimal.getPrice();

			pAnimal.setName( Game.askForValidName( "Döp ditt djur!" ) );
			this.mAnimals.add( pAnimal );
		}
		else
		{
			System.out.println( "Du har inte råd!" );
		}

//		boolean lLoop = true;
//		while ( lLoop )
//		{
//			int animalAmount = Game.askForValidNumber( "Välj hur många djur du vill köpa: ", 1, 1000 );
//			if ( mCredits >= pAnimal.getPrice() * animalAmount )
//			{
//				mCredits -= pAnimal.getPrice() * animalAmount;
//				for ( int i = 0; i < animalAmount; i++ )
//				{
//					mAnimals.add( pAnimal.createChild() );
//					mAnimals.get( mAnimals.size() - 1 ).setName( Game.askForValidName( "Döp ditt djur!" ) );
//				}
//				lLoop = false;
//			}
//			else
//			{
//				System.out.println( "Du har inte råd!" );
//				switch ( Game.askForValidChar( "Avbryt köp?", "jn" ) )
//				{
//					case "J":
//						lLoop = false;
//						break;
//					case "N":
//				}
//			}
//		}
	}


	/**
	 * sells animal to no recipient
	 *
	 * @param pAnimal sells animal for equivalent value
	 */
	public void sellAnimal( AnimalBase pAnimal )
	{
		this.mCredits += pAnimal.getPrice();
		this.mAnimals.remove( pAnimal );
	}


	public void sellAll()
	{
		Iterator<AnimalBase> i = this.mAnimals.iterator();
		while ( i.hasNext() )
		{
			AnimalBase a = i.next();
			this.mCredits += a.getPrice();
			i.remove();
		}
	}


	/**
	* Receives food from store
	* Checks if there's enough credits, cancels the purchase if not
	* Adds the food quantity to existing object, creates new if it does not exist
	*
	* @param pFood food object to purchase of
	*/
	public void buyFood( FoodBase pFood )
	{
		// Has this player enough credits
		if ( this.mCredits >= pFood.getPriceTotal() )
		{
			// Decreas the credits by the price of the animal
			this.mCredits -= pFood.getPriceTotal();

			// Search for existing right food
			int lFoundFoodIndex = -1;
			for ( int i = 0; i < this.mFoods.size(); i++ )
			{
				if ( pFood.getName().equalsIgnoreCase( this.mFoods.get( i ).getName() ) )
				{
					lFoundFoodIndex = i;
					break;
				}
			}

			// Was found?
			if ( lFoundFoodIndex < 0 )
			{
				// Not found, add new
				this.mFoods.add( pFood.createNewWithQuantity( pFood.getQuantity() ) );
			}
			else
			{
				// Found, add quantity to existing
				this.mFoods.get( lFoundFoodIndex ).setQuantity( this.mFoods.get( lFoundFoodIndex ).getQuantity() + pFood.getQuantity() );
			}

//		if ( pFood.getQuantity() != 0 )
//		{
//			System.out.println( "Hur mycket " + pFood.getName() + " Vill du köpa?" );
//			int lQuantity = Game.askForValidNumber( "Ange kg: ", 1, 10000 );
//			if ( lQuantity <= pFood.getQuantity() )
//			{
//				System.out.println( "Det kommer kosta: " + ( lQuantity * pFood.getPrice() ) + " Credits" );
//				if ( Game.askForValidChar( "Är du säker? j/n", "jn" ).equalsIgnoreCase( "j" ) )
//				{
//					if ( mCredits > lQuantity * pFood.getPrice() )
//					{
//                    /*
//                      adds obj food into list if not present and sets quantity to amount bought
//                      else adds quantity bought onto existing obj in list
//                     */
//						if ( !mFoods.contains( pFood ) )
//						{
//							mFoods.add( pFood );
//							int temp = mFoods.indexOf( pFood );
//							mFoods.get( temp ).setQuantity( lQuantity );
//							//pFood.removeQuantity(lQuantity); unintended effect
//						} else
//						{
//							int temp = mFoods.indexOf( pFood );
//							mFoods.get( temp ).addQuantity( lQuantity );
//							pFood.removeQuantity( lQuantity );
//						}
//					} else
//					{
//						System.out.println( "Du har inte råd!" );
//					}
//				} else
//				{
//					System.out.println( "För dyrt för dig?" );
//				}
//			}
//		} else
//		{
//			System.out.println( "Det finns ingen mat att köpa!" );
//		}
		}
		else
			System.out.println( "Du har inte råd!" );
	}


	/**
	 * Makes all animals the player hold age (take health damage)
	 */
	public void decayAnimalsOwned()
	{
		if ( !mAnimals.isEmpty() )
		{
			for ( AnimalBase animal: mAnimals )
			{
				animal.growOlder();
			}
		}
	}


	/**
	 * Prints out list of animals in a vertical format
	 * includes index animal is at
	 */
	public void printLivestock()
	{
		System.out.println();
		//int index = 0;
		System.out.println( "I " + this.mName + "s djurbestånd finns det:" );
		if ( this.mAnimals.isEmpty() ) System.out.println( getName() + " har inga djur." );
		else
		{
			// This for loop is used to get largest string length of every property in the list, used for formatting
			int lNumLength = 0, lKindLength = 0, lHealthLength = 0, lGenderLength = 0, lPriceLength = 0;
			for ( int i = 0; i < this.mAnimals.size(); i++ )
			{
				AnimalBase a = this.mAnimals.get( i );
				if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
				if ( ( a.getKindStr() + "(" + a.getName() + ")" ).length() > lKindLength )
					lKindLength = ( a.getKindStr() + "(" + a.getName() + ")" ).length();
				if ( a.getGenderStr().length() > lGenderLength ) lGenderLength = a.getGenderStr().length();
				if ( a.getHealthFullStr().length() > lHealthLength ) lHealthLength = a.getHealthFullStr().length();
				if ( Integer.toString( a.getPrice() ).length() > lPriceLength )
					lPriceLength = Integer.toString( a.getPrice() ).length();
			}

			for ( int i = 0; i < this.mAnimals.size(); i++ )
			{
				AnimalBase a = this.mAnimals.get( i );
				String lStr = String.format( "%" + lNumLength + "d.   art: %-" + lKindLength + "s   kön: %-" + lGenderLength + "s   hälsa: %" + lHealthLength + "s   pris: %" + lPriceLength + "d kr", i, a.getKindStr() + "(" + a.getName() + ")", a.getGenderStr(), a.getHealthFullStr(), a.getPrice() );
				System.out.println( lStr );
			}
		}
	}


	public void printLivestockInList( ArrayList<AnimalBase> pWhichAnimalList )
	{
		if ( pWhichAnimalList.isEmpty() ) System.out.println( "I listan finns inga djur." );
		else
		{
			System.out.println( "I listan finns dessa djur:" );

			// This for loop is used to get largest string length of every property in the list, used for formatting
			int lNumLength = 0, lKindLength = 0, lHealthLength = 0, lGenderLength = 0, lPriceLength = 0;
			for ( int i = 0; i < pWhichAnimalList.size(); i++ )
			{
				AnimalBase a = pWhichAnimalList.get( i );
				if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
				if ( ( a.getKindStr() + "(" + a.getName() + ")" ).length() > lKindLength )
					lKindLength = ( a.getKindStr() + "(" + a.getName() + ")" ).length();
				if ( a.getGenderStr().length() > lGenderLength ) lGenderLength = a.getGenderStr().length();
				if ( a.getHealthFullStr().length() > lHealthLength ) lHealthLength = a.getHealthFullStr().length();
				if ( Integer.toString( a.getPrice() ).length() > lPriceLength )
					lPriceLength = Integer.toString( a.getPrice() ).length();
			}

			// Write to screen the list
			for ( int i = 0; i < pWhichAnimalList.size(); i++ )
			{
				AnimalBase a = pWhichAnimalList.get( i );
				String lStr = String.format( "%" + lNumLength + "d.   art: %-" + lKindLength + "s   kön: %-" + lGenderLength + "s   hälsa: %" + lHealthLength + "s   pris: %" + lPriceLength + "d kr", i, a.getKindStr() + "(" + a.getName() + ")", a.getGenderStr(), a.getHealthFullStr(), a.getPrice() );
				System.out.println( lStr );
			}
		}
	}


	/**
	 * Prints the food and amount of said food the player holds in their supply
	 */
	public void printFoodOwned()
	{
		System.out.println();
		System.out.println( "I " + this.mName + "s matförråd finns det:" );
		if ( !this.mFoods.isEmpty() )
		{
			// The following for loop is used to get largest string length of every property in the list, used for formatting
			int lNumLength = 0, lNameLength = 0, lPriceLength = 0, lQuantityLength = 0;
			for ( int i = 0; i < this.mFoods.size(); i++ )
			{
				FoodBase f = this.mFoods.get( i );
				if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
				if ( f.getName().length() > lNameLength ) lNameLength = f.getName().length();
				if ( f.getPriceStr().length() > lPriceLength ) lPriceLength = f.getPriceStr().length();
				if ( f.getQuantityStr().length() > lQuantityLength ) lQuantityLength = f.getQuantityStr().length();
			}

			for ( int i = 0; i < this.mFoods.size(); i++ )
			{
				FoodBase f = this.mFoods.get( i );
				String lStr = String.format( "%" + lNumLength + "d.   namn: %-" + lNameLength + "s   pris: %" + lPriceLength + "s kr/kg   mängd: %" + lQuantityLength + "s kg", i, f.getName(), f.getPriceStr(), f.getQuantityStr() );
				System.out.println( lStr );
			}
		} else
		{
			System.out.println( this.mName + " du har ju ingen mat!" );
		}
	}


	/**
	* @param pWhichFoodlList  List to show on screen
	*
	* @author P.S.
	*/
	public void printFoodInList( ArrayList<FoodBase> pWhichFoodlList )
	{
		if ( pWhichFoodlList.isEmpty() ) System.out.println( "Listan är tom." );
		else
		{
			System.out.println( "I listan finns:" );

			// The following for loop is used to get largest string length of every property in the list, used for formatting
			int lNumLength = 0, lNameLength = 0, lPriceLength = 0, lQuantityLength = 0;
			for ( int i = 0; i < pWhichFoodlList.size(); i++ )
			{
				FoodBase f = pWhichFoodlList.get( i );
				if ( Integer.toString( i ).length() > lNumLength ) lNumLength = Integer.toString( i ).length();
				if ( f.getName().length() > lNameLength ) lNameLength = f.getName().length();
				if ( f.getPriceStr().length() > lPriceLength ) lPriceLength = f.getPriceStr().length();
				if ( f.getQuantityStr().length() > lQuantityLength ) lQuantityLength = f.getQuantityStr().length();
			}

			// Write to screen the list
			for ( int i = 0; i < pWhichFoodlList.size(); i++ )
			{
				FoodBase f = pWhichFoodlList.get( i );
				String lStr = String.format( "%" + lNumLength + "d.   namn: %-" + lNameLength + "s   pris: %" + lPriceLength + "s kr/kg   mängd: %" + lQuantityLength + "s kg", i, f.getName(), f.getPriceStr(), f.getQuantityStr() );
				System.out.println( lStr );
			}
		}
	}


	/**
	 * prints to console the current amount this Player holds
	 */
	public void printCredits()
	{
		System.out.println( this.mName + " har " + this.mCredits + " Credit(s)" );
	}


	/**
	*
	* @return Thre balance in credits the player is holding
	*/
	public int getCredits()
	{
		return mCredits;
	}


	public String getName()
	{
		return mName;
	}


	/**
	* Show a list of animals the player owns
	* Let the player select an animal
	* Let the player select another animal of same kind but different gender
	* Create new animal instance using the keyword new and the right constructor, there are two
	* Add the newly crated animal to player's list of animals
	*
	* @author P.S.
	*/
	public void tryAnimalBreeding()
	{
		System.out.println( "\n" + getName() + " ska nu försöka para sina djur." );

		// Cannot choose an animal from an empty list
		if ( this.mAnimals.size() > 0 )
		{
			boolean lPlayerDoneFlag = true;//false;
			int lPlayerChoiceInt;

			do // Until player is done. Only once if lPlayerDoneFlag is true
			{
				this.printLivestock();
				this.printFoodOwned();

				// Ask which animal the player wants to breed
				lPlayerChoiceInt = Game.askForValidNumber( getName() + ", vilket djur vill du para?", 0, this.mAnimals.size() - 1 );

				// Store the animal
				AnimalBase lChosenAnimal = this.mAnimals.get( lPlayerChoiceInt );

				// Show which animal the player has chosen to breed
				System.out.println( getName() + ", vill para sin " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")." );

				// Create a temporary list of animals of same kind but other gender and skip same animal
				ArrayList<AnimalBase> lNewAnimalList = new ArrayList<>();
				for ( AnimalBase a: this.mAnimals )
				{
					// Adds compatible animal to the temporary list
					if ( lChosenAnimal.canMateWith( a ) ) lNewAnimalList.add( a );
				}

				// Where there any compatible animals
				if ( lNewAnimalList.size() > 0 )
				{
					this.printLivestockInList( lNewAnimalList );

					// Player selects another animal of same kind but different gender
					lPlayerChoiceInt = Game.askForValidNumber( getName() + ", vilket djur vill du para?", 0, lNewAnimalList.size() - 1 );

					// Store the animal
					AnimalBase lOtherAnimal = lNewAnimalList.get( lPlayerChoiceInt );

					// Make the animal do the actual mating and get a list of newborn animals if mating was successful
					ArrayList<AnimalBase> lNewOffspringList = lChosenAnimal.tryMateWith( lOtherAnimal );

					// Tell player how many new animals
					System.out.println( "Fått " + lNewOffspringList.size() + " nytt/nya djur." );

					// If breeding was successful
					// Let player name new animals
					// Show total number of animals
					if ( lNewOffspringList.size() > 0 )
					{
						for ( AnimalBase a : lNewOffspringList )
						{
							a.setName( Game.askForValidName( "Döp ditt djur art: " + a.getKindStr() + "   kön: " + a.getGenderStr() + "." ) );
						}

						// Add offspring to livestock list
						this.mAnimals.addAll( lNewOffspringList );

						this.printLivestock();
					}

					lNewOffspringList.clear();

					// Ask if the player wants to breed another animal
					if ( !lPlayerDoneFlag && Game.askForValidChar( getName() + ", vill du para ett djur till?", "JN" ).equalsIgnoreCase( "n" ) )
						lPlayerDoneFlag = true;
				} else
					System.out.println( getName() + ", du har inga djur som du kan para " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ") med." );
			} while ( !lPlayerDoneFlag );
		}
	}


	/**
	* Show a list of animals the player owns
	* Let the player select an animal
	* Show a list of foods
	* Let the player select food
	* Feed the animal
	*
	* @author P.S.
	*/
	public void tryAnimalFeeding()
	{
		System.out.println( "" );
		System.out.println( getName() + " ska nu mata sina djur." );

		// Cannot choose an animal from an empty list
		if ( this.mAnimals.size() > 0 )
		{
			boolean lPlayerDoneFlag = false;
			int lPlayerChoiceInt;

			do
			{
				this.printLivestock();
				this.printFoodOwned();

				// Ask which animal the player wants to feed
				lPlayerChoiceInt = Game.askForValidNumber( getName() + ", vilket djur vill du mata?", 0, this.mAnimals.size() - 1 );
				AnimalBase lChosenAnimal = this.mAnimals.get( lPlayerChoiceInt );

				// Show which animal the player has chosen to feed
				System.out.println( getName() + ", vill mata sin:" );
				System.out.printf( "art: %s   kön: %s   hälsa: %s   pris: %d kr\n", lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")", lChosenAnimal.getGenderStr(), lChosenAnimal.getHealthFullStr(), lChosenAnimal.getPrice() );

				// Show a list containing foods the animal can eat
				lChosenAnimal.printRightFoodList();

				// Create a temporary list of foods the animal can eat
				// To prevent a player choosing wrong food that the animal can't eat
				ArrayList<FoodBase> lNewFoodList = new ArrayList<>();
				for ( FoodBase f: this.mFoods )
				{
					// Adds compatible animal to the temporary list
					if ( lChosenAnimal.canEatThis( f ) ) lNewFoodList.add( f );
				}

				// Cannot feed an animal if food is missing
				if ( lNewFoodList.size() > 0 )
				{
					this.printFoodInList( lNewFoodList );

					// Ask which food the player wants to feed the animal
					lPlayerChoiceInt = Game.askForValidNumber( getName() + ", vilket foder vill du ge din " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")?", 0, lNewFoodList.size() - 1 );
					FoodBase lChosenFood = lNewFoodList.get( lPlayerChoiceInt );

					// Show which food the player has chosen
					System.out.println( getName() + ", vill mata sin " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")" + " med " + lChosenFood.getName() );

					// Ask how much food to use
					float lPlayerChoiceFloat = Game.askForValidFloat( getName() + ", hur mycket foder vill du ge din " + lChosenAnimal.getKindStr() + "(" + lChosenAnimal.getName() + ")?", 0, lChosenFood.getQuantity() );

					// Feed the animal
					lChosenAnimal.tryEat( lChosenFood, lPlayerChoiceFloat );

					if ( lChosenFood.getQuantity() <= 0 ) mFoods.remove( lChosenFood );

					this.printLivestock();

					// Ask if the player wants to feed another animal
					if ( Game.askForValidChar( getName() + ", vill du mata ett djur till?", "JN" ).equalsIgnoreCase( "n" ) )
						lPlayerDoneFlag = true;
				}
				else
				{
					System.out.println( getName() + ", du har ingen mat att ge till djuren." );
					lPlayerDoneFlag = true;
				}
			} while ( !lPlayerDoneFlag );
		} else System.out.println( getName() + ", du äger inga djur." );
	}
}
