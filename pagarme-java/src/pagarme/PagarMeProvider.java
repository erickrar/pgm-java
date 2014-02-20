package pagarme;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pagarme.converter.JSonConverter;
import pagarme.exception.ArgumentException;
import pagarme.exception.FormatException;
import pagarme.exception.PagarMeException;
import pagarme.util.MapUtil;

import com.google.common.base.Strings;
import com.sun.jersey.core.util.Base64;

public class PagarMeProvider {


	private String apiKey;
	private String encryptionKey;
	private List<Customer> customers;
	private List<Plan> plans;
	private List<Subscription> subscriptions;
	private List<Transaction> transatcions;
	private PagarMeError errors;

	public String getApiKey() {
		return apiKey;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public List<Transaction> getTransatcions() {
		return transatcions;
	}


	public PagarMeError getErrorList() {
		return errors;
	}

	public void setErrorList(PagarMeError errorList) {
		this.errors = errorList;
	}

	public PagarMeProvider(String apiKey, String encryptionKey) throws ArgumentException{		
		if(Strings.isNullOrEmpty(apiKey) || !apiKey.startsWith("ak_"))
			throw new ArgumentException("Invalid API key.");

		if(Strings.isNullOrEmpty(encryptionKey) || !encryptionKey.startsWith("ek_"))
			throw new ArgumentException("Invalid encryption key.");

		this.apiKey= apiKey;
		this.encryptionKey = encryptionKey;
		customers = new ArrayList<Customer>();
		plans = new ArrayList<Plan>();
		subscriptions = new ArrayList<Subscription>();
		transatcions = new ArrayList<Transaction>();
	}

	public Transaction postTransaction(TransactionSetup setup) throws FormatException, PagarMeException{
		Transaction trans = null;
		PagarMeQuery query = new PagarMeQuery(this,"POST","transactions");
		validateSetup(setup, false);
		query.addQueries(JSonConverter.objectToMap(setup));		
		PagarMeQueryResponse response = query.execute();
		try{
			trans = JSonConverter.getAsTransaction(response.getData());
			trans.setProvider(this);
		}catch(PagarMeException e){
			errors = JSonConverter.getAsErrorList(response.getData());
			throw new PagarMeException(getErrorList().showErrors());
		}

		return trans;
	}
	

	public Subscription PostSubscription(SubscriptionSetup setup)  throws FormatException,PagarMeException{
		Subscription subs = null;
		PagarMeQuery query = new PagarMeQuery(this,"POST","subscriptions");
		ValidateSubscription(setup);
		query.addQueries(JSonConverter.objectToMap(setup));
		PagarMeQueryResponse response = query.execute();
		try{
			subs = JSonConverter.getAsSubscription(response.getData());
			subs.setProvider(this);

		}catch(PagarMeException e){
			errors = JSonConverter.getAsErrorList(response.getData());
			throw new PagarMeException(getErrorList().showErrors());
		}
		
		return subs;
	}

	private static void ValidateSubscription(SubscriptionSetup setup) throws FormatException{
		validateSetup(setup, true);

		if( Double.parseDouble(setup.getPlan()) < 0)
			throw new FormatException("Plan ID must be equal or greater than zero.");

		if(Strings.isNullOrEmpty(setup.getEmail()))
			throw new FormatException("Customer email is required.");
	}

	private static void validateSetup(TransactionSetup setup, boolean subset) throws FormatException{

		if(setup.getPaymentMethod().equals(PaymentMethod.credit_card) && Strings.isNullOrEmpty(setup.getCardhash()))
			throw new FormatException("CardHash is required.");

		if (subset && Double.parseDouble(setup.getAmount())<=0d){
			throw new FormatException("Amount must be greater than zero.");
		}
	}


	public  String generateCardHash(CreditCard credirCatd) throws PagarMeException,FormatException, NoSuchAlgorithmException {
		PagarMeQuery query = new PagarMeQuery(this, "GET" , "transactions/card_hash_key");
		Map<String, Object> map = MapUtil.objectToMap(credirCatd);
		query.addQueries(map);
		PagarMeQueryResponse response = query.execute();
		CardHashKey cardKey = JSonConverter.getAsCardHashKey(response.getData());
	
		
		byte [] encoded = Base64.encode(cardKey.getPublickey());
		//KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		//kpg.initialize(2048);
		//KeyPair keys = kpg.generateKeyPair();
		//PublicKey pubKey = keys.getPublic();
		return cardKey.getId()+"_"+new String(encoded);
	}
}
