package main.menus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.entities.Pacientes;

public class CadastrarPacientes {
    private List<Pacientes> pacientes;
    private final String pasta = "main/dados";
    private final String arquivoCSV = pasta + "/pacientes.csv";

    public CadastrarPacientes() {
        pacientes = new ArrayList<>();
    }

    public void cadastrarPaciente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- CADASTRO DE PACIENTES ----\n");

        System.out.println("Digite o nome do paciente:");
        String nome = scanner.nextLine();

        String cpf = "";
        while (true) {
            System.out.println("Digite o CPF do paciente (11 dígitos):");
            cpf = scanner.nextLine().trim();
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido! Certifique-se de digitar exatamente 11 números.");
            }
        }

        System.out.println("Digite a idade do paciente:");
        int idade = scanner.nextInt();
        scanner.nextLine(); 

        String planoSaude = "";
        while (true) {
            System.out.println("Escolha o plano de saúde: (Idoso, Servidor, Aluno, Especial, Nenhum)");
            planoSaude = scanner.nextLine();
            if (planoSaude.equalsIgnoreCase("Idoso") ||
                planoSaude.equalsIgnoreCase("Servidor") ||
                planoSaude.equalsIgnoreCase("Aluno") ||
                planoSaude.equalsIgnoreCase("Especial") ||
                planoSaude.equalsIgnoreCase("Nenhum")) {
                break;
            } else {
                System.out.println("Plano inválido. Tente novamente.");
            }
        }

        String prioridade = "";
        while (true) {
            System.out.println("O paciente possui prioridade? (S/N)");
            prioridade = scanner.nextLine().trim();
            if (prioridade.equalsIgnoreCase("S") || prioridade.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Resposta inválida. Digite apenas 'S' ou 'N'.");
            }
        }

        List<String> historicoConsultas = new ArrayList<>();
        System.out.println("O paciente possui histórico de consultas? (S/N)");
        String possuiConsultas = scanner.nextLine();
        if (possuiConsultas.equalsIgnoreCase("S")) {
            System.out.println("Digite o histórico de consultas (digite 'fim' para encerrar):");
            while (true) {
                String consulta = scanner.nextLine();
                if (consulta.equalsIgnoreCase("fim")) break;
                historicoConsultas.add(consulta);
            }
        }

        List<String> historicoInternacoes = new ArrayList<>();
        System.out.println("O paciente possui histórico de internações? (S/N)");
        String possuiInternacoes = scanner.nextLine();
        if (possuiInternacoes.equalsIgnoreCase("S")) {
            System.out.println("Digite o histórico de internações (digite 'fim' para encerrar):");
            while (true) {
                String internacao = scanner.nextLine();
                if (internacao.equalsIgnoreCase("fim")) break;
                historicoInternacoes.add(internacao);
            }
        }

        Pacientes paciente = new Pacientes(nome, cpf, idade, planoSaude, prioridade);
        for (String c : historicoConsultas) paciente.adicionarConsulta(c);
        for (String i : historicoInternacoes) paciente.adicionarInternacao(i);

        
        pacientes.add(paciente);
        salvarPacienteCSV(paciente);

        System.out.println("Paciente cadastrado e salvo no CSV com sucesso!");
    }

    private void salvarPacienteCSV(Pacientes paciente) {

        boolean arquivoExiste = new File(arquivoCSV).exists();

        try (FileWriter writer = new FileWriter(arquivoCSV, true)) {
            if (!arquivoExiste) {
                writer.append("Nome, CPF, Idade, Plano de Saude, Prioridade, Historico de Consultas, Historico de Internacoes\n");
            }
            writer.append(paciente.getNome()).append(",");
            writer.append(paciente.getCpf()).append(",");
            writer.append(String.valueOf(paciente.getIdade())).append(",");
            writer.append(paciente.getPlanoSaude()).append(",");
            writer.append(paciente.getPrioridade()).append(",");
            writer.append(String.join(" | ", paciente.getHistoricoConsultas())).append(",");
            writer.append(String.join(" | ", paciente.getHistoricoInternacoes())).append("\n");

        } catch (IOException e) {
            System.out.println("Erro ao salvar no CSV: " + e.getMessage());
        }
    }

    
}
