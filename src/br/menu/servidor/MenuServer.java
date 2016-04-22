package br.menu.servidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.exemplos.LeituraEscritaDeArquivos;
import br.dagostini.exemplos.LerIp;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class MenuServer extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txt_porta;
	private JComboBox<String> cbx_ip;
	private JButton bt_IniciaServidor;
	private JButton bt_EncerraServidor;
	private JTextArea txtA_msg;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuServer frame = new MenuServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 304);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Arial", Font.BOLD, 12));
		lblIp.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		contentPane.add(lblIp, gbc_lblIp);

		cbx_ip = new JComboBox<>();
		cbx_ip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_cbx_ip = new GridBagConstraints();
		gbc_cbx_ip.insets = new Insets(0, 0, 5, 5);
		gbc_cbx_ip.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbx_ip.gridx = 1;
		gbc_cbx_ip.gridy = 0;
		contentPane.add(cbx_ip, gbc_cbx_ip);

		JLabel lblPorta = new JLabel("PORTA:");
		lblPorta.setFont(new Font("Arial", Font.BOLD, 12));
		lblPorta.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 0;
		contentPane.add(lblPorta, gbc_lblPorta);

		txt_porta = new JTextField();
		txt_porta.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_txt_porta = new GridBagConstraints();
		gbc_txt_porta.fill = GridBagConstraints.BOTH;
		gbc_txt_porta.insets = new Insets(0, 0, 5, 5);
		gbc_txt_porta.gridx = 3;
		gbc_txt_porta.gridy = 0;
		contentPane.add(txt_porta, gbc_txt_porta);
		txt_porta.setColumns(10);

		bt_IniciaServidor = new JButton("Iniciar Servidor");
		bt_IniciaServidor.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_IniciaServidor = new GridBagConstraints();
		gbc_bt_IniciaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_bt_IniciaServidor.gridx = 4;
		gbc_bt_IniciaServidor.gridy = 0;
		contentPane.add(bt_IniciaServidor, gbc_bt_IniciaServidor);

		bt_EncerraServidor = new JButton("Encerrar Servidor");
		bt_EncerraServidor.setFont(new Font("Arial", Font.PLAIN, 12));
		GridBagConstraints gbc_bt_EncerraServidor = new GridBagConstraints();
		gbc_bt_EncerraServidor.insets = new Insets(0, 0, 5, 0);
		gbc_bt_EncerraServidor.gridx = 5;
		gbc_bt_EncerraServidor.gridy = 0;
		contentPane.add(bt_EncerraServidor, gbc_bt_EncerraServidor);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		txtA_msg = new JTextArea();
		txtA_msg.setForeground(new Color(0, 0, 0));
		txtA_msg.setBackground(Color.LIGHT_GRAY);
		txtA_msg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(txtA_msg);

		configurar();
	}

	// =========================
	// instâncias de variavéis
	// =========================

	/** Formata data */
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	/** Contém todos cliente conectados no servidor */
	private Map<String, Cliente> mapaClientes = new HashMap<>();

	/** Contém todos os nomes dos arquivos diponibilizados pelos usuários */
	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<>();

	/** Referência a esse próprio objeto depois de exportar */
	private IServer servidor;

	/**
	 * Referência onde o objeto exporta será buscado pelo nom, é o registro que
	 * escuta na porta TCP/IP
	 */
	private Registry registry;

	// ==========================
	// Métodos
	// ==========================

	private void configurar() {
		// carrega dados no cbx_IP
		carregaIp();

		// deixa botão ecerrar servidor desabilitado
		bt_EncerraServidor.setEnabled(false);

		// se fechar a tela pelo Close, ela irá fechar a conexão
		// e derrubar todos que estão conectados ao mesmo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				fecharTodosClientes();
			}
		});

		// inicia servico
		bt_IniciaServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServico();
			}
		});

		// encerra servico
		bt_EncerraServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarServico();
			}
		});

	}

	/** Busca todos os endereços IP, presente na máquina */
	private void carregaIp() {
		try {
			for (String res : new LerIp().returnListIp()) {
				cbx_ip.addItem(res);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void iniciarServico() {
		String strPorta = txt_porta.getText().trim();

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
			servidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(IServer.NOME_SERVICO, servidor);

			mostrar("Serviço iniciado");

			cbx_ip.setEnabled(false);
			txt_porta.setEnabled(false);
			bt_IniciaServidor.setEnabled(false);

			bt_EncerraServidor.setEnabled(true);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}
	}

	protected void encerrarServico() {
		mostrar("SERVIDOR PARANDO O SERVIÇO");

		fecharTodosClientes();

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);

			cbx_ip.setEnabled(true);
			txt_porta.setEnabled(true);
			bt_IniciaServidor.setEnabled(true);

			bt_EncerraServidor.setEnabled(false);

			mostrar("SERVIDOR ENCERRADO.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void fecharTodosClientes() {
		mostrar("ENCERRANDO CONEXÃO DE TODOS CLIENTES NO SERVIDOR");
	}

	private void mostrar(String string) {
		txtA_msg.append(sdf.format(new Date()));
		txtA_msg.append(" -> ");
		txtA_msg.append(string);
		txtA_msg.append("\n");
	}

	// ===============================
	// Implemetação dos métodos da Interface IServer

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar(c.getNome() + ", com ip:" + c.getIp() + " se conectou.");
		mapaClientes.put(c.getIp(), c);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		mostrar("Foi pesquisado o \"Arq\" ->" + nome);

		Map<Cliente, List<Arquivo>> resultMapArq = new HashMap<>();
		for (Map.Entry<Cliente, List<Arquivo>> entry : mapaArquivos.entrySet()) {
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
		File file = new File(".\\Share\\Dowload\\" + arq.getNome());
		byte[] dados = new LeituraEscritaDeArquivos().leia(file);
		mostrar("Dados" + dados);
		return dados;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapaClientes.remove(c);
		mapaArquivos.remove(c);
		mostrar("Cliente: " + c.getNome().toUpperCase() + " desconectado!");
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			mostrar("Cliente:" + c.getNome() + "/ Publico arq: " + arquivo.getNome() + " : " + arquivo.getTamanho());
		}
		mapaArquivos.put(c, lista);
	}
}
