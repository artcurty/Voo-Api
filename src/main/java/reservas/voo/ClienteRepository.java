package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface ClienteRepository extends JpaRepository<Cliente,Long> {
  //  List<Cliente> clientesByVoo_id(Long id_voo);
//    Cliente clienteByIdVoo_id(Long id_cliente ,Long id_voo);

}


