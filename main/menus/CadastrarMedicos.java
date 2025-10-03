package main.menus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.entities.Medicos;

public class CadastrarMedicos {
    private List<Medicos> medicos;
    private final String pasta = "main/dados";
    private final String arquivoCSV = pasta + "/medicos.csv";

    private static final String[] ESPECIALIDADES = {
        "Acupuntura",
        "Alergia e imunologia",
        "Anestesiologia",
        "Angiologia",
        "Cardiologia",
        "Cirurgia cardiovascular",
        "Cirurgia da mão",
        "Cirurgia de cabeça e pescoço",
        "Cirurgia do aparelho digestivo",
        "Cirurgia geral",
        "Cirurgia oncológica",
        "Cirurgia pediátrica",
        "Cirurgia plástica",
        "Cirurgia torácica",
        "Cirurgia vascular",
        "Clínica médica",
        "Coloproctologia",
        "Dermatologia",
        "Endocrinologia e metabologia",
        "Endoscopia",
        "Gastroenterologia",
        "Genética médica",
        "Geriatria",
        "Ginecologia e obstetrícia",
        "Hematologia e hemoterapia",
        "Homeopatia",
        "Infectologia",
        "Mastologia",
        "Medicina de emergência",
        "Medicina de família e comunidade",
        "Medicina do trabalho",
        "Medicina do tráfego",
        "Medicina esportiva",
        "Medicina física e reabilitação",
        "Medicina intensiva",
        "Medicina legal e perícia médica",
        "Medicina nuclear",
        "Medicina preventiva e social",
        "Nefrologia",
        "Neurocirurgia",
        "Neurologia",
        "Nutrologia",
        "Oftalmologia",
        "Oncologia clínica",
        "Ortopedia e traumatologia",
        "Otorrinolaringologia",
        "Patologia",
        "Patologia clínica/medicina laboratorial",
        "Pediatria",
        "Pneumologia",
        "Psiquiatria",
        "Radiologia e diagnóstico por imagem",
        "Radioterapia",
        "Reumatologia",
        "Urologia"
    };

    public CadastrarMedicos() {
        medicos = new ArrayList<>();
    }

    public void cadastrarMedico() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- CADASTRO DE MÉDICOS ----\n");

        System.out.println("Digite o nome do médico:");
        String nome = scanner.nextLine();

        String crm = "";
        while (true) {
            System.out.println("Digite o CRM do médico (mínimo 5 dígitos):");
            crm = scanner.nextLine().trim();
            if (crm.matches("\\d{5,}")) {
                break;
            } else {
                System.out.println("CRM inválido! Digite ao menos 5 números.");
            }
        }

        System.out.println("Escolha a especialidade do médico:");
        for (int i = 0; i < ESPECIALIDADES.length; i++) {
            System.out.println((i + 1) + " - " + ESPECIALIDADES[i]);
        }

        int escolha = -1;
        while (true) {
            try {
                System.out.print("Digite o número correspondente: ");
                escolha = Integer.parseInt(scanner.nextLine());
                if (escolha >= 1 && escolha <= ESPECIALIDADES.length) {
                    break;
                } else {
                    System.out.println("Opção inválida. Digite um número entre 1 e " + ESPECIALIDADES.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
            }
        }
        String especialidade = ESPECIALIDADES[escolha - 1];

        double custoConsulta = 0.0;
        while (true) {
            try {
                System.out.println("Digite o custo da consulta (em R$):");
                custoConsulta = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (custoConsulta >= 0) break;
                else System.out.println("O valor não pode ser negativo!");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número.");
            }
        }

        List<String> agendaHorarios = new ArrayList<>();
        System.out.println("Deseja adicionar horários disponíveis à agenda? (S/N)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Digite os horários (ex: 'Segunda 14h', 'Terça 9h'). Digite 'fim' para encerrar:");
            while (true) {
                String horario = scanner.nextLine();
                if (horario.equalsIgnoreCase("fim")) break;
                agendaHorarios.add(horario);
            }
        }

        Medicos medico = new Medicos(nome, crm, especialidade, custoConsulta);
        for (String h : agendaHorarios) medico.adicionarHorario(h);

        medicos.add(medico);
        salvarMedicoCSV(medico);

        System.out.println("Médico cadastrado e salvo no CSV com sucesso!");
    }

    private void salvarMedicoCSV(Medicos medico) {
        boolean arquivoExiste = new File(arquivoCSV).exists();

        try (FileWriter writer = new FileWriter(arquivoCSV, true)) {
            if (!arquivoExiste) {
                writer.append("Nome,CRM,Especialidade,Custo da Consulta,Agenda de Horários\n");
            }
            writer.append(medico.getNome()).append(",");
            writer.append(medico.getCrm()).append(",");
            writer.append(medico.getEspecialidade()).append(",");
            writer.append(String.valueOf(medico.getCustoConsulta())).append(",");
            writer.append(String.join(" | ", medico.getAgendaHorarios())).append("\n");

        } catch (IOException e) {
            System.out.println("Erro ao salvar no CSV: " + e.getMessage());
        }
    }

}
