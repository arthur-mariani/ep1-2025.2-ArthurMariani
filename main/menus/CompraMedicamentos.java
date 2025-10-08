package main.menus;

import java.io.*;
import java.util.*;

public class CompraMedicamentos {

    private final String caminhoMedicamentos = "main/dados/medicamentos.csv";
    private final String caminhoControle = "main/dados/ControleMedicamentos.csv";

    public void comprarMedicamento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---- COMPRA DE MEDICAMENTOS ----\n");

        System.out.print("Digite o nome da pessoa: ");
        String nomePessoa = sc.nextLine().trim();

        System.out.print("Digite o CPF da pessoa (11 dígitos): ");
        String cpfPessoa = sc.nextLine().trim();

        System.out.print("Digite o nome do medicamento desejado: ");
        String nomeMedicamento = sc.nextLine().trim();

        int quantiaDesejada = 0;
        while (true) {
            try {
                System.out.print("Digite a quantia desejada: ");
                quantiaDesejada = Integer.parseInt(sc.nextLine());
                if (quantiaDesejada > 0) break;
                System.out.println("A quantia deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números válidos.");
            }
        }

        String possuiReceita = "";
        while (true) {
            System.out.print("Possui receita médica? (S/N): ");
            possuiReceita = sc.nextLine().trim().toUpperCase();
            if (possuiReceita.equals("S") || possuiReceita.equals("N")) break;
            System.out.println("Resposta inválida. Digite apenas 'S' ou 'N'.");
        }

        List<String[]> medicamentos = new ArrayList<>();
        boolean encontrado = false;
        boolean compraAprovada = false;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoMedicamentos))) {
            String linha;
            boolean cabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (cabecalho) {
                    medicamentos.add(linha.split(","));
                    cabecalho = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados[0].equalsIgnoreCase(nomeMedicamento)) {
                    encontrado = true;

                    int quantiaAtual = Integer.parseInt(dados[1]);
                    String necessitaReceita = dados[4];

                    if (quantiaAtual < quantiaDesejada) {
                        System.out.println("Estoque insuficiente. Quantia disponível: " + quantiaAtual);
                    } else if (necessitaReceita.equalsIgnoreCase("S") && possuiReceita.equals("N")) {
                        System.out.println("Este medicamento requer receita médica. Compra não autorizada.");
                    } else {
                        int novaQuantia = quantiaAtual - quantiaDesejada;
                        dados[1] = String.valueOf(novaQuantia);
                        compraAprovada = true;
                        System.out.println("Compra realizada com sucesso!");
                    }
                }
                medicamentos.add(dados);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de medicamentos: " + e.getMessage());
            return;
        }

        if (!encontrado) {
            System.out.println("Medicamento não encontrado no sistema.");
            return;
        }

        if (compraAprovada) {
            atualizarEstoque(medicamentos);
            registrarCompra(nomePessoa, cpfPessoa, nomeMedicamento, quantiaDesejada, possuiReceita);
        }
    }

    private void atualizarEstoque(List<String[]> medicamentos) {
        try (FileWriter writer = new FileWriter(caminhoMedicamentos, false)) {
            for (String[] dados : medicamentos) {
                writer.append(String.join(",", dados)).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o estoque: " + e.getMessage());
        }
    }

    private void registrarCompra(String nome, String cpf, String medicamento, int quantia, String possuiReceita) {
        boolean arquivoExiste = new File(caminhoControle).exists();

        try (FileWriter writer = new FileWriter(caminhoControle, true)) {
            if (!arquivoExiste) {
                writer.append("Nome,CPF,Medicamento,Quantia,Possui Receita\n");
            }
            writer.append(nome).append(",");
            writer.append(cpf).append(",");
            writer.append(medicamento).append(",");
            writer.append(String.valueOf(quantia)).append(",");
            writer.append(possuiReceita).append("\n");

        } catch (IOException e) {
            System.out.println("Erro ao registrar compra: " + e.getMessage());
        }
    }
}
