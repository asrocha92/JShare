package br.dagostini.exemplos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;

public class ListarDiretoriosArquivos {
	
	private File dirStart = new File(".\\Share\\Dowload\\.");	
	
	public List<Diretorio> listarDiretorios(){
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (!file.isFile()) {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);	
			}
		}
		return listaDiretorios;
	}
	
	public List<Arquivo> listarArquivos(){
		List<Arquivo> listaArquivos = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			}	
		}
		return listaArquivos;
	}
	
	

	public static void main(String[] args) {
		
		ListarDiretoriosArquivos lda = new ListarDiretoriosArquivos();
		
		System.out.println("Diretórios");
		for (Diretorio dir : lda.listarDiretorios()) {
			System.out.println("\t" + dir.getNome());
		}
		
		System.out.println("Arquivos");
		for (Arquivo arq : lda.listarArquivos()) {
			System.out.println("\t" + arq.getTamanho() + "\t" + arq.getNome());
		}

	}
}
