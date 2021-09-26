package devops21_java_djurspelet;

import java.util.Random;
import java.util.ArrayList;

public class Cat extends AnimalBase
{
	protected static final int PRICE = 1000;
	protected static final String KIND = "katt";
	protected static final float FOOD_REQ_QUANTITY = 0.2f;
	protected static final int TYPICAL_NUMBER_OF_OFFSPRING = 8;


	/**
	* Initializes this object
	*
	* @author P.S.
	*/
	public Cat()
	{
		super( AnimalKind.CAT, PRICE, 15, FOOD_REQ_QUANTITY );
		this.mRightFood.add( CatFood.NAME );
	}


	/**
	* Initializes this object
	*
	* @param pGender  Desired gender of this animal
	*
	* @author P.S.
	*/
	public Cat( AnimalGender pGender )
	{
		super( AnimalKind.CAT, PRICE, 15, FOOD_REQ_QUANTITY, pGender );
	}


	/**
	* Checks if this animal can mate with other animal
	* Creates a random number of new offspring up to TYPICAL_NUMBER_OF_OFFSPRING
	* Sends this list to the caller
	*
	* @param pOtherAnimal  Which animal for mating
	* @return              An ArrayList of AnimalBase
	*
	* @author P.S.
	*/
	public ArrayList<AnimalBase> tryMateWith( AnimalBase pOtherAnimal )
	{
		System.out.println( "TODO: tryMateWith" );

		// Create a list that holds animals to be sent back to the caller later
		ArrayList<AnimalBase> lNewOffspringList = new ArrayList<>();

		// Is the other animal matable
		if ( this.canMateWith( pOtherAnimal ) )
		{
			Random lRandom = new Random();

			// 50% of chance
			if ( lRandom.nextBoolean() )
			{
				// How many kittens?
				int lNum = 1 + lRandom.nextInt( TYPICAL_NUMBER_OF_OFFSPRING );
				for ( int i = 0; i < lNum; i++ ) lNewOffspringList.add( new Cat() );
				System.out.println( "Parningen lyckades!" );
				System.out.println( "Fått " + lNewOffspringList.size() + " ny(a).");
			}
			else
				System.out.println( "Parningen misslyckades." );
		}

		return lNewOffspringList;
	}


	public String getKindStr()
	{
		return String.valueOf( KIND );
	}
}
