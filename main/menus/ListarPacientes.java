package main.menus;

public class ListarPacientes extends Listar {

    public ListarPacientes() {
        this.caminhoArquivo = "main/dados/pacientes.csv";
        this.cabecalho = "Nome\tCPF\tIdade\tPlano de Saúde\tPrioridade\tHistórico de Consultas\tHistórico de Internações";
    }
}
