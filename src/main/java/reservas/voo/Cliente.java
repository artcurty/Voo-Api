package reservas.voo;


import javax.persistence.*;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public Voo getVoo() {
        return voo;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

}