package pagarme;

import java.util.Collection;

import pagarme.converter.JSonConverter;
import pagarme.exception.FormatException;
import pagarme.exception.InvalidOperationException;
import pagarme.exception.PagarMeException;

import com.google.gson.annotations.SerializedName;

public class Plan extends PagarMeModel {

	private String amount;
	private String color;
	private String days;
	private String name;

	public Plan(PagarMeProvider provider){
		this.provider = provider;
	}

	@SerializedName("trial_days")
	private String trialDays;

	private boolean freezed;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrialDays() {
		return trialDays;
	}

	public void setTrialDays(String trialDays) {
		this.trialDays = trialDays;
	}

	public Plan createPlan() throws PagarMeException{
		Plan plan = null;
		if(provider==null)
			return null;

		PagarMeQuery query =  new PagarMeQuery(provider, "POST",String.format("%s",className));
		query.addQueries(JSonConverter.objectToMap(this));
		result = query.execute();
		try {
			plan = JSonConverter.getAsObject(result.getData(),Plan.class);
			clonePlan(plan);
		} catch (PagarMeException e) {
			provider.setErrorList( JSonConverter.getAsObject(result.getData(),PagarMeError.class));
			 throw new PagarMeException(provider.getErrorList().showErrors());
		}
		
		return plan;
	}
	
	
	public Collection<Plan> listAll() throws PagarMeException, InvalidOperationException{
		return JSonConverter.getAsPlanList(super.listAll(1000,0).getData());
	}

	public Collection<Plan> listAllWithPagination(int totalPerPage, int page) throws PagarMeException, InvalidOperationException{
		return JSonConverter.getAsPlanList(super.listAll(totalPerPage,page).getData());
	}

	public Plan find(int id){
		this.id = id;
		Plan plan = null;
		try {
			plan = JSonConverter.getAsObject(result.getData(),Plan.class);
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		clonePlan(plan);
		return plan;
	}


	public Plan refresh(){
		Plan plan = null;
		try {
			plan = JSonConverter.getAsObject(result.getData(),Plan.class);
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		clonePlan(plan);
		return plan;
	}

	private void clonePlan(Plan plan) {
		this.amount = plan.amount;
		this.color = plan.color;
		this.dateCreated = plan.dateCreated;
		this.days = plan.days;
		this.freezed = plan.freezed;
		this.id = plan.id;
		this.name = plan.name;
		this.trialDays = plan.trialDays;

	}

	@Override
	protected void validate() throws FormatException {

		if(Double.parseDouble(days) <=0)
			throw new FormatException("Days should be positive");
		if (Double.parseDouble(trialDays) < 0)
			throw new FormatException("TrialDays should be zero or positive");

	}
}