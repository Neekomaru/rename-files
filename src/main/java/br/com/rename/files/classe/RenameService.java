package br.com.rename.files.classe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;

public class RenameService {

	

	public void renameFiles(String caminho) throws IOException {
            final File folder = new File(caminho);
		listarArquivos(folder, caminho);
	}

	public void listarArquivos(final File folder, String caminho) throws IOException {
		int cont = 1;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listarArquivos(fileEntry,caminho);
			} else {

				String nomeArquivo = fileEntry.getName();
				String extensao = getExtension(nomeArquivo);
				String stringGerada = gerarNomeFile();
				String novoNome = cont + "-" + stringGerada + "." + extensao;
				System.out.println("Nome Antigo: " + nomeArquivo);
				System.out.println("Novo Nome: " + novoNome);
				Path yourFile = Paths.get(caminho + nomeArquivo);
				Files.move(yourFile, yourFile.resolveSibling(novoNome));
			}
			cont++;
		}
	}

	public String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	public String gerarNomeFile() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ123456789";

		Random random = new Random();

		String stringGerada = "";
		int index = -1;
		for (int i = 0; i < 12; i++) {
			index = random.nextInt(letras.length());
			stringGerada += letras.substring(index, index + 1);
		}
		return stringGerada;
	}
}
