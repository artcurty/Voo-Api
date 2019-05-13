package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


interface VooRepository extends JpaRepository<Voo,Long>{

    List<Voo> vooByOrigemDestino(String origem, String destino);
    List<Voo> vooByCodeCompanhia(int code);
    List<Voo> vooByDestino(String destino);
    List<Voo> vooByOrigem(String origem);

    Voo vooById_Comp_id (long id_voo , long id_comp);
}