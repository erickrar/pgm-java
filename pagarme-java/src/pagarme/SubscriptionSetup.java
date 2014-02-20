package pagarme;

import com.google.gson.annotations.SerializedName;


public class SubscriptionSetup extends TransactionSetup {

	@SerializedName("plan_id")
	private String plan;

	@SerializedName("document_number")
	private String documentNumber;

	@SerializedName("document_type")
	private CustomerDocumentType documentType;

	@SerializedName("customer[email]")
	private String email;

	@SerializedName("customer[name]")
	private String name;

	@SerializedName("customer[sex]")
	private CustomerSex sex;

	@SerializedName("customer[born_at]")
	private String bornAt;
	
	 public SubscriptionSetup(){
		 plan="0";
	 }

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

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

	public void setPlan(int planId) {
		this.plan=String.valueOf(planId);
	} 
}
