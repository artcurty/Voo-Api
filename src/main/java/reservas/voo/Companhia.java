package reservas.voo;


import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Data
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

    public String getCode() {
        return code;
    }
}