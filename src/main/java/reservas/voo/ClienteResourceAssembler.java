package reservas.voo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class ClienteResourceAssembler implements ResourceAssembler<Cliente,Resource<Cliente>> {

    @Override
    public Resource<Cliente> toResource(Cliente cliente){
        return new Resource<>(cliente, linkTo(methodOn(ClienteController.class).one(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).AllClientes()).withRel("Clientes"));
    }
}
