package pagarme;

public enum CustomerSex {
	NONE("none"),
	M("M"),
	F("F");
	private String sex;
	
	private CustomerSex(String sex) {
	 this.sex=sex;
	}
	
	 @Override
	public String toString() {
	return sex;
	}
}
