package br.menu.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import br.alex.util.EmendaArqCliente;
import br.alex.util.Util;
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
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.rmi.ConnectIOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JList;

public class MenUI2 extends JFrame implements IServer {
	private JLabel lblNome;
	private JTextField txt_user;
	private JLabel lblNomeDoArquivo;
	private JTextField txt_nomeArq;
	private JLabel lblIpServidor;
	private JTextField txt_IPServidor;
	private JLabel lblPortaServidor;
	private JTextField txt_portaServidor;
	private JButton bt_pesquisar;
	private JButton bt_sair;
	private JPanel contentPane;
	private JTextField txt_portaLocal;
	private JComboBox<String> cbx_ipLocal;
	private JButton bt_iniciaServico;
	private JButton bt_paraServico;
	private JButton bt_conectar;
	private JButton bt_dowload;
	private JScrollPane scrollPane;
	private JTable tb_dowload;
	private JTextArea txtA_logs;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenUI2 frame = new MenUI2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenUI2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 489);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 123, 213, 0, 109, 39, 55, -5, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblNome = new JLabel("Nome usuário:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		txt_user = new JTextField();
		txt_user.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_user = new GridBagConstraints();
		gbc_txt_user.insets = new Insets(0, 0, 5, 5);
		gbc_txt_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_user.gridx = 1;
		gbc_txt_user.gridy = 0;
		contentPane.add(txt_user, gbc_txt_user);
		txt_user.setColumns(10);

		lblIpServidor = new JLabel("IP servidor:");
		lblIpServidor.setForeground(Color.GREEN);
		lblIpServidor.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_lblIpServidor = new GridBagConstraints();
		gbc_lblIpServidor.anchor = GridBagConstraints.EAST;
		gbc_lblIpServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpServidor.gridx = 2;
		gbc_lblIpServidor.gridy = 0;
		contentPane.add(lblIpServidor, gbc_lblIpServidor);

		txt_IPServidor = new JTextField();
		txt_IPServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_IPServidor = new GridBagConstraints();
		gbc_txt_IPServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_IPServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_IPServidor.gridx = 3;
		gbc_txt_IPServidor.gridy = 0;
		contentPane.add(txt_IPServidor, gbc_txt_IPServidor);
		txt_IPServidor.setColumns(10);

		lblPortaServidor = new JLabel("Porta servidor:");
		lblPortaServidor.setForeground(Color.GREEN);
		lblPortaServidor.setFont(new Font("Arial", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPortaServidor = new GridBagConstraints();
		gbc_lblPortaServidor.anchor = GridBagConstraints.EAST;
		gbc_lblPortaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaServidor.gridx = 4;
		gbc_lblPortaServidor.gridy = 0;
		contentPane.add(lblPortaServidor, gbc_lblPortaServidor);

		txt_portaServidor = new JTextField();
		txt_portaServidor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_portaServidor.setText("1818");
		GridBagConstraints gbc_txt_portaServidor = new GridBagConstraints();
		gbc_txt_portaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_portaServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_portaServidor.gridx = 5;
		gbc_txt_portaServidor.gridy = 0;
		contentPane.add(txt_portaServidor, gbc_txt_portaServidor);
		txt_portaServidor.setColumns(10);

		bt_conectar = new JButton("Conectar");
		GridBagConstraints gbc_bt_conectar = new GridBagConstraints();
		gbc_bt_conectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_conectar.gridwidth = 2;
		gbc_bt_conectar.insets = new Insets(0, 0, 5, 0);
		gbc_bt_conectar.gridx = 6;
		gbc_bt_conectar.gridy = 0;
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

		txt_nomeArq = new JTextField();
		txt_nomeArq.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_nomeArq = new GridBagConstraints();
		gbc_txt_nomeArq.insets = new Insets(0, 0, 5, 5);
		gbc_txt_nomeArq.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_nomeArq.gridx = 1;
		gbc_txt_nomeArq.gridy = 2;
		contentPane.add(txt_nomeArq, gbc_txt_nomeArq);
		txt_nomeArq.setColumns(10);

		bt_pesquisar = new JButton("Pesquisar Arquivo");
		bt_pesquisar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_pesquisar = new GridBagConstraints();
		gbc_bt_pesquisar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_pesquisar.gridwidth = 2;
		gbc_bt_pesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_bt_pesquisar.gridx = 2;
		gbc_bt_pesquisar.gridy = 2;
		contentPane.add(bt_pesquisar, gbc_bt_pesquisar);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 8;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		table_pesquisa = new JTable();
		scrollPane_1.setViewportView(table_pesquisa);

		bt_dowload = new JButton("Baixar arquivo");
		GridBagConstraints gbc_bt_dowload = new GridBagConstraints();
		gbc_bt_dowload.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_dowload.insets = new Insets(0, 0, 5, 0);
		gbc_bt_dowload.gridx = 7;
		gbc_bt_dowload.gridy = 4;
		contentPane.add(bt_dowload, gbc_bt_dowload);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);

		tb_dowload = new JTable();
		scrollPane.setViewportView(tb_dowload);

		JLabel lblIp = new JLabel("Meu IP:");
		lblIp.setFont(new Font("Arial", Font.BOLD, 12));
		lblIp.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 6;
		contentPane.add(lblIp, gbc_lblIp);

		cbx_ipLocal = new JComboBox<>();
		cbx_ipLocal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_cbx_ipLocal = new GridBagConstraints();
		gbc_cbx_ipLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbx_ipLocal.insets = new Insets(0, 0, 5, 5);
		gbc_cbx_ipLocal.gridx = 1;
		gbc_cbx_ipLocal.gridy = 6;
		contentPane.add(cbx_ipLocal, gbc_cbx_ipLocal);

		JLabel lblPorta = new JLabel("Porta local:");
		lblPorta.setFont(new Font("Arial", Font.BOLD, 12));
		lblPorta.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 6;
		contentPane.add(lblPorta, gbc_lblPorta);

		txt_portaLocal = new JTextField();
		txt_portaLocal.setText("1919");
		txt_portaLocal.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_portaLocal = new GridBagConstraints();
		gbc_txt_portaLocal.fill = GridBagConstraints.BOTH;
		gbc_txt_portaLocal.insets = new Insets(0, 0, 5, 5);
		gbc_txt_portaLocal.gridx = 3;
		gbc_txt_portaLocal.gridy = 6;
		contentPane.add(txt_portaLocal, gbc_txt_portaLocal);
		txt_portaLocal.setColumns(10);

		bt_iniciaServico = new JButton("Inicia serviço");
		GridBagConstraints gbc_bt_iniciaServico = new GridBagConstraints();
		gbc_bt_iniciaServico.insets = new Insets(0, 0, 5, 5);
		gbc_bt_iniciaServico.gridx = 4;
		gbc_bt_iniciaServico.gridy = 6;
		contentPane.add(bt_iniciaServico, gbc_bt_iniciaServico);

		bt_paraServico = new JButton("Para serviço");
		GridBagConstraints gbc_bt_paraServio = new GridBagConstraints();
		gbc_bt_paraServio.insets = new Insets(0, 0, 5, 5);
		gbc_bt_paraServio.gridx = 5;
		gbc_bt_paraServio.gridy = 6;
		contentPane.add(bt_paraServico, gbc_bt_paraServio);

		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 8;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 7;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);

		txtA_logs = new JTextArea();
		scrollPane_2.setViewportView(txtA_logs);
		txtA_logs.setBackground(new Color(255, 255, 153));

		bt_sair = new JButton("SAIR");
		bt_sair.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_sair = new GridBagConstraints();
		gbc_bt_sair.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_sair.gridx = 7;
		gbc_bt_sair.gridy = 8;
		contentPane.add(bt_sair, gbc_bt_sair);

		configurar();
	}

	// ==================================================================
	// instâncias de variavéis local
	// ==================================================================

	/**
	 * Vai conter o IP do servidor Fixo, caso de falha volte a conectar no
	 * servidor atual
	 */
	private static String IPservidorFixo = null;

	private static String PORTAservidorFixo = null;

	/** Contém todos cliente conectados no servidor */
	private Map<String, Cliente> mapaClientes = new HashMap<>();

	/** Contém todos os nomes dos arquivos diponibilizados pelos usuários */
	private Map<Cliente, List<Arquivo>> resultMapArquivos = new HashMap<>();

	/** Referência a esse próprio objeto depois de exportar */
	private IServer servico = null;

	private Cliente cliente = null;

	// ==================================================================
	// Métodos
	// ==================================================================
	private void configurar() {

		carregarTabela();

		cbx_ipLocal.addItem(new LerIp().retornarIP());

		txt_user.setText("Alex");
		txt_IPServidor.setText("127.0.0.1");
		txt_nomeArq.setText("resultados.txt");

		bt_paraServico.setEnabled(false);
		bt_pesquisar.setEnabled(false);

		bt_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});

		bt_iniciaServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServ();
			}
		});

		bt_paraServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarServ();
			}
		});

		bt_conectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar(txt_IPServidor.getText(), txt_portaServidor.getText(), 0);
			}
		});

		bt_dowload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerDowload();
			}
		});

		// se fechar a tela pelo Close, ela irá fechar a conexão
		// e derrubar todos que estão conectados ao mesmo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				encerrar();
			}
		});

		bt_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				encerrar();
			}
		});

	}

	protected void conectar(String hostServidor, String portaServidor, int contaErro) {
		Util util = new Util();
		try {
			hostServidor = util.vrfIP(hostServidor);
			int porta = util.vrfPorta(portaServidor);

			instaciarClient();

			servico = (IServer) Naming.lookup("rmi://" + hostServidor + ":" + porta + "/" + IServer.NOME_SERVICO);

			servico.registrarCliente(cliente);
			servico.publicarListaArquivos(cliente, new ListarDiretoriosArquivos().listarArquivos());

			bt_pesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Conectado ao servidor!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage() + "\n\n-------------------------------------------------------\n"
							+ "ERRO: VERIFIQUE SE O SERVIDOR ESTÁ RODANDO, SE O IP E PORTA ESTÃO"
							+ " CORRETOS, SE NÃO HÁ BLOQUEIO DE FIREWALL OU ANTIVIRUS.\n"
							+ "-------------------------------------------------------------------\n\n");
			if(contaErro < 2){
				JOptionPane.showMessageDialog(this, "Reconectando ao servidor");
				conectar(IPservidorFixo, PORTAservidorFixo, contaErro + 1);				
			}else{
				JOptionPane.showMessageDialog(this, "Não é possível conectar ao servidor");
			}
		}

	}

	protected void encerrar() {
		try {
			for (int i = 0; i < mapaClientes.size(); i++) {
				desconectar(mapaClientes.get(i));
			}
			if (servico != null)
				servico.desconectar(cliente);
			if (servidorServ != null)
				encerrarServ();
			JOptionPane.showMessageDialog(this, "Encerrado serviço.");
		} catch (RemoteException e1) {
			return;
		}
	}

	public void carregarTabela() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				table = new TableArquivos();
				table_pesquisa.setModel(table);
			}
		}).start();
	}

	protected void pesquisar() {
		try {
			table_pesquisa.setModel(table.atualizarListaClinArq(servico.procurarArquivo(txt_nomeArq.getText())));
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar, ou conexão com o servidor");
			conectar(IPservidorFixo, PORTAservidorFixo, 0);
			JOptionPane.showMessageDialog(this, "Reconectando ao servidor atual");
		}
	}

	private void fazerDowload() {
		try {
			int row = table_pesquisa.getSelectedRow();
			if (row > -1) {
				EmendaArqCliente result = table.retornarDados(row);
				// servico volta a ser nulo
				servico = null;
				// muda ip e porta para conectar no cliente que tem o
				// arquivo disponivel para dowload
				txt_IPServidor.setText(result.getCliente().getIp());
				txt_portaServidor.setText(String.valueOf(result.getCliente().getPorta()));
				// faz a conexão no cliente
				conectar(txt_IPServidor.getText(), txt_portaServidor.getText(), 0);
				// baixa arquivo e escre na pasta upload
				escreverDowload(servico.baixarArquivo(result.getArquivo()), result.getArquivo().getFile());

				JOptionPane.showMessageDialog(this, "Efetuado download com sucesso.");
				// return;
			} else {
				JOptionPane.showMessageDialog(this, "Selecione um item para que possa ser baixado!");
			}
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao fazer dowload\n"+e.getMessage());
			JOptionPane.showMessageDialog(this, "Reconectando ao servidor pai");
			conectar(IPservidorFixo, PORTAservidorFixo, 1);
		}
	}

	private void escreverDowload(byte[] dados, File nome) {
		try{
			JOptionPane.showMessageDialog(this,nome.getName());
			new LeituraEscritaDeArquivos().escreva(new File(".\\Share\\Download\\" + "Cópia de " + nome.getName()), dados);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage() + "\nLocalização:\n" + e.getLocalizedMessage());
		}
	}

	private void instaciarClient() throws RuntimeException {
		Util util = new Util();
		if (cliente == null) {
			if (IPservidorFixo == null || PORTAservidorFixo == null) {
				IPservidorFixo = txt_IPServidor.getText();
				PORTAservidorFixo = txt_portaServidor.getText();
			}

			cliente = new Cliente();
			cliente.setNome(util.vrfNome(txt_user.getText()));
			cliente.setIp(util.vrfIP(cbx_ipLocal.getSelectedItem().toString()));
			cliente.setPorta(util.vrfPorta(txt_portaLocal.getText()));
		} else {
			cliente.setNome(util.vrfNome(txt_user.getText()));
			cliente.setIp(util.vrfIP(cbx_ipLocal.getSelectedItem().toString()));
			cliente.setPorta(util.vrfPorta(txt_portaLocal.getText()));
		}
	}

	// ===================================================================
	// Instâncias de variavéis para o serviço
	// ===================================================================

	/** Formata data */
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	/** Contém todos cliente conectados no servidor */
	private Map<String, Cliente> mapClientServ = new HashMap<>();

	/** Contém todos os nomes dos arquivos diponibilizados pelos usuários */
	private Map<Cliente, List<Arquivo>> mapArqServ = new HashMap<>();

	/** Referência a esse próprio objeto depois de exportar */
	private IServer servidorServ;

	/**
	 * Referência onde o objeto exporta será buscado pelo nom, é o registro que
	 * escuta na porta TCP/IP
	 */
	private Registry registryClientServ;
	private JTable table_pesquisa;
	private TableArquivos table;

	// ====================================================================================
	// Métodos
	// ====================================================================================

	protected void iniciarServ() {
		try {
			servidorServ = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registryClientServ = LocateRegistry.createRegistry(new Util().vrfPorta(txt_portaLocal.getText()));
			registryClientServ.rebind(IServer.NOME_SERVICO, servidorServ);

			mostrar("Serviço iniciado");

			cbx_ipLocal.setEnabled(false);
			txt_portaLocal.setEnabled(false);
			bt_iniciaServico.setEnabled(false);

			bt_paraServico.setEnabled(true);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()
					+ "\n\nErro ao iniciar serviço, verifique se a porta, já está sendo usada ou se firewall está bloqueando.");
			return;
		}
	}

	protected void encerrarServ() {
		mostrar("SERVIDOR PARANDO O SERVIÇO");

		encerrarTodosClientes();

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registryClientServ, true);

			txt_portaLocal.setEnabled(true);
			bt_iniciaServico.setEnabled(true);

			bt_paraServico.setEnabled(false);

			mostrar("SERVIDOR ENCERRADO.");

		} catch (Exception e) {
			return;
		}
	}

	protected void encerrarTodosClientes() {
		mostrar("ENCERRANDO CONEXÃO DE TODOS CLIENTES NO SERVIDOR");
	}

	private void mostrar(String string) {
		txtA_logs.append(sdf.format(new Date()));
		txtA_logs.append(" -> ");
		txtA_logs.append(string);
		txtA_logs.append("\n");
	}

	// ====================================================================================
	// Implemetação dos métodos da Interface IServe
	// =====================================================================================

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar(c.getNome() + ", com ip:" + c.getIp() + " se conectou.");
		mapClientServ.put(c.getIp(), c);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		mostrar("Foi pesquisado o \"Arq\" ->" + nome);
		nome.trim();
		// Nestá linha se não foi digitado nenhum arquivo, retorna a lista de
		// todos que estão conectados no serviço
		// e todo os arquivos disponibilizados por cada um no serviço
		if (nome.length() == 0)
			return mapArqServ;

		Map<Cliente, List<Arquivo>> resultMapArq = new HashMap<>();
		for (Map.Entry<Cliente, List<Arquivo>> entry : mapArqServ.entrySet()) {
			List<Arquivo> listArq = new ArrayList<>();
			for (Arquivo arq : entry.getValue()) {
				if (arq.getNome().equals(nome)) {
					listArq.add(arq);
				}
			}
			if (listArq.size() > 0) {
				resultMapArq.put(entry.getKey(), listArq);
			}
		}
		return resultMapArq;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		File file = new File(".\\Share\\Upload\\" + arq.getNome());
		byte[] dados = new LeituraEscritaDeArquivos().leia(file);
		mostrar("Feito dowload do -> " + arq.getNome());
		return dados;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapClientServ.remove(c);
		mapArqServ.remove(c);
		mostrar("Cliente: " + c.getNome().toUpperCase() + " desconectado!");
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			mostrar("Cliente:" + c.getNome() + "/ Publico arq: " + arquivo.getNome() + " : " + arquivo.getTamanho());
		}
		mapArqServ.put(c, lista);
	}

}
