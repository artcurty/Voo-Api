package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


interface VooRepository extends JpaRepository<Voo,Long>{


    List<Voo> findVoosByCompanhia_Id(Long Companhia_Id);

    @Query("select v from Voo v where v.origem = ?1 and v.destino =?2 and v.numVagaLivre >= ?3")
    List<Voo> findVoosByOrigemAndDestinoAndNumVagaLivre(String Origem, String Destino, int numVagaLivre);

    @Query("select v from Voo v where v.numVagaLivre >= ?1")
    List<Voo> findVoosByNumVagaLivre(int numVagaLivre);

    @Query("select v from Voo v where v.origem = ?1 and v.numVagaLivre >= ?2")
    List<Voo> findVoosByOrigemAndNumVagaLivre(String Origem, int numVagaLivre);

    @Query("select v from Voo v where v.destino = ?1 and v.numVagaLivre >= ?2")
    List<Voo> findVoosByDestinoAndNumVagaLivre(String Destino, int numVagaLivre);

    Voo findVooByIdAndCompanhia_Id(Long Voo_Id, Long Companhia_Id);



}
