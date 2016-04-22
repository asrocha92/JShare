package br.alex.util;

public class Util {

	public String vrfNome(String nome) {
		nome.trim();
		if (nome.length() == 0) {
			throw new RuntimeException("Você precisa digitar um nome!");
		}
		return nome;
	}
	
	public String vrfIP(String ip){
		ip.trim();
		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			throw new RuntimeException("O endereço ip parece inválido!");
		}
		return ip;
	}
	
	public int vrfPorta(String strPorta){
		strPorta.trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			throw new RuntimeException("A porta deve ser um valor numérico de no máximo 5 dígitos!");
		}
		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			throw new RuntimeException("A porta deve estar entre os valores de 1024 à 65535!");
		}
		return intPorta;
	}

}
