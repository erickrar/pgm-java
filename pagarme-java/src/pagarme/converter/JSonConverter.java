package pagarme.converter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import pagarme.CardHashKey;
import pagarme.Customer;
import pagarme.PagarMeError;
import pagarme.Plan;
import pagarme.Subscription;
import pagarme.Transaction;
import pagarme.exception.PagarMeException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSonConverter {


	public static final Gson gson;

	static{
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	
	public static Transaction getAsTransaction(String json) throws PagarMeException {
		return (Transaction)getAsObject(json, Transaction.class);
	}
	
	public static Customer getAsCustomer(String json) throws PagarMeException {
		return (Customer)getAsObject(json, Customer.class);
	}
	public static Collection<Transaction> getAsTransactionList(String json) throws PagarMeException {
		return getAsList(json, new TypeToken<Collection<Transaction>>() {}.getType());
	}
	
	public static Collection<Subscription> getAsSubscriptionList(String json) throws PagarMeException {
		return getAsList(json, new TypeToken<Collection<Subscription>>() {}.getType());
	}
	
	public static Collection<Customer> getAsCustomerList(String json) throws PagarMeException {
		return getAsList(json, new TypeToken<Collection<Customer>>() {}.getType());
	}
	
	public static Subscription getAsSubscription(String json) throws PagarMeException {
		return (Subscription)getAsObject(json, Subscription.class);
	}	
	
	public static CardHashKey getAsCardHashKey(String json) throws PagarMeException {
		validate(json);
		return gson.fromJson(json, CardHashKey.class);
	}

	public static Plan getAsPlan(String json) throws PagarMeException {
		return (Plan)getAsObject(json, Plan.class);
	}
	
	public static PagarMeError getAsErrorList(String json){
		PagarMeError list = gson.fromJson(json, PagarMeError.class);
		return list;
	}
	
	public static Collection<Plan> getAsPlanList(String json) throws PagarMeException {
		return getAsList(json, new TypeToken<Collection<Plan>>() {}.getType());
	}
	
	private static <T> Object getAsObject(String json, Class<T> clazz) throws PagarMeException{
		validate(json);
		return gson.fromJson(json, clazz);
	}
	
	private static <T> Collection<T> getAsList(String json,Type listType)throws PagarMeException{
	validate(json);
		return gson.fromJson(json, listType);
	}

	public static Map<String,Object> objectToMap(Object object){
		 String json = gson.toJson(object);
		 Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
		 return map;
	}
	
	private static void validate(String json) throws PagarMeException{
		if (json.contains("errors"))
			throw new PagarMeException(json);
	}
}