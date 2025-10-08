package main.menus;

public class ListarMedicamentos extends Listar {

    public ListarMedicamentos() {
        this.caminhoArquivo = "main/dados/medicamentos.csv";
        this.cabecalho = "Nome\tQuantia\tTarja\tCusto do Medicamento\tNecessita Receita";
    }
}