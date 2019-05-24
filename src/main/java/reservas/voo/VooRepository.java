package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface VooRepository extends JpaRepository<Voo,Long>{

    List<Voo> findVoosByOrigemAndDestino(String Origem, String Destino);
    List<Voo> findVoosByCompanhia_Code(String Companhia_Code);
    List<Voo> findVoosByCompanhia_Id(Long Companhia_Id);
    List<Voo> findVoosByDestino(String Destino);
    List<Voo> findVoosByOrigem(String Origem);

/*
    @Query("select v from Voo v where (v.num_vagas - v.vagas_ocupadas)")
    List<Voo> findVoosByNum_vagas();
*/
    Voo findVooByIdAndCompanhia_Id(Long Voo_Id, Long Companhia_Id);



}
