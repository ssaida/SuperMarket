
public class FoodItem {

	private final int DEF_CATALOG_NUMBER = 9999;
	private final int DEF_QUANTITY = 0;
	private final int DEF_PRICE = 1;
	private final int MIN_CATALOG_NUMBER = 1000;
	private final int MAX_CATALOG_NUMBER = 9999;
	private final int DEF_AMOUNT = 0;
	
	private String _name;
	private long _catalogNumber;
	private int _quantity;
	private Date _productionDate;
	private Date _expiryDate;
	private int _minTemperature;
	private int _maxTemperature;
	private int _price;
	
	public FoodItem(String name, long catalogNumber, int quantity, Date production, Date expiry, int minTemp, int maxTemp, int price) {
		_name = name.length() > 0 && name != null ? name : "item";
		_catalogNumber = catalogNumber >= MIN_CATALOG_NUMBER && catalogNumber <= MAX_CATALOG_NUMBER ? catalogNumber : DEF_CATALOG_NUMBER;
		_quantity = quantity < DEF_QUANTITY ? DEF_QUANTITY : quantity;
		if(expiry.before(production))
			_expiryDate = new Date(production.tomorrow());
		else
			_expiryDate = new Date(expiry);
		_productionDate = new Date(production);
		if(minTemp > maxTemp) {
			_minTemperature = maxTemp;
			_maxTemperature = minTemp;
		}else {
			_minTemperature = minTemp;
			_maxTemperature = maxTemp;
		}
		_price = price > DEF_PRICE ? price : DEF_PRICE;
	}

	public FoodItem(FoodItem other) {
		this._name = other._name;
		this._catalogNumber = other._catalogNumber;
		this._quantity = other._quantity;
		this._productionDate = new Date(other._productionDate);
		this._expiryDate = new Date(other._expiryDate);
		this._minTemperature = other._minTemperature;
		this._maxTemperature = other._maxTemperature;
		this._price = other._price;
	}
	
	public int get_quantity() {
		return _quantity;
	}

	public void set_quantity(int quantity) {
		if(quantity >= DEF_QUANTITY)
			_quantity = quantity;
	}

	public Date get_productionDate() {
		return _productionDate;
	}

	public void set_productionDate(Date productionDate) {
		if(productionDate.before(_expiryDate))
			_productionDate = new Date(productionDate);
	}

	public Date get_expiryDate() {
		return _expiryDate;
	}

	public void set_expiryDate(Date expiryDate) {
		if(expiryDate.after(_productionDate))
			_expiryDate = new Date(expiryDate);
	}

	public int get_price() {
		return _price;
	}

	public void set_price(int price) {
		_price = price > DEF_PRICE ? price : DEF_PRICE;
	}

	public String getName() {
		return _name;
	}

	public long get_catalogNumber() {
		return _catalogNumber;
	}

	public int get_minTemperature() {
		return _minTemperature;
	}

	public int get_maxTemperature() {
		return _maxTemperature;
	}
	public String toString() {
		return "FoodItem: "+_name+"\tCatalog Number: "+this._catalogNumber+"\tProduction Date: "+
		this._productionDate+"\tExpiry Date: "+this._expiryDate+"\tQuantity: "+this._quantity;
	}
	public boolean equals(FoodItem other) {
		return _name.equals(other._name) && this._catalogNumber == other._catalogNumber && this._productionDate.equals(other._productionDate) &&
				this._expiryDate.equals(other._expiryDate) && this._minTemperature == other._minTemperature && this._maxTemperature == other._maxTemperature &&
				this._price == other._price;
	}
	public int howManyItems(int amount) {
		if(amount > DEF_AMOUNT) {
			return _quantity >= amount/_price ? amount/_price : _quantity;
		}else {
			return 0;
		}
	}
	public boolean isCheaper(FoodItem other) {
		return _price < other._price;
	}
	public boolean isFresh(Date date) {
		return date.before(_expiryDate) && date.after(_productionDate);
	}
	public boolean olderFoodItem(FoodItem other) {
		return this._productionDate.before(other._productionDate);
	}
}