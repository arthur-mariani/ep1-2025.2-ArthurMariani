package main.menus;

import java.io.*;
import java.util.*;

public class AgendamentoInternacoes extends Agendamento {

    private static final String CAMINHO_PACIENTES = "main/dados/pacientes.csv";
    private static final String CAMINHO_INTERNACOES = "main/dados/internacoes.csv";

    private int codigoInternacao;
    private int quarto;
    private String dataEntrada;
    private int diasInternado;
    private double custoFinal;

    private static final double PRECO_DIA = 800.0;

    public void registrarInternacao() {
        super.criarAgendamento();

        if (getPaciente() == null || getMedico() == null) {
            System.out.println("Não foi possível continuar o agendamento da internação.");
            return;
        }

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("\nDigite a data de entrada (ex: 05/10/2025): ");
            dataEntrada = sc.nextLine().trim();

            System.out.print("Digite o número de dias de internação: ");
            diasInternado = sc.nextInt();
            sc.nextLine();

            codigoInternacao = gerarProximoCodigo();
            quarto = gerarProximoQuarto();

            if (verificarQuartoOcupado(quarto)) {
                System.out.println("Quarto " + quarto + " já está ocupado. Escolha outro.");
                return;
            }

            String planoSaude = buscarPlanoPorCPF(getCPF());

            custoFinal = calcularCustoComDesconto(planoSaude, diasInternado);

            salvarInternacao(codigoInternacao, getPaciente(), getMedico(), dataEntrada, quarto, custoFinal, planoSaude);

            mostrarResumo(planoSaude);

        } catch (Exception e) {
            System.out.println("Erro ao registrar internação: " + e.getMessage());
        }
    }

    // MÉTODOS AUXILIARES

    private String buscarPlanoPorCPF(String cpfBusca) throws IOException {
        for (String[] linha : lerCSV(CAMINHO_PACIENTES)) {
            if (linha.length >= 4 && linha[1].trim().equals(cpfBusca)) {
                return linha[3].trim();
            }
        }
        return "Nenhum";
    }

    private double calcularCustoComDesconto(String plano, int dias) {
        double custoBase = dias * PRECO_DIA;

        switch (plano) {
            case "Idoso":
                return custoBase * 0.65;
            case "Servidor Publico":
                return custoBase * 0.75;
            case "Aluno":
                return custoBase * 0.90;
            case "Especial":
                if (dias < 7) return 0;
                else return custoBase;
            default:
                return custoBase;
        }
    }

    private boolean verificarQuartoOcupado(int quartoBusca) throws IOException {
        for (String[] linha : lerCSV(CAMINHO_INTERNACOES)) {
            if (linha.length >= 5) {
                int quartoExistente = Integer.parseInt(linha[4].trim());
                if (quartoExistente == quartoBusca) {
                    return true;
                }
            }
        }
        return false;
    }

    private int gerarProximoCodigo() throws IOException {
        int max = 999;
        for (String[] linha : lerCSV(CAMINHO_INTERNACOES)) {
            try {
                int cod = Integer.parseInt(linha[0].trim());
                if (cod > max) max = cod;
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    private int gerarProximoQuarto() throws IOException {
        int max = 499;
        for (String[] linha : lerCSV(CAMINHO_INTERNACOES)) {
            try {
                int quartoAtual = Integer.parseInt(linha[4].trim());
                if (quartoAtual > max) max = quartoAtual;
            } catch (NumberFormatException ignored) {}
        }
        int proximo = max + 1;
        if (proximo > 520) {
            System.out.println("Todos os quartos estão ocupados (500–520).");
            return -1;
        }
        return proximo;
    }

    private void salvarInternacao(int codigo, String paciente, String medico, String dataEntrada,
                                  int quarto, double custo, String plano) throws IOException {

        File arquivo = new File(CAMINHO_INTERNACOES);
        boolean arquivoExiste = arquivo.exists();
        boolean precisaCabecalho = !arquivoExiste || arquivo.length() == 0;

        try (FileWriter fw = new FileWriter(arquivo, true)) {
            if (precisaCabecalho) {
                fw.write("Codigo,Paciente,Medico,DataEntrada,Quarto,Custo,Plano,Status\n");
            }

            fw.write(codigo + "," + paciente + "," + medico + "," + dataEntrada + "," +
                    quarto + "," + custo + "," + plano + "," + "Internação em andamento" + "\n");
        }
    }

    private List<String[]> lerCSV(String caminho) throws IOException {
        List<String[]> linhas = new ArrayList<>();
        File arquivo = new File(caminho);
        if (!arquivo.exists()) return linhas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.startsWith("Codigo") && !linha.isEmpty()) {
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

    private void mostrarResumo(String plano) {
        System.out.println("\nInternação registrada com sucesso!");
        System.out.println("Código da internação: " + codigoInternacao);
        System.out.println("Paciente: " + getPaciente() + " (CPF: " + getCPF() + ")");
        System.out.println("Médico responsável: " + getMedico() + " (CRM: " + getCRM() + ")");
        System.out.println("Data de entrada: " + dataEntrada);
        System.out.println("Quarto: " + quarto);
        System.out.println("Plano de saúde: " + plano);
        System.out.printf("Custo total: R$ %.2f%n", custoFinal);
        System.out.println("Status: Internação em andamento");
    }
}
