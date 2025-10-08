package main.entities;

public class Medicamento {
    private String nome;
    private int quantia;
    private String tarja;
    private double preco;
    private String necessitaReceita;

    public Medicamento(String nome, int quantia, String tarja, double preco, String necessitaReceita) {
        this.nome = nome;
        this.quantia = quantia;
        this.tarja = tarja;
        this.preco = preco;
        this.necessitaReceita = necessitaReceita;
    }

    public String getNome() { return nome; }
    public int getQuantia() { return quantia; }
    public String getTarja() { return tarja; }
    public double getPreco() { return preco; }
    public String getNecessitaReceita() { return necessitaReceita; }

    public void setQuantia(int quantia) { this.quantia = quantia; }
    public void setPreco(double preco) { this.preco = preco; }
}