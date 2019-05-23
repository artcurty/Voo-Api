package reservas.voo;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ClienteRepository extends JpaRepository<Cliente,Long>
{

    List<Cliente> findClientesByVoo_Id(Long Voo_Id);

    Cliente findClienteByIdAndVoo_Id(Long Cliente_Id, Long Voo_Id);

}


