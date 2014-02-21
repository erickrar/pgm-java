package pagarme;

import java.util.ArrayList;
import java.util.List;

import pagarme.converter.JSonConverter;
import pagarme.exception.FormatException;
import pagarme.exception.InvalidOperationException;
import pagarme.exception.PagarMeException;

import com.google.gson.annotations.SerializedName;

public abstract class PagarMeModel {

	protected int id;
	private List<String> dirtyValues;
	protected PagarMeProvider provider;
	protected  PagarMeQueryResponse result;

	protected String className;

	@SerializedName("do_not_track")
	private boolean doNotTrack;
	private boolean local;

	@SerializedName("date_created")
	protected String dateCreated;


	public PagarMeModel(){
		dirtyValues = new ArrayList<String>();
		local = true;
		className = getClass().getSimpleName().toLowerCase()+"s";
	}

	public PagarMeModel(PagarMeProvider provider){
		this.provider=provider;
	}

	public PagarMeModel(PagarMeProvider provider, PagarMeQueryResponse result){
		this(provider);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProvider(PagarMeProvider provider){
		this.provider=provider;
	}

	protected PagarMeQueryResponse refreshModel(){
		if(provider==null)
			return null;
		result = new PagarMeQuery(provider, "GET",String.format("%s/%s",className,id)).execute();
		return result;
	}

	public void refresh(PagarMeQueryResponse response) throws PagarMeException  {
		response.validate();
	}

	protected PagarMeQueryResponse find() throws InvalidOperationException{
		if(provider==null)
			throw new InvalidOperationException("The PagarMeProvider must be set in order to use this method.");
		result = new PagarMeQuery(provider, "GET",String.format("%s/%s",className,id)).execute();
		return result;
	}

	protected PagarMeQueryResponse listAll(int totalPerPage, int page) throws InvalidOperationException{
		if(provider==null)
			throw new InvalidOperationException("The PagarMeProvider must be set in order to use this method.");
		PagarMeQuery query = new PagarMeQuery(provider, "GET",String.format("%s",className));
		if(totalPerPage>0)
			query.addQuery("count", String.valueOf(totalPerPage));
		if(page>0)
			query.addQuery("page", String.valueOf(page));
		result = query.execute();
		return result;
	}


	protected void save() throws InvalidOperationException, PagarMeException{
		if (provider == null)
			throw new InvalidOperationException("The PagarMeProvider must be set in order to use this method.");

		PagarMeQuery query = local  ?
				new PagarMeQuery(provider, "POST",getClass().getSimpleName().toLowerCase()):  
					new PagarMeQuery(provider, "PUT", String.format("%s/%s/", className,id));

				
				query.addQueries(JSonConverter.objectToMap(this));
				doNotTrack = true;
				query.execute();
				doNotTrack = false;
				dirtyValues.clear();			  
	}

	protected  abstract void validate() throws FormatException;

	protected void addToDityList(String name){
		if(doNotTrack)
			return;

		for(String s: dirtyValues){
			if (s.startsWith(name+"."))
				dirtyValues.remove(s);
		}
		dirtyValues.add(name);
	}

}
