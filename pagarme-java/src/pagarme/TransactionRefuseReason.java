package pagarme;

public enum TransactionRefuseReason {
	None("none"),
	acquirer("acquirer"),
	antiFraud("antifraud");

	private String reason;

	TransactionRefuseReason(String reason){
		this.reason = reason;
	}

	@Override
	public String toString() {
		return reason;
	}
}
