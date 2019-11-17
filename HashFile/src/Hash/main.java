package Hash;
import java.io.*;
import java.security.*;
import java.net.*;

public class main {

	static String makeHash(File fileName) {
		String saida= "Erro de catch";
		try {
			final MessageDigest sha = MessageDigest.getInstance("SHA-256");
			FileInputStream inputS= new FileInputStream(fileName);
			byte[] bArray = new byte[1024];
			int bytesCount = 0;
			while((bytesCount = inputS.read(bArray))!= (-1)) {
				sha.update(bArray, 0, bytesCount);				
			}
			inputS.close();
			byte[] bytes = sha.digest();
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i<bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			saida = sb.toString();
			return saida;
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println("No such Algorithm in MessageDigest");
			}
		catch(FileNotFoundException d) {
			System.out.println("Oi, o arquivo nao existe, valeu?");
		}
		catch(IOException f) {
			System.out.println("A entrada nao foi validaa");
		}
		return saida;
		
		
	}

	public static void main(String[] args) {
		if(args.length<3) {
			for(int i =0; i<args.length;i++) {
				System.out.println(args[i]);
			}
			String file = args[args.length-1];
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL url = classLoader.getResource(file);
			File newF = new File(url.getPath());
			String imprime = makeHash(newF);
			System.out.println(imprime);
		}
		else {
			for(int i =0; i<args.length;i++) {
				System.out.println(args[i]);
			}
			String hash = args[args.length-1];
			String file = args[args.length-2];
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL ul = classLoader.getResource(file);
			File newF = new File(ul.getPath());
			String compara = makeHash(newF);
			
			if(compara.equals(hash)) {
				System.out.println("Arquivo manteve integridade");
			}
			else {
				System.out.println("Arquivo foi corrompido ou não é o mesmo");
			}
		
		}

	}
	

}
