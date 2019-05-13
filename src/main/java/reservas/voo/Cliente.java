package reservas.voo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cliente {


    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String nome;
    private int idade;
    private String sexo;
    @ManyToOne
    private Voo voo;

    public Cliente(){}

    public Cliente(String nome, String sexo, int idade, Voo voo) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.voo = voo;
    }

    public Cliente (String nome, String sexo, int idade){
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }


}