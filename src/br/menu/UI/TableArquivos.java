package br.menu.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import br.alex.util.EmendaArqCliente;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;

public class TableArquivos extends AbstractTableModel {
	
	private List<EmendaArqCliente> lista = new ArrayList<>();
	
	public TableArquivos() {
		
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int row, int col) {
		EmendaArqCliente obj = lista.get(row);
		switch (col) {
		case 0: return obj.getCliente().getNome();
		case 1: return obj.getCliente().getIp();
		case 2: return obj.getCliente().getPorta();
		case 3: return obj.getArquivo().getNome();
		case 4: return obj.getArquivo().getTamanho();
		default:return "";
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0: return "CLIENTE";
		case 1: return "IP";
		case 2: return "PORTA";
		case 3: return "ARQUIVO";
		case 4: return "TAMANHO DO ARQUIVO";
		default: return "";
		}
	}

	public TableArquivos atualizarListaClinArq(Map<Cliente, List<Arquivo>> map){
		lista.removeAll(this.lista);
		for (Map.Entry<Cliente, List<Arquivo>> entry : map.entrySet()) {
			for (Arquivo arq : entry.getValue()) {
				EmendaArqCliente obj = new EmendaArqCliente();
				obj.getCliente().setNome(entry.getKey().getNome());
				obj.getCliente().setIp(entry.getKey().getIp());
				obj.getCliente().setPorta(entry.getKey().getPorta());
				obj.getArquivo().setNome(arq.getNome());
				obj.getArquivo().setTamanho(arq.getTamanho());
				obj.getArquivo().setFile(arq.getFile());
				System.out.print(obj.getCliente().getIp());
				lista.add(obj);
			}
		}
		this.fireTableStructureChanged();
		return this;
	}
	
	public EmendaArqCliente retornarDados(int row){
		return lista.get(row);
	}
	
}
