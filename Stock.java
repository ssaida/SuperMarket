
public class Stock {

	private static final int MAX_SIZE = 100;
	private FoodItem[] _stock;
	public int _noOfItems;
	
	public Stock() {
		_stock = new FoodItem[MAX_SIZE];
		this._noOfItems = 0;
	}
	public int getNoOfItems() {
		return this._noOfItems;
	}
	
	public boolean addItem(FoodItem item) {
		FoodItem temp = isExist(item);
		if(temp != null) {
			int tmp;
			tmp = item.get_quantity();
			tmp += temp.get_quantity();
			temp.set_quantity(tmp);
			return true;
		}
		if(this._noOfItems < _stock.length) {
			int idx = 0;
			if(_noOfItems > 0) {
				for(int i=_noOfItems;i > 0;i--) {
					if(_stock[i-1].get_catalogNumber() <= item.get_catalogNumber()) {
						idx = i;
						break;
					}
					_stock[i] = _stock[i-1];
				}
			}
			_stock[idx] = new FoodItem(item);
			this._noOfItems++;
			return true;
		}
		return false;
	}
	private FoodItem isExist(FoodItem item) {
		for(int i=0;i<_noOfItems;i++) {
			if(_stock[i].getName().equals(item.getName()) && _stock[i].get_catalogNumber() == item.get_catalogNumber()) {
				if(_stock[i].get_expiryDate().equals(item.get_expiryDate()) && _stock[i].get_productionDate().equals(item.get_productionDate())) {
					return _stock[i];
				}
			}
		}
		return null;
	}
	public String order(int amount) {
		String orderList = "";
		for(int i=0;i<_noOfItems;i++) {
			String name = _stock[i].getName();
			long catalog = _stock[i].get_catalogNumber();
			int quantity = _stock[i].get_quantity();
			for(int j=0;j<_noOfItems;j++) {
				if(_stock[j].getName().equals(name) && _stock[j].get_catalogNumber() == catalog && i != j) {
					quantity += _stock[j].get_quantity();
				}
			}
			if(quantity < amount) {
				orderList += _stock[i].getName()+" ";
			}
		}
		if(orderList.length() > 0) {
			orderList = orderList.substring(0, orderList.length()-1);
			orderList = orderList.replace(" ",", ");
		}
		return orderList;
	}
	public int howMany(int temp) { 
		int temperature = 0;
		for(int i=0;i<_noOfItems;i++) {
			if(_stock[i].get_minTemperature() <= temp && _stock[i].get_maxTemperature() >= temp) {
				temperature += _stock[i].get_quantity();
			}
		}
		return temperature;
	}
	public void removeAfterDate(Date date) {
		for(int i=0;i<_noOfItems;i++) {
			if(date.after(_stock[i].get_expiryDate())) {
				removeByIndex(i);
				i--;
			}
		}
	}
	private void removeByIndex(int index) {
		for(int i=index;i<_noOfItems-1;i++)
			_stock[i] = _stock[i+1];
		this._noOfItems--;
	}
	public FoodItem mostExpensive() {
		if(this._noOfItems == 0)
			return null;
		FoodItem temp = _stock[0];
		for(int i=0;i<_noOfItems;i++) {
			if(_stock[i].get_price() > temp.get_price())
				temp = _stock[i];
		}
		return temp;
	}
	public int howMAnyPieces() {
		if(this._noOfItems == 0)
			return 0;
		int total = 0;
		for(int i=0;i<_noOfItems;i++) {
			total += _stock[i].get_quantity();
		}
		return total;
	}
	public int getTempOfStock() {
		if(this._noOfItems == 0) {
			int min = _stock[0].get_minTemperature();
			int max = _stock[0].get_maxTemperature();
			for(int i=0;i<_noOfItems;i++) {
				if(min > _stock[i].get_maxTemperature())
					min = _stock[i].get_maxTemperature();
				if(max < _stock[i].get_minTemperature())
					max = _stock[i].get_minTemperature();
			}
			if(max <= min)
				return max;
			else
				return Integer.MAX_VALUE;
		}
		return Integer.MAX_VALUE;
	}
	public void updateStock(String[] itemList) {
		for(String item : itemList) {
			for(int i=0;i<_noOfItems;i++) {
				if(item.equals(_stock[i].getName())) {
					int temp = _stock[i].get_quantity();
					if(temp == 1) {
						removeByIndex(i);
					}else 
						_stock[i].set_quantity(temp-1);
						break;
				}
			}
		}
	}
	public String toString() {
		if(this._noOfItems == 0)
			return "Empty :-(";
		String res = "";
		for(int i=0;i<_noOfItems;i++) {
			res += _stock[i]+"\n";
		}
		return res;
	}
}
