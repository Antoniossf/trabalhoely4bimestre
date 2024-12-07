package org.animal;

public class Retorno1 {
	private Boolean resposta;
	private String mensagem;
	
	
	public Boolean getResposta() {
		return resposta;
	}
	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return "Retorno [resposta=" + resposta + ", mensagem=" + mensagem + "]";
	}
	
	public Retorno1(boolean resposta, String mensagem) {
		this.resposta = resposta;
		this.mensagem = mensagem;
		
		
	}
	

}