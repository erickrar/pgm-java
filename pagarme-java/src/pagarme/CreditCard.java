package pagarme;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class CreditCard implements Serializable {
	private static final long serialVersionUID = 3359881519154922843L;
	
	@SerializedName("card_number")
	 public String cardNumber;
	
	@SerializedName("card_holder_name")
	 public String cardHolderName;
	
	@SerializedName("card_expiration_date")
	 public String cardExpirationDate;
	
	@SerializedName("card_cvv")
	 public String cardCvv;
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardExpirationDate() {
		return cardExpirationDate;
	}

	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	
	public String toHtmlParameter(){
		StringBuilder sb = new StringBuilder();
		sb.append("card_number=");sb.append(cardNumber);sb.append("&");
		sb.append("card_holder_name=");sb.append(cardHolderName);sb.append("&");
		sb.append("card_expiration_date=");sb.append(cardExpirationDate);sb.append("&");
		sb.append("card_cvv=");sb.append(cardCvv);
		return sb.toString();
	}
}
