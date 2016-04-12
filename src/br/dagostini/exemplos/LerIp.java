package br.dagostini.exemplos;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

	public static void main(String[] args) {
		System.out.println(new LerIp().retornarIP());
	}
}
