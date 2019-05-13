package reservas.voo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> clienteByVoo_id(long id);

    Cliente cleinteByIdVoo_id(long id_cliente ,long id_voo);
}

