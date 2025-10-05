package main.menus;

import java.io.*;
import java.util.*;

public class AlterarStatusC {

    private final String caminhoConsultas = "main/dados/consultas.csv";

    public void alterarStatusConsulta() {
        Scanner sc = new Scanner(System.in);

        try {
            List<String[]> consultas = lerConsultas();

            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta encontrada.");
                return;
            }

            System.out.println("\nCONSULTAS DISPONÍVEIS:");
            for (String[] c : consultas) {
                if (c.length >= 7) {
                    System.out.printf("Código: %s | Paciente: %s | Médico: %s | Data/Hora: %s | Status: %s%n",
                            c[0], c[1], c[2], c[3], c[5]);
                }
            }

            System.out.print("\nDigite o código da consulta que deseja alterar: ");
            String codigoBusca = sc.nextLine().trim();

            boolean encontrada = false;
            for (String[] c : consultas) {
                if (c[0].equals(codigoBusca)) {
                    encontrada = true;
                    System.out.println("\nConsulta encontrada!");
                    System.out.println("Status atual: " + c[5]);
                    System.out.println("Escolha o novo status:");
                    System.out.println("1 - Agendada");
                    System.out.println("2 - Cancelada");
                    System.out.println("3 - Concluída");
                    System.out.print("Opção: ");
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1:
                            c[5] = "Agendada";
                            break;
                        case 2:
                            c[5] = "Cancelada";
                            break;
                        case 3:
                            c[5] = "Concluída";
                            break;
                        default:
                            System.out.println("Opção inválida. Nenhuma alteração feita.");
                            return;
                    }
                    System.out.println("\nStatus atualizado para: " + c[5]);
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("Consulta não encontrada.");
                return;
            }

            salvarConsultas(consultas);
            System.out.println("\nAlterações salvas com sucesso no arquivo!");

        } catch (IOException e) {
            System.out.println("Erro ao alterar o status: " + e.getMessage());
        }
    }

    private List<String[]> lerConsultas() throws IOException {
        List<String[]> linhas = new ArrayList<>();
        File arquivo = new File(caminhoConsultas);
        if (!arquivo.exists()) return linhas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isEmpty()) {
                    linhas.add(linha.split(","));
                }
            }
        }
        return linhas;
    }

    private void salvarConsultas(List<String[]> consultas) throws IOException {
        try (FileWriter fw = new FileWriter(caminhoConsultas, false)) {
            for (String[] c : consultas) {
                fw.write(String.join(",", c) + "\n");
            }
        }
    }
}
