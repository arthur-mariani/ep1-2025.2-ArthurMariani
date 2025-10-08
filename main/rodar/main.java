package main.rodar;

import java.util.Scanner;

import main.menus.*;
import main.entities.PlanoDeSaude;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcaoPrincipal = 0;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Cadastros");
            System.out.println("2. Agendamentos");
            System.out.println("3. Consultas");
            System.out.println("4. Internações");
            System.out.println("5. Planos de Saúde");
            System.out.println("6. Farmácia");
            System.out.println("7. Relatórios");
            System.out.println("8. Sair");
            System.out.println("==============");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPrincipal = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoPrincipal = -1;
            }

            switch (opcaoPrincipal) {
                case 1:
                    menuCadastros(sc);
                    break;

                case 2:
                    menuAgendamentos(sc);
                    break;

                case 3:
                    menuConsultas(sc);
                    break;

                case 4:
                    menuInternacoes(sc);
                    break;

                case 5:
                    menuPlanos(sc);
                    break;

                case 6:
                    menuFarmacia(sc);
                    break;

                case 7:
                    menuRelatorios(sc);
                    break;

                case 8:
                    System.out.println("\nEncerrando o sistema.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (true);
    }

    // SUBMENUS

    private static void menuCadastros(Scanner sc) {
        int opcaoCadastro = 0;
        do {
            System.out.println("\n-- CADASTROS --");
            System.out.println("1. Cadastro de Pacientes");
            System.out.println("2. Cadastro de Médicos");
            System.out.println("3. Alterar dados de Pacientes");
            System.out.println("4. Alterar dados de Médicos");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoCadastro = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoCadastro = -1;
            }

            switch (opcaoCadastro) {
                case 1:
                    new CadastrarPacientes().cadastrarPaciente();
                    break;
                case 2:
                    new CadastrarMedicos().cadastrarMedico();
                    break;
                case 3:
                    new AlterarPacientes().alterarPaciente();
                    break;
                case 4:
                    new AlterarMedicos().alterarMedico();
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoCadastro != 5);
    }

    private static void menuAgendamentos(Scanner sc) {
        int opcaoAgendamento = 0;
        do {
            System.out.println("\n-- AGENDAMENTOS --");
            System.out.println("1. Agendamento de Consulta");
            System.out.println("2. Agendamento de Internação");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoAgendamento = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoAgendamento = -1;
            }

            switch (opcaoAgendamento) {
                case 1:
                    new AgendamentoConsultas().agendarConsulta();
                    break;
                case 2:
                    new AgendamentoInternacoes().registrarInternacao();
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoAgendamento != 3);
    }

    private static void menuConsultas(Scanner sc) {
        int opcaoConsultas = 0;
        do {
            System.out.println("\n-- CONSULTAS --");
            System.out.println("1. Consultas marcadas");
            System.out.println("2. Alterar Status Consultas");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoConsultas = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoConsultas = -1;
            }

            switch (opcaoConsultas) {
                case 1:
                    new ListarConsultas().listar();
                    break;
                case 2:
                    new AlterarStatusC().alterarStatusConsulta();
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoConsultas != 3);
    }

    private static void menuInternacoes(Scanner sc) {
        int opcaoInternacoes = 0;
        do {
            System.out.println("\n-- INTERNAÇÕES --");
            System.out.println("1. Internações marcadas");
            System.out.println("2. Alterar Status Internações");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoInternacoes = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoInternacoes = -1;
            }

            switch (opcaoInternacoes) {
                case 1:
                    new ListarInternacoes().listar();
                    break;
                case 2:
                    new AlterarStatusI().alterarStatusInternacao();
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoInternacoes != 3);
    }

    private static void menuPlanos(Scanner sc) {
        int opcaoPlanos = 0;
        do {
            System.out.println("\n-- PLANOS DE SAÚDE --");
            System.out.println("1. Planos e Benefícios");
            System.out.println("2. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPlanos = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoPlanos = -1;
            }

            switch (opcaoPlanos) {
                case 1:
                    PlanoDeSaude.listarPlanos();
                    break;
                case 2:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoPlanos != 2);
    }

    private static void menuFarmacia(Scanner sc) {
        int opcaoFarmacia = 0;
        do {
            System.out.println("\n-- FARMÁCIA --");
            System.out.println("1. Listar Medicamentos");
            System.out.println("2. Cadastro de Medicamentos");
            System.out.println("3. Compra de Medicamentos");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoFarmacia = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoFarmacia = -1;
            }

            switch (opcaoFarmacia) {
                case 1:
                    new ListarMedicamentos().listar();
                    break;
                case 2:
                    new CadastrarMedicamentos().cadastrarMedicamentos();
                    break;
                case 3:
                    new CompraMedicamentos().comprarMedicamento();
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoFarmacia != 4);
    }

    private static void menuRelatorios(Scanner sc) {
        int opcaoRelatorios = 0;
        do {
            System.out.println("\n-- RELATÓRIOS --");
            System.out.println("1. Listar Consultas");
            System.out.println("2. Listar Internações");
            System.out.println("3. Listar Médicos");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoRelatorios = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcaoRelatorios = -1;
            }

            switch (opcaoRelatorios) {
                case 1:
                    new ListarConsultas().listar();
                    break;
                case 2:
                    new ListarInternacoes().listar();
                    break;
                case 3:
                    new ListarMedicos().listar();
                    break;
                case 4:
                    new ListarPacientes().listar();
                    break;
                case 5:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcaoRelatorios != 5);
    }
}