package br.menu.servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.channels.NetworkChannel;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class MenuServer extends JFrame {

	private JPanel contentPane;
	private JTextField txt_porta;
	private JComboBox cbx_ip;
	private JButton bt_IniciaServidor;
	private JButton bt_EncerraServidor;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MenuServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 304);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		
		cbx_ip = new JComboBox();
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
		
		JTextPane txtPane_msg = new JTextPane();
		txtPane_msg.setForeground(new Color(0, 0, 0));
		txtPane_msg.setBackground(Color.LIGHT_GRAY);
		txtPane_msg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(txtPane_msg);
		
		configurar();
	}
	
	/**
	 * Métodos
	 */

	
	private void configurar() {
		
		carregaIp();
		
		//inicia servidor
		bt_IniciaServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServidor();
			}
		});
		
		//encerra servidor
		bt_EncerraServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarServidor();
			}
		});
		
	}

	/**
	 * Busca todos os endereços IP, presente na máquina
	 */
	private void carregaIp() {
		try {
			Enumeration<NetworkInterface> netIfc = NetworkInterface.getNetworkInterfaces();
			
			while(netIfc.hasMoreElements()){
				NetworkInterface ifc =  netIfc.nextElement();
				if(ifc.isUp()){
					Enumeration<InetAddress> addresses = ifc.getInetAddresses();
					while(addresses.hasMoreElements()){
						InetAddress addr = addresses.nextElement();
						
						String  ip = addr.getHostAddress();
						
						if(ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")){
							cbx_ip.addItem(ip);
						}
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	protected void encerrarServidor() {
		// TODO Auto-generated method stub
		
	}

	protected void iniciarServidor() {
		// TODO Auto-generated method stub
		
	}

}
