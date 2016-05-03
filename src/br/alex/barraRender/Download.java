package br.alex.barraRender;

import br.dagostini.jshare.comum.pojos.Arquivo;

public class Download {

	private Arquivo arquivo;
	private float progress;
	
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	
}
