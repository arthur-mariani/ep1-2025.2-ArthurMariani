package main.entities;
import java.util.ArrayList;
import java.util.List;

public class Pacientes {
    private String nome;
    private String cpf;
    private int idade;
    private String planoSaude;
    private String prioridade;
    private List<String> historicoConsultas;
    private List<String> historicoInternacoes;

    public Pacientes(String nome, String cpf, int idade, String planoSaude, String prioridade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.planoSaude = planoSaude;
        this.prioridade = prioridade;
        this.historicoConsultas = new ArrayList<>();
        this.historicoInternacoes = new ArrayList<>();
    }

    public void adicionarConsulta(String consulta) {
        historicoConsultas.add(consulta);
    }

    public void adicionarInternacao(String internacao) {
        historicoInternacoes.add(internacao);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getPlanoSaude() { return planoSaude; }
    public void setPlanoSaude(String planoSaude) { this.planoSaude = planoSaude; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public List<String> getHistoricoConsultas() { return historicoConsultas; }
    public List<String> getHistoricoInternacoes() { return historicoInternacoes; }


    public String toString() {
        return "Paciente [" +
                "Nome: " + nome +
                ", CPF: " + cpf +
                ", Idade: " + idade +
                ", Plano de Saúde: " + planoSaude +
                ", Prioridade: " + prioridade +
                ", Historico de Consultas: " + historicoConsultas +
                ", Historico de Internações: " + historicoInternacoes +
                ']';
    }
}
