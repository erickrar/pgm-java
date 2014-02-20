package pagarme;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import pagarme.converter.JSonConverter;
import pagarme.exception.PagarMeException;

public class Subscription  extends PagarMeModel{

	private Plan plan;
	private SubscriptionStatus status;
	private Date currentPeriodStart;
	private Date currentPeriodEnd;
	private String postBackUrl;
	private PaymentMethod paymentMethod;
	private List<Transaction> transactions;
	private Customer customer;
	private CustomerAddress address;
	private CustomerPhone phone;



	public Subscription(PagarMeProvider provider){
		this.provider = provider;
	}

	public Subscription(PagarMeProvider provider, PagarMeQueryResponse result){
		this.provider=provider;
		this.result=result;

	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public Date getCurrentPeriodStart() {
		return currentPeriodStart;
	}

	public void setCurrentPeriodStart(Date currentPeriodStart) {
		this.currentPeriodStart = currentPeriodStart;
	}

	public Date getCurrentPeriodEnd() {
		return currentPeriodEnd;
	}

	public void setCurrentPeriodEnd(Date currentPeriodEnd) {
		this.currentPeriodEnd = currentPeriodEnd;
	}

	public String getPostBackUrl() {
		return postBackUrl;
	}

	public void setPostBackUrl(String postBackUrl) {
		this.postBackUrl = postBackUrl;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerAddress getAddress() {
		return address;
	}

	public void setAddress(CustomerAddress address) {
		this.address = address;
	}

	public CustomerPhone getPhone() {
		return phone;
	}

	public void setPhone(CustomerPhone phone) {
		this.phone = phone;
	}


	public Collection<Subscription> listAll() throws PagarMeException{
		return JSonConverter.getAsSubscriptionList(super.listAll(1000,0).getData());
	}

	public Collection<Subscription> listAllWithPagination(int totalInPage, int page) throws PagarMeException{
		return JSonConverter.getAsSubscriptionList(super.listAll(totalInPage,page).getData());
	}


	public Subscription find(int id){
		this.id = id;
		Subscription subs = null;
		try {
			subs = JSonConverter.getAsSubscription(find().getData());
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		cloneSubscription(subs);
		return subs;
	}


	public Subscription refresh(){
		Subscription subs = null;
		try {
			subs = JSonConverter.getAsSubscription(refreshModel().getData());
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		cloneSubscription(subs);
		return subs;
	}

	@Override
	protected void validate() {
		// TODO Auto-generated method stub

	}

	private void cloneSubscription(Subscription subs) {
		this.address = subs.address;
		this.currentPeriodEnd = subs.currentPeriodEnd;
		this.currentPeriodStart = subs.currentPeriodStart;
		this.customer = subs.customer;
		this.dateCreated = subs.dateCreated;
		this.id = subs.id;
		this.paymentMethod = subs.paymentMethod;
		this.phone = subs.phone;
		this.plan = subs.plan;
		this.postBackUrl = subs.postBackUrl;
		this.status = subs.status;
		this.transactions = subs.transactions;
	}
}