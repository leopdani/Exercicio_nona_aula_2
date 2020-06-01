package ex_2.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Log {
	private PrintWriter arquivo;

	public void abrir(String nome) throws IOException {
		
		File outFile = new File("C:\\Users\\leopd\\Documents\\Exercicio_nona_aula_2\\log"+ nome);
		FileOutputStream outFileStream;

		System.out.println("Procure o arquivo em " + outFile.getAbsolutePath());
		if (outFile.exists()) {
			outFileStream = new FileOutputStream(outFile, true);
		} else {
			outFileStream = new FileOutputStream(outFile);
		}
		arquivo = new PrintWriter(outFileStream);
	}

	public void escrever(String texto) throws IOException {
		synchronized (arquivo) {
			arquivo.println(texto + " " + LocalDateTime.now());
			arquivo.flush();
		}
	}

	public void fechar() throws IOException {
		synchronized (arquivo) {
			arquivo.close();
		}
	}
}