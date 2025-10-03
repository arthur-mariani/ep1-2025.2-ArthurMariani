package main.menus;

import java.io.*;
import java.util.*;

public class AlterarPacientes {
    private final String pasta = "main/dados";
    private final String arquivoCSV = pasta + "/pacientes.csv";

    public void alterarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- ALTERAÇÃO DE PACIENTE ----\n");

        System.out.print("Digite o CPF do paciente que deseja alterar: ");
        String cpfAntigo = scanner.nextLine().trim();

        File arquivo = new File(arquivoCSV);
        if (!arquivo.exists()) {
            System.out.println("Nenhum paciente cadastrado ainda.");
            return;
        }

        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome")) {
                    linhas.add(linha);
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length > 1 && dados[1].trim().equals(cpfAntigo)) {
                    encontrado = true;
                    continue; 
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (!encontrado) {
            System.out.println("Paciente com CPF " + cpfAntigo + " não encontrado.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, false))) {
            for (String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar alterações: " + e.getMessage());
            return;
        }

        System.out.println("\nAgora insira os novos dados do paciente:");
        CadastrarPacientes cadastrar = new CadastrarPacientes();
        cadastrar.cadastrarPaciente();

        System.out.println("Paciente alterado com sucesso!");
    }
}
