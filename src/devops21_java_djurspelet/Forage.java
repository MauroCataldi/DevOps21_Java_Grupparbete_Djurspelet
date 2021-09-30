package devops21_java_djurspelet;

public class Forage extends FoodBase
{
	protected static final float PRICE = 20.0f;
	protected static final String NAME = "Grovfoder";


	/**
	* Initializes this object
	*
	* @param pQuantity  Quantity of food held in whole kilograms
	*/
	public Forage( float pQuantity )
	{
		super( NAME, FoodKind.FORAGE, PRICE, pQuantity );
	}


	/**
	* Create a new separate instance of same class
	* @return  New object with specified quantity
	*
	*/
	public FoodBase createNewWithQuantity( float pQuantity )
	{
		return new Forage( pQuantity );
	}
}
