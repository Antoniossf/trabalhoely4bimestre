package org.animal;


public class Retorno1Dao {

	public Retorno1 RetornoJson(boolean resposta, String mensagem) {
		Retorno1 retorno = new Retorno1(resposta, mensagem);
		
		return retorno;		
	}
	
}