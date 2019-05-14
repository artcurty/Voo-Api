package reservas.voo;


import javax.persistence.*;
import java.util.List;


@Entity
public class Companhia {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    private String nome;
    private String code;

    public Companhia(){

    }

    public Companhia(String nome, String code) {
        this.nome = nome;
        this.code = code;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCode(String code) {
        this.code = code;
    }

}