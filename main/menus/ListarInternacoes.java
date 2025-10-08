package main.menus;

public class ListarInternacoes extends Listar {

    public ListarInternacoes() {
        this.caminhoArquivo = "main/dados/internacoes.csv";
        this.cabecalho = "Código\tPaciente\tMédico\tData Entrada\tQuarto\tCusto\tPlano de Saúde\tStatus";
    }
}