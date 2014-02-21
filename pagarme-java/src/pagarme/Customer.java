package pagarme;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pagarme.converter.JSonConverter;
import pagarme.exception.InvalidOperationException;
import pagarme.exception.PagarMeException;

import com.google.gson.annotations.SerializedName;


public class Customer extends PagarMeModel implements Serializable {

	private static final long serialVersionUID = -3571725106064938685L;

	@SerializedName("document_number")
	private String documentNumber;

	@SerializedName("document_type")
	private CustomerDocumentType documentType;

	@SerializedName("email")
	private String email;

	@SerializedName("name")
	private String name;

	@SerializedName("gender")
	private CustomerSex sex;

	@SerializedName("born_at")
	private String bornAt;

	@SerializedName("addresses")
	private List<CustomerAddress> addresses = new ArrayList<CustomerAddress>();

	@SerializedName("phones")
	private List<CustomerPhone> phones = new ArrayList<CustomerPhone>();

	private boolean freezed;

	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public CustomerDocumentType getDocumentType() {
		return documentType;
	}
	public void setDocumentType(CustomerDocumentType documentType) {
		this.documentType = documentType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public CustomerSex getSex() {
		return sex;
	}
	public void setSex(CustomerSex sex) {
		this.sex = sex;
	}
	public String getBornAt() {
		return bornAt;
	}
	public void setBornAt(String bornAt) {
		this.bornAt = bornAt;
	}
	public List<CustomerAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CustomerAddress> addresses) {
		this.addresses = addresses;
	}
	public List<CustomerPhone> getPhones() {
		return phones;
	}
	public void setPhones(List<CustomerPhone> phones) {
		this.phones = phones;
	}

	public Collection<Customer> listAll() throws PagarMeException, InvalidOperationException{
		return JSonConverter.getAsCustomerList(super.listAll(1000,0).getData());
	}

	public Collection<Customer> listAllWithPagination(int totalInPage, int page) throws PagarMeException, InvalidOperationException{
		return JSonConverter.getAsCustomerList(super.listAll(totalInPage,page).getData());
	}

	public void addAddress(CustomerAddress address){
		addresses.add(address);
	}
	
	public void addPhone(CustomerPhone phone){
		phones.add(phone);
	}
	
	public Customer find(int id) throws InvalidOperationException{
		this.id = id;
		Customer customer = null;
		try {
			customer = JSonConverter.getAsObject(find().getData(), Customer.class);
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		cloneCustomer(customer);
		return customer;
	}
	
	public void save() throws InvalidOperationException, PagarMeException{
		super.save();
		refresh();
	}


	public Customer refresh() throws InvalidOperationException{
		Customer customer = null;
		try {
			customer = JSonConverter.getAsObject(find().getData(), Customer.class);
		} catch (PagarMeException e) {
			e.printStackTrace();
		}
		cloneCustomer(customer);
		return customer;
	}	

	private void readObject(ObjectInputStream ois)	throws ClassNotFoundException, IOException {
		System.out.println("LOL");
		ois.defaultReadObject();
		freezed = true;
	}
	
	@Override
	protected void validate() {
		// TODO Auto-generated method stub

	}

	private void cloneCustomer(Customer customer){
		this.addresses = customer.addresses;
		this.bornAt = customer.bornAt;
		this.dateCreated = customer.dateCreated;
		this.documentNumber = customer.documentNumber;
		this.documentType = customer.documentType;
		this.email = customer.email;
		this.freezed = customer.freezed;
		this.id = customer.id;
		this.name = customer.name;
		this.phones = customer.phones;
		this.sex = customer.sex;
	}
}