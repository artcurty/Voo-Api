package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


interface VooRepository extends JpaRepository<Voo,Long>{

    List<Voo> findVoosByOrigemAndDestino(String Origem, String Destino);
    List<Voo> findVoosByCompanhia_Code(String Companhia_Code);
    List<Voo> findVoosByCompanhia_Id(Long Companhia_Id);
    List<Voo> findVoosByDestino(String Destino);
    List<Voo> findVoosByOrigem(String Origem);
    Voo findVooByIdAndCompanhia_Id(Long Voo_Id, Long Companhia_Id);

}
