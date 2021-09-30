package devops21_java_djurspelet;

import java.util.ArrayList;

public abstract class AnimalBase // Enforce creation of subclasses
{
	protected static final int ATSTART_HEALTH = 100;

	private String mName;
	private AnimalKind mKind;
	private AnimalGender mGender;     // AnimalGender.MALE or AnimalGenderFEMALE
	protected int mPriceAtMaxHealth;  // Initial price at 100%
	private float mHealth;            // Current health
	private float mLastHealth;        // For compute deltas
	private int mAge;
	private int mExpectedLifeLength;
	protected ArrayList<String> mRightFood;  // List of foods the anmial can eat
	float mFoodQuantityHealthReq;            // Quantity required to increase health 10% points


	/**
	* Initializes Animal object.
	* Sets kind, price at 100% health, expected life length
	* Randomizes gender
	*
	* @param pKind                What kind of animal
	* @param pPriceAtMaxHealth    Original price
	* @param pExpectedLifeLength  Dies at end
	*
	* @author P.S.
	*/
	protected AnimalBase( AnimalKind pKind, int pPriceAtMaxHealth, int pExpectedLifeLength, float pFoodQuantityHealthReq )
	{
		this.mName = "Namnlös";
		this.mKind = pKind;
		this.mGender = ( (int)( Math.random() * 2 ) + 1 ) > 1 ? AnimalGender.MALE : AnimalGender.FEMALE;
		this.mPriceAtMaxHealth = pPriceAtMaxHealth;
		this.mHealth = ATSTART_HEALTH;
		this.mLastHealth = this.mHealth;
		this.mAge = 0;
		this.mExpectedLifeLength = pExpectedLifeLength;
		this.mRightFood = new ArrayList<>();
		this.mFoodQuantityHealthReq = pFoodQuantityHealthReq;
	}


	/**
	* Initializes Animal object.
	* Sets kind, price at 100% health, expected life length and gender
	*
	* @param pKind                What kind of animal
	* @param pPriceAtMaxHealth    Original price
	* @param pExpectedLifeLength  Dies at end
	* @param pGender              GENDER_MALE or GENDER_FEMALE
	*
	* @author P.S.
	*/
	protected AnimalBase( AnimalKind pKind, int pPriceAtMaxHealth, int pExpectedLifeLength, float pFoodQuantityHealthReq, AnimalGender pGender )
	{
		this.mName = "Namnlös";
		this.mKind = pKind;
		this.mGender = pGender;
		this.mPriceAtMaxHealth = pPriceAtMaxHealth;
		this.mHealth = ATSTART_HEALTH;
		this.mLastHealth = this.mHealth;
		this.mAge = 0;
		this.mExpectedLifeLength = pExpectedLifeLength;
		this.mRightFood = new ArrayList<>();
		this.mFoodQuantityHealthReq = pFoodQuantityHealthReq;
	}


	/**
	* @return  This animal's kind
	*
	* @author P.S.
	*/
	public AnimalKind getKind() { return this.mKind; }


	abstract public String getKindStr();


	/**
	* @return   AnimalGender.MALE or AnimalGender.FEMALE
	*
	* @author P.S.
	*/
	public AnimalGender getGender() { return this.mGender; }


	/**
	* @return   A string "hona" or "hane"
	*
	* @author P.S.
	*/
	public String getGenderStr()
	{
		String lGenderStr = "";
		switch ( mGender )
		{
			case MALE :
				lGenderStr = "hane";
				break;
			case FEMALE :
				lGenderStr = "hona";
				break;
		}
		return lGenderStr;
	}


	/**
	* @return  Animal's health as float
	*
	* @author  P.S.
	*/
	public float getHealth() { return this.mHealth; }


	/**
	* @return  Animal's health as String
	*
	* @author P.S.
	*/
	public String getHealthStr() { return String.format( "%.2f%%", this.getHealth() ); }


	/**
	* @return  Animal's health change as int
	*
	* @author P.S.
	*/
	public float getHealthDelta() { return this.mHealth - this.mLastHealth; }


	/**
	* @return  Animal's health change as text
	*
	* @author P.S.
	*/
	public String getHealthDeltaStr() { return String.format( "%.2f%%", this.getHealthDelta() ); }


	/**
	* @return  Animal's health change as text
	*
	* @author P.S.
	*/
	public String getHealthFullStr() { return String.format( "%.2f%%(%.2f%%)", this.getHealth(), this.getHealthDelta() ); }


	/**
	* @return  Computed price, from health and original price
	*
	* @author P.S.
	*/
	public int getPrice() { return (int)( mHealth * this.mPriceAtMaxHealth / 100); }


	/**
	* @return  Animal's age
	*
	* @author P.S.
	*/
	public int getAge() { return this.mAge; }


	public void growOlder()
	{
		this.mAge++;
		this.mLastHealth = this.mHealth;
		this.mHealth -= (int) ( Math.random() * 20 ) + 10;
		if ( this.mHealth < 0 ) this.mHealth = 0;
	}


	/**
	* @param pOtherAnimal  The animal to check
	* @return              True if this animal can mate with the specified animal
	*
	* @author P.S.
	*/
	public boolean canMateWith( AnimalBase pOtherAnimal )
	{
		if ( ( this.getKind() == pOtherAnimal.getKind() ) && ( this.getGender() != pOtherAnimal.getGender() ) )
			return true;
		//else System.out.println( this.getKindStr() + "(" + this.getName() + ") " + this.getGenderStr() + " kan inte paras med " + pOtherAnimal.getKindStr() + "(" + pOtherAnimal.getName() + ")" + pOtherAnimal.getGenderStr() + ".");

		return false;
	}

	// Enforce creation of this method in a subclass
	public abstract ArrayList<AnimalBase> tryMateWith( AnimalBase pOtherAnimal );


	/**
	* Tests if this animal can eat certain type of food
	*
	* @param pWhatFoodStr  A food object
	* @return              True if same
	*
	* @author P.S.
	*/
	public boolean canEatThis( String pWhatFoodStr )
	{
		for ( String fStr : mRightFood )
		{
			if ( fStr.equalsIgnoreCase( pWhatFoodStr ) ) return true;;
		}
		return false;
	}


	/**
	* Tests if this animal can eat certain type of food
	*
	* @param pWhatFood  A food String
	* @return           True if same
	*
	* @author P.S.
	*/
	public boolean canEatThis( FoodBase pWhatFood )
	{
		for ( String fStr : mRightFood )
		{
			if ( fStr.equalsIgnoreCase( pWhatFood.getName() ) ) return true;
		}
		return false;
	}


	/**
	* Checks if food is eatable
	* Makes sure that the amount of food in the food backet never gets below 0
	* Decreases quantity in food backet by the computed quantity
	* Increases health of this animal
	* Writes to screen a notice if this animal can not eat that food
	*
	* @param pWhichFoodBucket  From which food object to take
	* @param pQuantity         How much to give
	* @return                  true or false
	*
	* @author P.S
	*/
	public boolean tryEat( FoodBase pWhichFoodBucket, int pQuantity )
	{
		if ( this.canEatThis( pWhichFoodBucket ) )
		{
			int lDiff = pWhichFoodBucket.getQuantity() - pQuantity;
			if ( lDiff < 0 ) pQuantity += lDiff;

			pWhichFoodBucket.removeQuantity( pQuantity );

			this.mHealth += 10 * pQuantity / this.mFoodQuantityHealthReq;
			if ( this.mHealth > 100 ) this.mHealth = 100;

			return true;
		}
		else
			System.out.println( this.mName + "(" + this.getKindStr() + ") kan inte äta " + pWhichFoodBucket.getName() );

		return false;
	}


	/**
	* Writes to screen a list of what this animal can eat
	*
	* @author P.S.
	*/
	public void printRightFoodList()
	{
		System.out.println( this.getKindStr() + " kan äta:" );

		for ( String fStr : mRightFood )
		{
			System.out.println( fStr );
		}
	}


	public abstract AnimalBase createChild();


	/**
	* Create a new separate instance of this object
	* @return  New object with specified gender
	*
	* @author  P.S.
	*/
	public abstract AnimalBase createNewWithGender( AnimalGender pWhatGender );


	/**
	*
	* @param pName  Animal's name
	*
	* @author P.S.
	*/
	public void setName( String pName ) { this.mName = pName; }


	/**
	* @return  Animal's name, preset if not set
	*
	* @author P.S.
	*/
	public String getName() { return this.mName; }
}
