package pagarme;

public enum PaymentMethod {
	credit_card("credit_card"),
	boleto("boleto");
	private String method;

	private PaymentMethod(String method) {
		this.method=method;
	}

	@Override
	public String toString() {
		return method;
	}
}
