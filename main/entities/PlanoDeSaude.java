package main.entities;

public class PlanoDeSaude {

    public static void listarPlanos() {
        System.out.println("Plano Idoso: 35% de desconto em consultas e internação para pessoas com idade superior a 60 anos.");
        System.out.println("Plano Servidor Público: 25% de desconto em consultas e internação para pessoas que são servidores públicos.");
        System.out.println("Plano Aluno: 10% de desconto em consultas e internação para pessoas matriculadas em universidades.");
        System.out.println("Plano Especial: Internações com tempo inferior a 7 dias não têm custo adicional.");
        System.out.println("Nenhum: Sem benefícios.");
    }
}
