package reservas.voo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class VooResourceAssembler implements ResourceAssembler<Voo,Resource<Voo>> {
    @Override
    public Resource<Voo> toResource(Voo voo){
        return new Resource<>(voo,linkTo(methodOn(VooController.class).one(voo.getId())).withSelfRel(),
                linkTo(methodOn(VooController.class).AllVoos("All","All")).withRel("Voos"));
    }

}
