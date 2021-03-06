package br.dagostini.jshare.comum.pojos;

import java.io.File;
import java.io.Serializable;

public class Arquivo implements Serializable {

	private static final long serialVersionUID = 8077295408159335912L;
	
	private String nome;
	private long tamanho;
	private File file;
	
	public File getFile(){
		return file;
	}
	
	public void setFile(File file){
		this.file = new File(file.toString());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}
	
	@Override
	public String toString() {
		return nome+" - "+tamanho;
	}
}
