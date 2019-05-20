package reservas.voo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class VooController {

        private final VooRepository V_repository;
        private final VooResourceAssembler V_assembler;

        VooController(VooRepository V_repository,VooResourceAssembler V_assembler)
        {
            this.V_repository = V_repository;
            this.V_assembler = V_assembler;
        }

        @GetMapping(value = "/voos",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Voo>> AllVoo(){

            List<Resource<Voo>> voos = V_repository.findAll().stream()
                    .map(voo -> new Resource<>(voo,linkTo(methodOn(VooController.class).one(voo.getId())).withSelfRel(),
                                                   linkTo(methodOn(VooController.class).AllVoo()).withRel("Voos"))).collect(Collectors.toList());

            return new Resources<>(voos,linkTo(methodOn(VooController.class).AllVoo()).withSelfRel());

        }

        @GetMapping(value = "/voos/{id_voo}",produces = "application/json; charset=UTF-8")
        public Resource<Voo> one(@PathVariable Long id_voo){

            Voo voo = V_repository.findById(id_voo).orElseThrow(()-> new VooNotFoundException(id_voo));

            return V_assembler.toResource(voo);
        }
}
