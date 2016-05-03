package br.alex.util;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;

public class EmendaArqCliente {
	private Cliente cliente = new Cliente();
	private Arquivo arquivo = new Arquivo();
	
	public Cliente getCliente() {
		return cliente;
	}
	public Arquivo getArquivo() {
		return arquivo;
	}
}
