package pagarme;

import com.google.gson.annotations.SerializedName;

public class PagarMeInternalError {

	
	@SerializedName("type")
	private String type;
	
	@SerializedName("parameter_name")
	private String parameterName;
	
	@SerializedName("message")
	private String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
