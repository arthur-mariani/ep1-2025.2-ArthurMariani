package main.menus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import main.entities.Medicamento;

public class CadastrarMedicamentos {
    private List<Medicamento> medicamentos;
    private final String pasta = "main/dados";
    private final String arquivoCSV = pasta + "/medicamentos.csv";

    public CadastrarMedicamentos() {
        medicamentos = new ArrayList<>();
    }

    public void cadastrarMedicamentos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- CADASTRO DE MEDICAMENTOS ----\n");

        System.out.println("Digite o nome do medicamento:");
        String nome = scanner.nextLine().trim();

        int quantia = 0;
        while (true) {
            try {
                System.out.println("Digite a quantia em estoque:");
                quantia = Integer.parseInt(scanner.nextLine());
                if (quantia >= 0) break;
                System.out.println("A quantia não pode ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números.");
            }
        }

        String tarja = "";
        while (true) {
            System.out.println("Digite a tarja do medicamento (Vermelha, Preta, Amarela, Sem Tarja):");
            tarja = scanner.nextLine().trim();
            if (tarja.equalsIgnoreCase("Vermelha") ||
                tarja.equalsIgnoreCase("Preta") ||
                tarja.equalsIgnoreCase("Amarela") ||
                tarja.equalsIgnoreCase("Sem Tarja")) {
                break;
            } else {
                System.out.println("Tarja inválida. Tente novamente.");
            }
        }

        double preco = 0.0;
        while (true) {
            try {
                System.out.println("Digite o preço do medicamento (use ponto para decimais):");
                preco = Double.parseDouble(scanner.nextLine());
                if (preco >= 0) break;
                System.out.println("O preço não pode ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números (ex: 25.50).");
            }
        }

        String necessitaReceita = "";
        while (true) {
            System.out.println("O medicamento necessita de receita? (S/N):");
            necessitaReceita = scanner.nextLine().trim().toUpperCase();
            if (necessitaReceita.equals("S") || necessitaReceita.equals("N")) {
                break;
            } else {
                System.out.println("Resposta inválida! Digite apenas 'S' ou 'N'.");
            }
        }

        Medicamento medicamento = new Medicamento(nome, quantia, tarja, preco, necessitaReceita);
        medicamentos.add(medicamento);
        salvarMedicamentoCSV(medicamento);

        System.out.println("Medicamento cadastrado e salvo no CSV com sucesso!");
    }

    private void salvarMedicamentoCSV(Medicamento medicamento) {
        boolean arquivoExiste = new File(arquivoCSV).exists();

        try (FileWriter writer = new FileWriter(arquivoCSV, true)) {
            if (!arquivoExiste) {
                writer.append("Nome,Quantia,Tarja,Preço,Necessita Receita\n");
            }

            writer.append(medicamento.getNome()).append(",");
            writer.append(String.valueOf(medicamento.getQuantia())).append(",");
            writer.append(medicamento.getTarja()).append(",");
            writer.append(String.valueOf(medicamento.getPreco())).append(",");
            writer.append(medicamento.getNecessitaReceita()).append("\n");

        } catch (IOException e) {
            System.out.println("Erro ao salvar no CSV: " + e.getMessage());
        }
    }
}