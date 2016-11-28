package pagarme;

import com.google.gson.annotations.SerializedName;

public class CardHashKey {

	
	private int id;
	@SerializedName("date_created")
	private String dateCreated;
	
	@SerializedName("public_key")
	private String publickey;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	
	
}
