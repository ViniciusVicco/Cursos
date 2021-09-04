package Application;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {

	public static void print(String text) {
		System.out.println(text);
	}

	public static void main(String[] args) {
		print(DigestUtils.sha256Hex("123456"));
	}

	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);

	}

	public static void addMessage(String message, Severity severity, String clientId) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severity, message, null));
	}

	public static void addInfoMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_INFO, null);
	}

	public static void addFatalMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_FATAL, null);
	}

	public static void addWarnMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_WARN, null);
	}

	public static void addErrorMessage(String message) {
		addMessage(message, FacesMessage.SEVERITY_ERROR, null);
	}
}