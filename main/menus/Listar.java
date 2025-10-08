package main.menus;

import java.io.*;

public class Listar {

    protected String caminhoArquivo;
    protected String cabecalho;

    public void listar() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            System.out.println("--------------------------------------------------");
            System.out.println(cabecalho);
            System.out.println("--------------------------------------------------");

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(";");
                for (String dado : dados) {
                    System.out.print(dado + "\t");
                }
                System.out.println();
            }

            System.out.println("--------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
