package pagarme;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class CustomerPhone implements Serializable {

	private static final long serialVersionUID = 6497816915315683894L;
	private String ddd;
	private String ddi;
	private String number;

	@SuppressWarnings("unused")
	private boolean freezed;

	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getDdi() {
		return ddi;
	}
	public void setDdi(String ddi) {
		this.ddi = ddi;
	}


	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}


	private void readObject(ObjectInputStream ois)	throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		freezed = true;
	}
}
