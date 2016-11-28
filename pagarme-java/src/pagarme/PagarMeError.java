package pagarme;


import com.google.gson.annotations.SerializedName;

public class PagarMeError {


	@SerializedName("errors")
	private PagarMeInternalError []  list;
	private String url;
	private String method;
	
	public PagarMeInternalError [] getList() {
		return list;
	}

	public void setList(PagarMeInternalError [] list) {
		this.list = list;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	
	public String showErrors(){
		StringBuilder builder = new StringBuilder();
		builder.append("errors: \n");
		for(PagarMeInternalError pie : list){
			builder.append(pie.getParameterName());
			builder.append(": ");
			builder.append(pie.getMessage());
			builder.append("\n");
		}
		return builder.toString();
	}
}
