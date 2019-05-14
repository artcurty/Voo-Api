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

    ClienteController(ClienteRepository Cl_repository,VooRepository V_repository ,ClienteResourceAssembler Cliente_assembler){
        this.Cl_repository = Cl_repository;
        this.V_repository = V_repository;
        this.Cliente_assembler = Cliente_assembler;
    }

        @GetMapping(value = "/voo/{id_voo}/clientes",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Cliente>> allVooClientes(@PathVariable long id_voo){

                List<Cliente> clientes = Cl_repository.clienteByVoo_id(id_voo);

                List<Resource<Cliente>> Cliente_resource;
                //resource é um container generico que inclui dados e um banco de links

                Cliente_resource = clientes.stream().map(Cliente_assembler::toResource).collect(Collectors.toList());

            return new Resources<>(Cliente_resource, linkTo(methodOn(ClienteController.class).allVooClientes(id_voo)).withSelfRel());
        }

        @GetMapping(value = "/voos/{id_voo}/clientes/{id_cliente}", produces = "application/json; charset=UTF-8")
        public Resource<Cliente> oneVooCliente(@PathVariable("id_voo") long id_voo, @PathVariable("id_cliente") long id_cliente) {

            Cliente cliente = Cl_repository.cleinteByIdVoo_id(id_cliente,id_voo);

            return Cliente_assembler.toResource(cliente);
        }
/*
        @PostMapping(value = "/voos/{id_voo}",produces = "application/json; charset=UTF-8")
        ResponseEntity<?> novoCliente(@RequestBody Cliente novoCliente, @PathVariable long id_voo) throws URISyntaxException{
            Voo voo = V_repository.findById(id_voo)
                    .orElseThrow(() -> new VooNotFoundException(id_voo));
            novoCliente.setVoo(voo);
            Resource<Cliente> resource = Cliente_assembler.toResource(Cl_repository.save(novoCliente));
            return ResponseEntity
                    .created(new URI(resource.getId().expand().getHref()))
                    .body(resource);
        }
*/
        //@DeleteMapping(value = "/voos/{id_voo}/clientes/{id_cliente}",produces = "application/json; charset=UTF-8")


}
