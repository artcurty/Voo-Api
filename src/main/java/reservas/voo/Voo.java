package reservas.voo;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Voo {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String nome;
    private String origem;
    private float valor;
    private String destino;
    private String dia;
    private String horario;
    private int num_vagas;

    @ManyToOne
    Companhia companhia;

    public Voo(){

    }

    public Voo(String origem,float valor,String destino,String dia,String horario, int num_vagas, Companhia companhia ) {
        this.nome = nome;
        this.origem = origem;
        this.valor = valor ;
        this.destino = destino;
        this.dia = dia;
        this.horario = horario;
        this.num_vagas = num_vagas;
        this.companhia = companhia;

        this.nome = this.companhia.getCode() + origem.substring(0,2) + destino.substring(0,2) + dia.substring(0,2).toUpperCase() + horario.substring(0,2);
     }
}

