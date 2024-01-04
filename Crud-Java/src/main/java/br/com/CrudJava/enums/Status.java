package br.com.CrudJava.enums;

public enum Status {
	ACTIVE("ativo"), INACTIVE("inativo");

	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
}
