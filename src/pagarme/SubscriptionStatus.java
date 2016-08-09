package pagarme;

public enum SubscriptionStatus {
	local("local"),
	pending_payment("pending_payment"),
	unpaid("unpaid"),
	trialing("trialing"),
	paid("paid"),
	canceled("canceled");
	private String status;
	
	private SubscriptionStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
	return status;
	}
}
	