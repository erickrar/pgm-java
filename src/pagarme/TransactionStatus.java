package pagarme;

public enum TransactionStatus {
	local("local"),
	processing("processing"),
	waiting_payment("waiting_payment"),
	refused("refused"),
	paid("paid"),
	refunded("refunded");
	
	private String status;
	TransactionStatus(String status){
		this.status=status;
	}
	
	@Override
	public String toString() {
	return status;
	}
	
}
