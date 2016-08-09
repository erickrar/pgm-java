package pagarme;

import com.google.gson.annotations.SerializedName;

public class TransactionSetup {

    private String amount;

    @SerializedName("payment_method")
    private PaymentMethod paymentMethod;
    private String cardhash;
    private Customer customer;


    @SerializedName("card_number")
    private String cardNumber;

    @SerializedName("card_holder_name")
    private String cardHolderName;

    @SerializedName("card_expiration_date")
    private String cardExpirationDate;

    @SerializedName("card_cvv")
    private String cardCvv;

    @SerializedName("postback_url")
    private String postbackUrl;


    public TransactionSetup() {
        paymentMethod = PaymentMethod.credit_card;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardhash() {
        return cardhash;
    }

    public void setCardhash(String cardhash) {
        this.cardhash = cardhash;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPostbackUrl() {
        return postbackUrl;
    }

    public void setPostbackUrl(String postbackUrl) {
        this.postbackUrl = postbackUrl;
    }

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

}
