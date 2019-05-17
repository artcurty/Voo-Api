package reservas.voo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ClienteController {
        private final ClienteRepository Cl_repository;

        ClienteController(ClienteRepository Cl_repository){
            this.Cl_repository = Cl_repository;
        }

        @GetMapping(value = "/clientes",produces = "application/json; charset=UTF-8")
        public List<Cliente> AllClientes(){

            List<Cliente> cliente = Cl_repository.findAll();

            return cliente;
        }

        @GetMapping(value = "/clientes/{id_cliente}",produces = "application/json; charset=UTF-8")
        public Cliente one(@PathVariable Long id_cliente){
            return Cl_repository.findById(id_cliente).orElseThrow(()-> new NotFoundException(id_cliente));
        }

}

