package com.srm.infra;

public class Erro {
	
	private String parametro;
	private String erro;
	
	public Erro() {
	}
	
	public Erro(String parametro, String erro) {
		super();
		this.parametro = parametro;
		this.erro = erro;
	}

	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}

	@Override
	public String toString() {
		return "Erro [parametro=" + parametro + ", erro=" + erro + "]";
	}

}
