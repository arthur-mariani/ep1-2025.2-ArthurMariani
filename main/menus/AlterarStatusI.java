package main.menus;

import java.io.*;
import java.util.*;

public class AlterarStatusI {

    private static final String CAMINHO_INTERNACOES = "main/dados/internacoes.csv";

    public void alterarStatusInternacao() {
        Scanner sc = new Scanner(System.in);

        try {
            List<String[]> internacoes = lerInternacoes();

            if (internacoes.isEmpty()) {
                System.out.println("Nenhuma internação encontrada.");
                return;
            }

            System.out.println("\n===== LISTA DE INTERNAÇÕES =====");
            for (int i = 1; i < internacoes.size(); i++) {
                String[] linha = internacoes.get(i);
                if (linha.length >= 8) {
                    System.out.printf("Código: %s | Paciente: %s | Médico: %s | Data: %s | Quarto: %s | Custo: %s | Plano: %s | Status: %s%n",
                            linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6], linha[7]);
                } else {
                    System.out.printf("Código: %s | Paciente: %s | Médico: %s | Data: %s | Quarto: %s | Custo: %s | Plano: %s%n",
                            linha[0], linha[1], linha[2], linha[3], linha[4], linha[5], linha[6]);
                }
            }

            System.out.print("\nDigite o código da internação que deseja alterar: ");
            String codigoBusca = sc.nextLine().trim();

            boolean encontrada = false;

            for (int i = 1; i < internacoes.size(); i++) {
                String[] internacao = internacoes.get(i);
                String codigoArquivo = internacao[0].trim();

                if (codigoArquivo.replaceFirst("^0+", "")
                        .equalsIgnoreCase(codigoBusca.replaceFirst("^0+", ""))) {

                    encontrada = true;
                    System.out.println("\nInternação encontrada!");
                    System.out.println("Status atual: " + (internacao.length >= 8 ? internacao[7] : "Agendada"));
                    System.out.println("\nEscolha o novo status:");
                    System.out.println("1 - Cancelada");
                    System.out.println("2 - Paciente Liberado");
                    System.out.print("Opção: ");

                    int opcao = sc.nextInt();
                    sc.nextLine();

                    String novoStatus = null;
                    switch (opcao) {
                        case 1:
                            novoStatus = "Internação Cancelada";
                            break;
                        case 2:
                            novoStatus = "Paciente Liberado";
                            break;
                        default:
                            novoStatus = null;
                            break;
                    }

                    if (novoStatus == null) {
                        System.out.println("Opção inválida. Nenhuma alteração feita.");
                        return;
                    }

                    if (internacao.length < 8) {
                        internacao = Arrays.copyOf(internacao, 8);
                        internacao[7] = novoStatus;
                    } else {
                        internacao[7] = novoStatus;
                    }

                    internacoes.set(i, internacao);
                    System.out.println("\nStatus atualizado para: " + novoStatus);
                    break;
                }
            }

            if (!encontrada) {
                System.out.println("Internação não encontrada.");
                return;
            }

            salvarInternacoes(internacoes);
            System.out.println("\nAlterações salvas com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao alterar o status: " + e.getMessage());
        }
    }

    // MÉTODOS AUXILIARES

    private List<String[]> lerInternacoes() throws IOException {
        List<String[]> linhas = new ArrayList<>();
        File arquivo = new File(CAMINHO_INTERNACOES);

        if (!arquivo.exists()) {
            System.out.println("Arquivo de internações não encontrado.");
            return linhas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.isEmpty()) {
                    String[] dados = linha.split(",");
                    for (int i = 0; i < dados.length; i++) {
                        dados[i] = dados[i].trim();
                    }
                    linhas.add(dados);
                }
            }
        }
        return linhas;
    }

    private void salvarInternacoes(List<String[]> internacoes) throws IOException {
        try (FileWriter fw = new FileWriter(CAMINHO_INTERNACOES, false)) {
            for (String[] i : internacoes) {
                fw.write(String.join(",", i) + "\n");
            }
        }
    }
}
