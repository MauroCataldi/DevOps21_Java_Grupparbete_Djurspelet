package devops21_java_djurspelet;

import java.util.ArrayList;
import java.util.Random;

public class Dog extends AnimalBase
{
	protected static final int PRICE = 1000;
	protected static final String KIND = "hund";
	protected static final float FOOD_REQ_QUANTITY = 0.3f;
	protected static final int TYPICAL_NUMBER_OF_OFFSPRING = 6;

	/**
	* Initializes this object
	*
	*/
	public Dog()
	{
		super( AnimalKind.DOG, PRICE, 16, FOOD_REQ_QUANTITY );
		this.mRightFood.add( DogFood.NAME );
	}


	/**
	* Initializes this object
	*
	* @param pGender  Desired gender of this animal
	*
	*/
	public Dog( AnimalGender pGender )
	{
		super( AnimalKind.DOG, PRICE, 16, FOOD_REQ_QUANTITY, pGender );
		this.mRightFood.add( DogFood.NAME );
	}


	/**
	* Checks if this animal can mate with other animal
	* Creates a random number of new offspring up to TYPICAL_NUMBER_OF_OFFSPRING
	* Sends this list to the caller
	*
	* @param pOtherAnimal  Which animal for mating
	* @return              An ArrayList of AnimalBase
	*
	*/
	public ArrayList<AnimalBase> tryMateWith( AnimalBase pOtherAnimal )
	{
		// Create a list that holds animals to be sent back to the caller later
		ArrayList<AnimalBase> lNewOffspringList = new ArrayList<>();

		// Is the other animal matable
		if ( this.canMateWith( pOtherAnimal ) )
		{
			Random lRandom = new Random();

			// 50% of chance
			if ( lRandom.nextBoolean() )
			{
				// How many puppies?
				int lNum = 1 + lRandom.nextInt( TYPICAL_NUMBER_OF_OFFSPRING );
				for ( int i = 0; i < lNum; i++ ) lNewOffspringList.add( new Dog() );
				System.out.println( "Parningen lyckades!" );
			}
			else
				System.out.println( "Parningen misslyckades!" );
		}

		return lNewOffspringList;
	}

	@Override
	public AnimalBase createChild() {
		return new Dog();
	}


	/**
	* Create a new separate instance of same class
	* @return  New animal of same kind but of specified gender
	*
	*/
	public AnimalBase createNewWithGender( AnimalGender pWhatGender )
	{
		return new Dog( pWhatGender );
	}


	public String getKindStr()
	{
		return String.valueOf( KIND );
	}
}
