package main.entities;

import java.util.ArrayList;
import java.util.List;

public class Medicos {
    
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private List<String> agendaHorarios;
    
    public Medicos(String nome, String crm, String especialidade, double custoConsulta) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
        this.agendaHorarios = new ArrayList<>();
    }

    public void adicionarHorario(String horario) {
        agendaHorarios.add(horario);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public double getCustoConsulta() { return custoConsulta; }
    public void setCustoConsulta(double custoConsulta) { this.custoConsulta = custoConsulta; }

    public List<String> getAgendaHorarios() { return agendaHorarios; }

    @Override
    public String toString() {
        return "Médico [" +
                "Nome: " + nome +
                ", CRM: " + crm +
                ", Especialidade: " + especialidade +
                ", Custo da Consulta: " + custoConsulta +
                ", Agenda de Horários: " + agendaHorarios +
                ']';
    }
}
