package br.dagostini.exemplos;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;

public class LerIp {

	public LerIp() {


	}
	
	
	public String retornarIP(){
		InetAddress IP;		
		try {
			IP = InetAddress.getLocalHost();
			String IPString = IP.getHostAddress();
			
			if (IPString.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
				return IPString;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** Busca todos os endereços IP, presente na máquina */
	public List<String> returnListIp() {
		List<String> listaIp = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> netIfc = NetworkInterface.getNetworkInterfaces();
			while (netIfc.hasMoreElements()) {
				NetworkInterface ifc = netIfc.nextElement();
				if (ifc.isUp()) {
					Enumeration<InetAddress> addresses = ifc.getInetAddresses();
					while (addresses.hasMoreElements()) {
						InetAddress addr = addresses.nextElement();

						String ip = addr.getHostAddress();

						if (ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
							listaIp.add(ip.trim());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaIp;
	}
	

	public static void main(String[] args) {
		System.out.println(new LerIp().retornarIP());
	}
}
