package main.menus;

import java.io.*;
import java.util.*;

public class AgendamentoConsultas extends Agendamento {

    private static final String CAMINHO_PACIENTES = "main/dados/pacientes.csv";
    private static final String CAMINHO_MEDICOS = "main/dados/medicos.csv";
    private static final String CAMINHO_CONSULTAS = "main/dados/consultas.csv";

    private int codigoConsulta;
    private int sala;
    private String dataHora;
    private String status;
    private double valorConsulta;

    // MÉTODO PRINCIPAL
    public void agendarConsulta() {
        super.criarAgendamento();

        if (getPaciente() == null || getMedico() == null) {
            System.out.println("Não foi possível continuar o agendamento.");
            return;
        }

        try {
            String planoSaude = buscarPlanoPorCPF(getCPF());
            String[] dadosMedico = buscarMedicoCompleto(getCRM());

            if (dadosMedico == null) {
                System.out.println("Médico não encontrado.");
                return;
            }

            double precoBase = Double.parseDouble(dadosMedico[0]);
            String[] horariosDisponiveis = dadosMedico[1].split("\\|");

            mostrarHorariosDisponiveis(horariosDisponiveis);
            dataHora = escolherHorario(horariosDisponiveis);

            if (verificarConflito(getMedico(), dataHora)) {
                System.out.println("Conflito: horário já ocupado.");
                return;
            }

            valorConsulta = aplicarDesconto(planoSaude, precoBase);
            codigoConsulta = gerarProximoCodigo();
            sala = gerarProximaSala();

            status = "Agendada";
            salvarConsulta(codigoConsulta, getPaciente(), getMedico(), dataHora, sala, status, valorConsulta);

            mostrarResumo();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // MÉTODOS AUXILIARES
    private void mostrarHorariosDisponiveis(String[] horarios) {
        System.out.println("\nHorários disponíveis:");
        for (int i = 0; i < horarios.length; i++) {
            System.out.println((i + 1) + " - " + horarios[i].trim());
        }
    }

    private String escolherHorario(String[] horarios) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEscolha o número do horário desejado: ");
        int opcao = sc.nextInt();
        return horarios[opcao - 1].trim();
    }

    private String buscarPlanoPorCPF(String cpfBusca) throws IOException {
        for (String[] linha : lerCSV(CAMINHO_PACIENTES)) {
            if (linha[1].trim().equals(cpfBusca)) {
                return linha[3].trim();
            }
        }
        return "Nenhum";
    }

    private String[] buscarMedicoCompleto(String crmBusca) throws IOException {
        for (String[] linha : lerCSV(CAMINHO_MEDICOS)) {
            if (linha[1].trim().equals(crmBusca)) {
                return new String[]{linha[3].trim(), linha[4].trim()};
            }
        }
        return null;
    }

    private boolean verificarConflito(String medico, String horario) throws IOException {
        for (String[] linha : lerCSV(CAMINHO_CONSULTAS)) {
            if (linha.length >= 5 && linha[2].equals(medico) && linha[3].equals(horario)) {
                return true;
            }
        }
        return false;
    }

    private double aplicarDesconto(String plano, double preco) {
        switch (plano) {
            case "Idoso": return preco * 0.65;
            case "Servidor": return preco * 0.75;
            case "Aluno": return preco * 0.90;
            default: return preco;
        }
    }

    private void salvarConsulta(int codigo, String paciente, String medico, String dataHora, int sala, String status, double valor) throws IOException {
        try (FileWriter fw = new FileWriter(CAMINHO_CONSULTAS, true)) {
            fw.write(codigo + "," + paciente + "," + medico + "," + dataHora + "," + sala + "," + status + "," + valor + "\n");
        }
    }

    private List<String[]> lerCSV(String caminho) throws IOException {
        List<String[]> linhas = new ArrayList<>();
        File arquivo = new File(caminho);
        if (!arquivo.exists()) return linhas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.startsWith("Nome") && !linha.isEmpty()) {
                    linhas.add(linha.split(","));
                }
            }
        }
        return linhas;
    }

    private int gerarProximoCodigo() throws IOException {
        int max = 0;
        for (String[] linha : lerCSV(CAMINHO_CONSULTAS)) {
            try {
                int cod = Integer.parseInt(linha[0]);
                if (cod > max) max = cod;
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    private int gerarProximaSala() throws IOException {
        int max = 99;
        for (String[] linha : lerCSV(CAMINHO_CONSULTAS)) {
            try {
                int salaAtual = Integer.parseInt(linha[4]);
                if (salaAtual > max) max = salaAtual;
            } catch (NumberFormatException ignored) {}
        }
        return max + 1;
    }

    private void mostrarResumo() {
        System.out.println("\nConsulta agendada com sucesso!");
        System.out.println("Código: " + codigoConsulta);
        System.out.println("Paciente: " + getPaciente() + " (CPF: " + getCPF() + ")");
        System.out.println("Médico: " + getMedico() + " (CRM: " + getCRM() + ")");
        System.out.println("Data/Hora: " + dataHora);
        System.out.println("Sala: " + sala);
        System.out.println("Valor Final: R$ " + valorConsulta);
    }
}
