package reservas.voo;

import org.springframework.data.jpa.repository.Query;
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
public class ClienteController
{
        private final ClienteRepository Cl_repository;
        private final ClienteResourceAssembler Cl_Assembler;
        private final VooRepository V_repository;

        ClienteController(ClienteRepository Cl_repository, ClienteResourceAssembler Cl_Assembler,VooRepository V_repository)
        {
            this.Cl_repository = Cl_repository;
            this.Cl_Assembler = Cl_Assembler;
            this.V_repository = V_repository;
        }

        @GetMapping(value = "/clientes",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Cliente>> AllClientes()
        {
            List<Resource<Cliente>> clientes = Cl_repository.findAll().stream()
                        .map(cliente -> new Resource<>(cliente,linkTo(methodOn(ClienteController.class).one(cliente.getId())).withSelfRel(),
                                                               linkTo(methodOn(ClienteController.class).AllClientes()).withRel("Clientes"))).collect(Collectors.toList());

            return new Resources<>(clientes,linkTo(methodOn(ClienteController.class).AllClientes()).withSelfRel());
        }

        @GetMapping(value = "/clientes/{id_cliente}",produces = "application/json; charset=UTF-8")
        public Resource<Cliente> one(@PathVariable Long id_cliente)
        {

            Cliente cliente = Cl_repository.findById(id_cliente).orElseThrow(()-> new ClienteNotFoundException(id_cliente));

            return Cl_Assembler.toResource(cliente);
        }

        @GetMapping(value = "voos/{Voo_Id}/clientes",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Cliente>> allClienteByVoo(@PathVariable Long Voo_Id)
        {
                List<Cliente> clientes = Cl_repository.findClientesByVoo_Id(Voo_Id);
                List<Resource<Cliente>> Cliente_resource;

                Cliente_resource = clientes.stream().map(Cl_Assembler::toResource).collect(Collectors.toList());

                return new Resources<>(Cliente_resource, linkTo(methodOn(ClienteController.class).allClienteByVoo(Voo_Id)).withSelfRel());

        }

        @GetMapping(value = "/voos/{Voo_Id}/clientes/{Cliente_Id}",produces = "application/json; charset=UTF-8")
        public Resource<Cliente> ClienteByIdAndVoo_Id(@PathVariable("Cliente_Id") Long Cliente_Id,@PathVariable("Voo_Id") Long Voo_Id)
        {
            Cliente cliente = Cl_repository.findClienteByIdAndVoo_Id(Cliente_Id, Voo_Id);

            return Cl_Assembler.toResource(cliente);
        }

        @PostMapping(value = "/voos/{Voo_Id}",produces = "application/json; charset=UTF-8")
        ResponseEntity<?> novoCliente(@RequestBody Cliente novoCliente, @PathVariable long Voo_Id) throws URISyntaxException{

            Voo voo = V_repository.findById(Voo_Id).orElseThrow(() -> new VooNotFoundException(Voo_Id));

            novoCliente.setVoo(voo);

            Resource<Cliente> resource = Cl_Assembler.toResource(Cl_repository.save(novoCliente));

            return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
        }

}

