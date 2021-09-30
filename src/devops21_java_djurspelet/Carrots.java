package devops21_java_djurspelet;

public class Carrots extends FoodBase
{
	protected static final float PRICE = 20.0f;
	protected static final String NAME = "Morötter";


	/**
	* Initializes this object
	*
	* @param pQuantity  Quantity of food held (whole kilograms)
	*/
	public Carrots( float pQuantity )
	{
		super( NAME, FoodKind.CARROTS, PRICE, pQuantity );
	}


	/**
	* Create a new separate instance of same class
	* @return  New object with specified quantity
	*
	*/
	public FoodBase createNewWithQuantity( float pQuantity )
	{
		return new Carrots( pQuantity );
	}
}
