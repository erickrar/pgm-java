package pagarme.converter;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

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

	public static <T> T getAsObject(String json, Class<T> clazz) throws PagarMeException{
		if(clazz != PagarMeError.class)
			validate(json);
		return (T)gson.fromJson(json, clazz);
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

	public static Collection<Plan> getAsPlanList(String json) throws PagarMeException {
		return getAsList(json, new TypeToken<Collection<Plan>>() {}.getType());
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