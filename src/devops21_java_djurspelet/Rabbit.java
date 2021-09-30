package devops21_java_djurspelet;

import java.util.ArrayList;
import java.util.Random;

public class Rabbit extends AnimalBase
{
	protected static final int PRICE = 1000;
	protected static final String KIND = "kanin";
	protected static final float FOOD_REQ_QUANTITY = 0.2f;
	protected static final int TYPICAL_NUMBER_OF_OFFSPRING = 5;

	/**
	* Initializes this object
	*
	*/
	public Rabbit()
	{
		super( AnimalKind.RABBIT, PRICE, 10, FOOD_REQ_QUANTITY );
		this.mRightFood.add( Carrots.NAME );
		this.mRightFood.add( Forage.NAME );
	}



	/**
	* Initializes this object
	*
	* @param pGender  Desired gender of this animal
	*
	*/
	public Rabbit( AnimalGender pGender )
	{
		super( AnimalKind.RABBIT, PRICE, 10, FOOD_REQ_QUANTITY, pGender );
		this.mRightFood.add( Carrots.NAME );
		this.mRightFood.add( Forage.NAME );
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
				// How many rabbits?
				int lNum = 1 + lRandom.nextInt( TYPICAL_NUMBER_OF_OFFSPRING );
				for ( int i = 0; i < lNum; i++ ) lNewOffspringList.add( new Rabbit() );
				System.out.println( "Parningen lyckades!" );
			}
			else
				System.out.println( "Parningen misslyckades!" );
		}

		return lNewOffspringList;
	}

	@Override
	public AnimalBase createChild() {
		return new Rabbit();
	}


	/**
	* Create a new separate instance of same class
	* @return  New animal of same kind but of specified gender
	*
	*/
	public AnimalBase createNewWithGender( AnimalGender pWhatGender )
	{
		return new Rabbit( pWhatGender );
	}


	public String getKindStr()
	{
		return String.valueOf( KIND );
	}
}
