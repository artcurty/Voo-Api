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


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public int getNum_vagas() {
        return num_vagas;
    }

    public String getDia() {
        return dia;
    }

    public String getDestino() {
        return destino;
    }

    public float getValor() {
        return valor;
    }

    public String getHorario() {
        return horario;
    }

    public Companhia getCompanhia() {
        return companhia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setNum_vagas(int num_vagas) {
        this.num_vagas = num_vagas;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setCompanhia(Companhia companhia) {
        this.companhia = companhia;
    }
}

