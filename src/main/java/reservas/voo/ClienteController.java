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
    private final VooRepository V_repository;
    private final ClienteResourceAssembler Cliente_assembler;

    ClienteController(ClienteRepository Cl_repository,VooRepository V_repository, ClienteResourceAssembler Cliente_assembler){
        this.Cl_repository = Cl_repository;
        this.V_repository = V_repository;
        this.Cliente_assembler = Cliente_assembler;
    }

    @GetMapping(value = "/voo/{id_voo}/clientes",produces = "application/json; charset=UTF-8")
    public Resource<Resources<Cliente>> allVooClientes(@PathVariable long id_voo){
        List<Cliente> clientes = Cl_repository.clienteByVoo_id(id_voo);
        List<Resource<Cliente>> Cliente_resource;
        //resource é um container generico que inclui dados e um banco de links

        Cliente_resource = clientes.stream().map(Cliente_assembler::toResource).collect(Collectors.toList());

        return new Resource<>(Cliente_resource,linkTo(methodOn(ClienteController.class).allVooClientes(id_voo)).withSelfRel());

    }
    @GetMapping(value = "/voos/{id_voo}/clientes/{id_cliente}", produces = "application/json; charset=UTF-8")

    @PostMapping(value = "/voos/{id_voo}",produces = "application/json; charset=UTF-8")


    @DeleteMapping(value = "/voos/{id_voo}/clientes/{id_cliente}",produces = "application/json; charset=UTF-8")


}
