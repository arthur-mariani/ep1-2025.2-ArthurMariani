package main.menus;

public class ListarMedicos extends Listar {

    public ListarMedicos() {
        this.caminhoArquivo = "main/dados/medicos.csv";
        this.cabecalho = "Nome\tCRM\tEspecialidade\tCusto da Consulta\tAgenda de Horários";
    }
}