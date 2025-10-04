package main.menus;

import java.io.*;
import java.util.Scanner;

public class Agendamento {
    private String paciente;
    private String CPF;
    private String medico;
    private String CRM;

    private final String arquivoPacientes = "main/dados/pacientes.csv";
    private final String arquivoMedicos   = "main/dados/medicos.csv";

    public void criarAgendamento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CPF do paciente: ");
        String cpfBusca = scanner.nextLine().trim();

        this.paciente = buscarPacientePorCPF(cpfBusca);
        this.CPF = cpfBusca;

        if (paciente == null) {
            System.out.println("Paciente com CPF " + cpfBusca + " não encontrado!");
            return;
        }

        System.out.print("Digite o CRM do médico: ");
        String crmBusca = scanner.nextLine().trim();

        this.medico = buscarMedicoPorCRM(crmBusca);
        this.CRM = crmBusca;

        if (medico == null) {
            System.out.println("Médico com CRM " + crmBusca + " não encontrado!");
            return;
        }

        System.out.println("\n=== AGENDAMENTO CRIADO ===");
        System.out.println("Paciente: " + paciente + " (CPF: " + CPF + ")");
        System.out.println("Médico: " + medico + " (CRM: " + CRM + ")");
    }

    private String buscarPacientePorCPF(String cpfBusca) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoPacientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome")) continue;

                String[] dados = linha.split(",");
                if (dados.length > 1 && dados[1].trim().equals(cpfBusca)) {
                    return dados[0].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pacientes.csv: " + e.getMessage());
        }
        return null;
    }

 private String buscarMedicoPorCRM(String crmBusca) {
    try (BufferedReader br = new BufferedReader(new FileReader(arquivoMedicos))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.startsWith("Nome")) continue;

            String[] dados = linha.split(",");
            if (dados.length > 1 && dados[1].trim().equals(crmBusca)) {
                return dados[0].trim(); 
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao ler medicos.csv: " + e.getMessage());
    }
    return null;
}
    public String getPaciente() { return paciente; }
    public String getCPF() { return CPF; }
    public String getMedico() { return medico; }
    public String getCRM() { return CRM; }
}
