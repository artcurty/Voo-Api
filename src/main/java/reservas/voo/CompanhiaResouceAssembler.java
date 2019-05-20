package reservas.voo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class CompanhiaResouceAssembler implements  ResourceAssembler<Companhia, Resource<Companhia>> {
    @Override
    public Resource<Companhia> toResource (Companhia companhia){

        return new Resource<>(companhia,linkTo(methodOn(CompanhiaController.class).one(companhia.getId())).withSelfRel(),
                linkTo(methodOn(CompanhiaController.class).AllCompanhias()).withRel("Companhias"));
    }
}
