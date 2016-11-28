package pagarme;

import pagarme.exception.PagarMeException;

public class PagarMeQueryResponse {

	private int status;
	private String data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) throws PagarMeException {
		this.status = status;
		 //validate();
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	 public void validate() throws PagarMeException   {
         if (status != 200)
             throw new PagarMeException("");
     }
}
