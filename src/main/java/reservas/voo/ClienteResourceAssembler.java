package reservas.voo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ClienteResourceAssembler implements ResourceAssembler<Cliente,Resource<Cliente>> {
    @Override
    public Resource<Cliente> toResource(Cliente cliente){

        return new Resource<>(cliente,
                linkTo(methodOn(ClienteController.class).oneVooCliente(cliente.getVoo().getId(),cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).allVooClientes(cliente.getVoo().getId())).withRel("Voos Clientes"));
    }
}