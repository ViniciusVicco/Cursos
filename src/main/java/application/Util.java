package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import servlet.ImgUsuarioServlet;

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

	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean saveImageUsuario(InputStream inputStream, String imageType, int idUsuario) {
		// Exemplo da maquina do janio: /home/janio/images/usuario
		String diretorio = System.getProperty("user.home") + ImgUsuarioServlet.PATH_IMAGES_USUARIO;

		// Criando os diretorios caso nao exista
		File file = new File(diretorio);
		if (!file.exists()) {
			file.mkdirs(); // mkdirs - cria arquivo ou diretorio (caso o diretorio anterior nao exista ele
							// cria tambem)
		}

		try {
			// criando o espaco de memoria para armazenamento de uma imagem
			// inputStream - eh o fluxo de dados de entrada
			BufferedImage bImage = ImageIO.read(inputStream);

			// estrutura final do arquivo ex: /home/janio/images/usuario/01.png
			File arquivoFinal = new File(diretorio + File.separator + idUsuario + "." + imageType);
			// salvando a imagem
			// buffer da imagem, png/gif, 01.png
			ImageIO.write(bImage, imageType, arquivoFinal);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean salvaCapaCurso(InputStream inputStream, String imageType, int idUsuario) {
		// Exemplo da maquina do janio: /home/janio/images/usuario
		String diretorio = "C:\\Users\\vinic\\Pictures\\curso\\";

		// Criando os diretorios caso nao exista
		File file = new File(diretorio);
		if (!file.exists()) {
			file.mkdirs(); // mkdirs - cria arquivo ou diretorio (caso o diretorio anterior nao exista ele
							// cria tambem)
		}

		try {
			// criando o espaco de memoria para armazenamento de uma imagem
			// inputStream - eh o fluxo de dados de entrada
			BufferedImage bImage = ImageIO.read(inputStream);

			// estrutura final do arquivo ex: /home/janio/images/usuario/01.png
			File arquivoFinal = new File(diretorio + File.separator + idUsuario + "." + imageType);
			// salvando a imagem
			// buffer da imagem, png/gif, 01.png
			ImageIO.write(bImage, imageType, arquivoFinal);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
}