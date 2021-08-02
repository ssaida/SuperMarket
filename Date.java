
public class Date {

	private final int DEF_DAY = 1;
	private final int DEF_MONTH = 1;
	private final int DEF_YEAR = 2000;
	
	private final int LONG_MONTH = 31;
	private final int SHORT_MONTH = 30;
	private final int FEB_MONTH = 29;
	private final int FEB_MONTH_LEAP = 28;
	
	private final int MAX_YEAR = 1000;
	private final int MIN_YEAR = 9999;
	
	private int _day, _month, _year;
	
	public Date(int day, int month, int year) {
		if((!yearOk(year)) || (dayMonthOk(day, month, year))){
			day = DEF_DAY;
			month = DEF_MONTH;
			year = DEF_YEAR;
		}
		_day = day;
		_month = month;
		_year = year;
	}

	public Date(Date other) {
		this._day = other._day;
		this._month = other._month;
		this._year = other._month;
	}
	
	private boolean dayMonthOk(int day, int month, int year) {
		// TODO Auto-generated method stub
		switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: return day >= 1 && day <= LONG_MONTH;
		case 4:
		case 6:
		case 9:
		case 11: return day >= 1 && day <= SHORT_MONTH;
		case 2: return (leapYear(year))? (day >= 1 && day <= FEB_MONTH_LEAP): (day >= 1 && day <= FEB_MONTH);
		default: return false;
		}
	}
	private boolean yearOk(int year) {
		return MIN_YEAR <= year && year <= MAX_YEAR;
	}

	private boolean leapYear(int year) {
		return (((year%4 == 0) && (year%100 != 0)) || (year%400 == 0));
	}
	// computes the day number since the beginning of the Christian counting of years 
	private int calculateDate(int day, int month, int year) {
		if(month < 3) {
			year--;
			month = month + 12;
		}
		return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
	}
	public boolean before(Date other) {
		return calculateDate(this._day, this._month, this._year) < calculateDate(other._day, other._month, other._year);
	}
	public boolean after(Date other) {
		return other.before(this);
	}
	public int difference(Date other) {
		return Math.abs(calculateDate(_day, _month, _year) - calculateDate(other._day, other._month, other._year));
	}
	public boolean equals(Date other) {
		return calculateDate(this._day, this._month, this._year) == calculateDate(other._day, other._month, other._year);
	}
	public String toString() {
        if( _day < 10 && _month < 10) {
            return "0" + _day + "/0" + _month + "/" + _year ;
        } else if (_day < 10) {
            return "0" + _day + "/" + _month + "/" + _year ;
        } else if(_month < 10 ) {
            return  _day + "/0" + _month + "/" + _year ;
        }           
        return _day + "/" + _month + "/" + _year ;
    }
	public Date tomorrow() {
		if(dayMonthOk(_day+1, _month, _year)) {
			Date date = new Date(_day+1, _month, _year);
			return date;
		}else {
			if(dayMonthOk(DEF_DAY, _month+1, _year)) {
				Date date = new Date(DEF_DAY, _month+1, _year);
				return date;
			}else {
				Date date = new Date(DEF_DAY, DEF_MONTH, _year+1);
				return date;
			}
		}
	}
	
}