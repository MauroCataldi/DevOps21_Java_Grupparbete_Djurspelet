package devops21_java_djurspelet;

public class CatFood extends FoodBase
{
	protected static final float PRICE = 20.0f;
	protected static final String NAME = "Torrfoder för katter";


	/**
	* Initializes this object
	*
	* @param pQuantity  Quantity of food held in whole kilograms
	*/
	public CatFood( float pQuantity )
	{
		super( NAME, FoodKind.CATFOOD, PRICE, pQuantity );
	}


	/**
	* Create a new separate instance of same class
	* @return  New object with specified quantity
	*
	*/
	public FoodBase createNewWithQuantity( float pQuantity )
	{
		return new CatFood( pQuantity );
	}
}
