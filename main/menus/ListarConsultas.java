package main.menus;

public class ListarConsultas extends Listar {

    public ListarConsultas() {
        this.caminhoArquivo = "main/dados/consultas.csv";
        this.cabecalho = "Código\tPaciente\tMédico\tData/Hora\tSala\tStatus\tValor Final";
    }
}