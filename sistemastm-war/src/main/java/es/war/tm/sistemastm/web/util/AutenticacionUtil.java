package es.war.tm.sistemastm.web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class AutenticacionUtil {

	private AutenticacionUtil() {}

	/**
	 * Recupera la IP para poder autenticar mediante SSA
	 * 
	 * @return IP
	 * @throws UnknownHostException 
	 */
	public static String getIPAddress() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		return address.getHostAddress();
	}
}
