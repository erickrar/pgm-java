package pagarme;

public enum CustomerDocumentType {
	NONE("none"),cpf("cpf"),cnpj("cnpj");
	private String type;

	CustomerDocumentType(String type){
		this.type=type;
	}
	
	@Override
	public String toString() {
	return type;
	}
};

