package br.menu.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.exemplos.LeituraEscritaDeArquivos;
import br.dagostini.exemplos.LerIp;
import br.dagostini.exemplos.ListarDiretoriosArquivos;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.menu.servidor.MenuServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class MenUI extends JFrame implements IServer {

	private JTable tb_dowload;
	private JLabel lblNome;
	private JTextField txt_user;
	private JLabel lblNomeDoArquivo;
	private JTextField txt_NArquivo;
	private JLabel lblIpServidor;
	private JTextField txt_IPServidor;
	private JLabel lblPortaServidor;
	private JTextField txt_portaServidor;
	private JButton bt_pesquisar;
	private JButton bt_fechar;
	private JPanel contentPane;
	private JTextField txt_portaLocal;
	private JComboBox<String> cbx_ipLocal;
	private JButton bt_iniciaServico;
	private JButton bt_paraServico;
	private JButton bt_conectar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenUI frame = new MenUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 302);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 213, 0, 0, 76, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblIp = new JLabel("Meu IP:");
		lblIp.setFont(new Font("Arial", Font.BOLD, 12));
		lblIp.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);

		cbx_ipLocal = new JComboBox<>();
		cbx_ipLocal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_cbx_ipLocal = new GridBagConstraints();
		gbc_cbx_ipLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbx_ipLocal.insets = new Insets(0, 0, 5, 5);
		gbc_cbx_ipLocal.gridx = 1;
		gbc_cbx_ipLocal.gridy = 0;
		contentPane.add(cbx_ipLocal, gbc_cbx_ipLocal);

		JLabel lblPorta = new JLabel("PORTA:");
		lblPorta.setFont(new Font("Arial", Font.BOLD, 12));
		lblPorta.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 0;
		contentPane.add(lblPorta, gbc_lblPorta);

		txt_portaLocal = new JTextField();
		txt_portaLocal.setText("1818");
		txt_portaLocal.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_portaLocal = new GridBagConstraints();
		gbc_txt_portaLocal.fill = GridBagConstraints.BOTH;
		gbc_txt_portaLocal.insets = new Insets(0, 0, 5, 5);
		gbc_txt_portaLocal.gridx = 3;
		gbc_txt_portaLocal.gridy = 0;
		contentPane.add(txt_portaLocal, gbc_txt_portaLocal);
		txt_portaLocal.setColumns(10);

		bt_iniciaServico = new JButton("Inicia serviço");
		GridBagConstraints gbc_bt_iniciaServico = new GridBagConstraints();
		gbc_bt_iniciaServico.insets = new Insets(0, 0, 5, 5);
		gbc_bt_iniciaServico.gridx = 4;
		gbc_bt_iniciaServico.gridy = 0;
		contentPane.add(bt_iniciaServico, gbc_bt_iniciaServico);

		bt_paraServico = new JButton("Para serviço");
		GridBagConstraints gbc_bt_paraServio = new GridBagConstraints();
		gbc_bt_paraServio.insets = new Insets(0, 0, 5, 5);
		gbc_bt_paraServio.gridx = 5;
		gbc_bt_paraServio.gridy = 0;
		contentPane.add(bt_paraServico, gbc_bt_paraServio);

		lblNome = new JLabel("Nome usuário:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);

		txt_user = new JTextField();
		txt_user.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_user = new GridBagConstraints();
		gbc_txt_user.insets = new Insets(0, 0, 5, 5);
		gbc_txt_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_user.gridx = 1;
		gbc_txt_user.gridy = 1;
		contentPane.add(txt_user, gbc_txt_user);
		txt_user.setColumns(10);

		lblIpServidor = new JLabel("IP servidor:");
		lblIpServidor.setForeground(Color.GREEN);
		lblIpServidor.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_lblIpServidor = new GridBagConstraints();
		gbc_lblIpServidor.anchor = GridBagConstraints.EAST;
		gbc_lblIpServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpServidor.gridx = 2;
		gbc_lblIpServidor.gridy = 1;
		contentPane.add(lblIpServidor, gbc_lblIpServidor);

		txt_IPServidor = new JTextField();
		txt_IPServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_IPServidor = new GridBagConstraints();
		gbc_txt_IPServidor.anchor = GridBagConstraints.NORTH;
		gbc_txt_IPServidor.gridwidth = 2;
		gbc_txt_IPServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_IPServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_IPServidor.gridx = 3;
		gbc_txt_IPServidor.gridy = 1;
		contentPane.add(txt_IPServidor, gbc_txt_IPServidor);
		txt_IPServidor.setColumns(10);

		lblPortaServidor = new JLabel("Porta servidor:");
		lblPortaServidor.setForeground(Color.GREEN);
		lblPortaServidor.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPortaServidor = new GridBagConstraints();
		gbc_lblPortaServidor.anchor = GridBagConstraints.EAST;
		gbc_lblPortaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaServidor.gridx = 5;
		gbc_lblPortaServidor.gridy = 1;
		contentPane.add(lblPortaServidor, gbc_lblPortaServidor);

		txt_portaServidor = new JTextField();
		txt_portaServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_portaServidor.setText("1818");
		GridBagConstraints gbc_txt_portaServidor = new GridBagConstraints();
		gbc_txt_portaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_portaServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_portaServidor.gridx = 6;
		gbc_txt_portaServidor.gridy = 1;
		contentPane.add(txt_portaServidor, gbc_txt_portaServidor);
		txt_portaServidor.setColumns(10);

		bt_conectar = new JButton("Conectar");
		GridBagConstraints gbc_bt_conectar = new GridBagConstraints();
		gbc_bt_conectar.insets = new Insets(0, 0, 5, 0);
		gbc_bt_conectar.gridx = 7;
		gbc_bt_conectar.gridy = 1;
		contentPane.add(bt_conectar, gbc_bt_conectar);

		lblNomeDoArquivo = new JLabel("Nome do arquivo:");
		lblNomeDoArquivo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNomeDoArquivo.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblNomeDoArquivo = new GridBagConstraints();
		gbc_lblNomeDoArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblNomeDoArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoArquivo.gridx = 0;
		gbc_lblNomeDoArquivo.gridy = 2;
		contentPane.add(lblNomeDoArquivo, gbc_lblNomeDoArquivo);

		txt_NArquivo = new JTextField();
		txt_NArquivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_NArquivo = new GridBagConstraints();
		gbc_txt_NArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_txt_NArquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_NArquivo.gridx = 1;
		gbc_txt_NArquivo.gridy = 2;
		contentPane.add(txt_NArquivo, gbc_txt_NArquivo);
		txt_NArquivo.setColumns(10);

		bt_pesquisar = new JButton("Pesquisar Arq.");
		bt_pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_pesquisar = new GridBagConstraints();
		gbc_bt_pesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_bt_pesquisar.gridx = 2;
		gbc_bt_pesquisar.gridy = 2;
		contentPane.add(bt_pesquisar, gbc_bt_pesquisar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);

		tb_dowload = new JTable();
		scrollPane.setViewportView(tb_dowload);

		bt_fechar = new JButton("SAIR");
		bt_fechar.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_fechar = new GridBagConstraints();
		gbc_bt_fechar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_fechar.gridx = 7;
		gbc_bt_fechar.gridy = 6;
		contentPane.add(bt_fechar, gbc_bt_fechar);

		configurar();
	}

	// =========================
	// instâncias de variavéis local
	// =========================

	/** Contém todos cliente conectados no servidor */
	private Map<String, Cliente> mapaClientes = new HashMap<>();

	/** Contém todos os nomes dos arquivos diponibilizados pelos usuários */
	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<>();

	/** Referência a esse próprio objeto depois de exportar */
	private IServer servico;

	private Cliente cliente;

	// =================================
	// instacias de variaveis do servico
	// =================================

	private Map<String, Cliente> mapaClienteServico = new HashMap<>();

	private Map<Cliente, List<Arquivo>> mapaArquivosServico = new HashMap<>();

	private IServer servicoServidor;

	private Registry registry;

	// ==================================================================
	// Métodos
	// ==================================================================
	private void configurar() {

		cbx_ipLocal.addItem(new LerIp().retornarIP());

		txt_user.setText("Alex");
		txt_IPServidor.setText("127.0.0.1");
		txt_NArquivo.setText("resultados.txt");

		bt_paraServico.setEnabled(false);
		bt_pesquisar.setEnabled(false);

		bt_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});

		bt_iniciaServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServico();
			}
		});

		bt_paraServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarServico();
			}
		});

		bt_conectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		// se fechar a tela pelo Close, ela irá fechar a conexão
		// e derrubar todos que estão conectados ao mesmo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					for (int i = 0; i < mapaClientes.size(); i++) {
						desconectar(mapaClientes.get(i));
					}
					if(servico != null)
						servico.desconectar(cliente);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		bt_fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					for (int i = 0; i < mapaClientes.size(); i++) {
						desconectar(mapaClientes.get(i));
					}
					if(servico != null)
						servico.desconectar(cliente);
					encerrarServico();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	protected void conectar() {
		// nome
		String nome = txt_user.getText().trim();
		if (nome.length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa digitar um nome!");
			return;
		}

		// Endereço IP
		String host = txt_IPServidor.getText().trim();
		if (!host.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "O endereço ip parece inválido!");
			return;
		}

		// Porta
		String strPorta = txt_portaServidor.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int porta = Integer.parseInt(strPorta);

		try {
			cliente = new Cliente();
			cliente.setNome(nome);
			cliente.setIp(host);
			cliente.setPorta(porta);

			servico = (IServer) Naming.lookup("rmi://" + host + ":" + porta + "/" + IServer.NOME_SERVICO);

			servico.registrarCliente(cliente);
			servico.publicarListaArquivos(cliente, new ListarDiretoriosArquivos().listarArquivos());

			bt_pesquisar.setEnabled(true);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"\n\n-------------------------------------------------------\n"
							+ "ERRO: VERIFIQUE SE O SERVIDOR ESTÁ RODANDO, SE O IP E PORTA ESTÃO"
							+ " CORRETOS, SE NÃO HÁ BLOQUEIO DE FIREWALL OU ANTIVIRUS.\n"
							+ "-------------------------------------------------------------------\n\n");
			e.printStackTrace();
		}

	}

	protected void pesquisar() {
		try {
			Map<Cliente, List<Arquivo>> resultList = servico.procurarArquivo(txt_NArquivo.getText().trim());
			for (Map.Entry<Cliente, List<Arquivo>> entry : resultList.entrySet()) {
				for (Arquivo arq : entry.getValue()) {
					System.err.println("Arquivo: " + arq.getNome() + " - tamanho:" + arq.getTamanho());
					escreverDowload(servico.baixarArquivo(arq), arq.getFile());
				}
			}
			System.out.println("fim");
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar");
			e.printStackTrace();
		}
	}

	private void escreverDowload(byte[] dados, File nome) {
		new LeituraEscritaDeArquivos().escreva(new File(".\\Share\\Upload\\" + "Cópia de " + nome.getName()), dados);
	}

	// ===================================================================
	// metódos para iniciar serviço para outros clientes possa se conectar
	// ===================================================================

	protected void iniciarServico() {
		String strPorta = txt_portaLocal.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser uma valor númerico de no máximo 5 dígitos!");
			return;
		}

		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			JOptionPane.showMessageDialog(this, "A porta deve estar entre os valores de 1024 à 65535!");
			return;
		}

		try {
			servicoServidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(IServer.NOME_SERVICO, servicoServidor);

			cbx_ipLocal.setEnabled(false);
			txt_portaLocal.setEnabled(false);
			bt_iniciaServico.setEnabled(false);

			bt_paraServico.setEnabled(true);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}
	}

	protected void encerrarServico() {

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);

			cbx_ipLocal.setEnabled(true);
			txt_portaLocal.setEnabled(true);
			bt_iniciaServico.setEnabled(true);

			bt_paraServico.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ===================================================================
	// Implemetação dos métodos da Interface IServer
	// ===================================================================

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		if (mapaClienteServico.get(c.getIp()) != null) {
			// mostrar("Retornando erro para cliente duplicado.");

			throw new RemoteException("Alguém já está conectado com este IP: " + c.getIp());
		}

		// mostrar(c.getNome() + ", com ip:" + c.getIp() + " se conectou.");
		mapaClienteServico.put(c.getIp(), c);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// mostrar("pesquisa: Arq ->" + nome);

		Map<Cliente, List<Arquivo>> resultMapArq = new HashMap<>();
		List<Arquivo> listArq = new ArrayList<>();
		for (Map.Entry<Cliente, List<Arquivo>> entry : mapaArquivosServico.entrySet()) {
			for (Arquivo arq : entry.getValue()) {
				if (arq.getNome().equals(nome)) {
					listArq.add(arq);
				}
			}
			if (listArq.size() > 0) {
				resultMapArq.put(entry.getKey(), listArq);
				listArq.removeAll(listArq);
			}
		}
		return resultMapArq;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {

		File f = new File(".\\Share\\Dowload\\" + arq.getNome());

		byte[] dados = new LeituraEscritaDeArquivos().leia(f);

		return dados;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapaClientes.remove(c);
		mapaArquivos.remove(c);
		// mostrar("Servidor encerrado e cliente removido: " + c.getNome());
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {

		if (mapaArquivosServico.get(c.getIp()) != null) {
			// mostrar("Retornando erro para cliente duplicado.");

			throw new RemoteException("Já está na lista este IP");
		}
		for (Arquivo arquivo : lista) {
			JOptionPane.showConfirmDialog(null, arquivo.getNome() + " : " + arquivo.getTamanho());
		}
		mapaArquivosServico.put(c, lista);
	}
}
