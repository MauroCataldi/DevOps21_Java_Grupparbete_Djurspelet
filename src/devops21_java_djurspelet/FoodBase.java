package devops21_java_djurspelet;

public abstract class FoodBase
{
	protected String mName;
	protected FoodKind mKind;
	private float mPrice;
	private float mQuantity;
	//int eatFood;


	/**
	* Initializes this object
	*
	* @param pName      Food name
	* @param pKind      Food kind
	* @param pPrice     In whole SEK
	* @param pQuantity  Quantity of food held in whole kilograms
	*
	* @author P.S.
	*/
	public FoodBase( String pName, FoodKind pKind, float pPrice, float pQuantity )
	{
		this.mName = pName;
		this.mKind = pKind;
		this.mPrice = pPrice;
		this.mQuantity = pQuantity;
	}


	/**
	* Create a new separate instance of same class
	* @return  New object with specified quantity
	*
	* @author  P.S.
	*/
	public abstract FoodBase createNewWithQuantity( float pQuantity );

	public String getName() { return this.mName; }
	public FoodKind getKind() { return this.mKind; }
	public float getPrice() { return this.mPrice; }
	public String getPriceStr() { return String.format( "%.2f", this.mPrice ); }
	public float getPriceTotal() { return this.mPrice * this.getQuantity(); }
	public String getPriceTotalStr() { return String.format( "%.2f", this.mPrice * this.getQuantity() ); }

	public void addQuantity( float pQuantity ) { this.mQuantity += pQuantity; }
	public void removeQuantity( float pQuantity ) { this.mQuantity -= pQuantity; }
	public void setQuantity( float pQuantity ) { this.mQuantity = pQuantity; }
	public float getQuantity() { return this.mQuantity; }
	public String getQuantityStr() { return String.format( "%.2f", this.mQuantity ); }
}
