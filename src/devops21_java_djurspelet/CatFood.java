package devops21_java_djurspelet;

public class CatFood extends FoodBase
{
	protected static final int PRICE = 20;
	protected static final String NAME = "Torrfoder för katter";


	/**
	* Initializes this object
	*
	* @param pQuantity  Quantity of food held in whole kilograms
	*/
	public CatFood( int pQuantity )
	{
		super( NAME, FoodKind.CATFOOD, PRICE, pQuantity );
	}


	/**
	* Create a new separate instance of same class
	* @return  New object with specified quantity
	*
	* @author  P.S.
	*/
	public FoodBase createNewWithQuantity( int pQuantity )
	{
		return new CatFood( pQuantity );
	}
}
