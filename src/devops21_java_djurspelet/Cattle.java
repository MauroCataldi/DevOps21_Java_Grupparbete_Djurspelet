package devops21_java_djurspelet;

import java.util.ArrayList;
import java.util.Random;

public class Cattle extends AnimalBase
{
	protected static final int PRICE = 20000;
	protected static final String KIND = "nötkreatur";
	protected static final float FOOD_REQ_QUANTITY = 10.0f;
	protected static final int TYPICAL_NUMBER_OF_OFFSPRING = 1;


	/**
	* Initializes this object
	*
	* @author P.S.
	*/
	public Cattle()
	{
		super( AnimalKind.CATTLE, PRICE, 20, FOOD_REQ_QUANTITY );
		this.mRightFood.add( Forage.NAME );
	}


	/**
	* Initializes this object
	*
	* @param pGender  Desired gender of this animal
	*
	* @author P.S.
	*/
	public Cattle( AnimalGender pGender )
	{
		super( AnimalKind.CATTLE, PRICE, 20, FOOD_REQ_QUANTITY, pGender );
	}


	/**
	* Checks if this animal can mate with other animal
	* Creates a random number of new offspring up to TYPICAL_NUMBER_OF_OFFSPRING
	* Sends this list to the caller
	*
	* @param pOtherAnimal  Wich animal for mating
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
				// How many calfs?
				int lNum = 1 + lRandom.nextInt( TYPICAL_NUMBER_OF_OFFSPRING );
				for ( int i = 0; i < lNum; i++ ) lNewOffspringList.add( new Cattle() );
				System.out.println( "Parningen lyckades!" );
				System.out.println( "Fått " + lNewOffspringList.size() + " ny(a).");
			}
			else
				System.out.println( "Parningen misslyckades!" );
		}

		return lNewOffspringList;
	}


	public String getKindStr()
	{
		return String.valueOf( KIND );
	}
}
