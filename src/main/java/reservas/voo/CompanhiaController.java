package reservas.voo;

import org.hibernate.ResourceClosedException;
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
public class CompanhiaController {

        private final CompanhiaRepository Comp_repository;
        private final CompanhiaResouceAssembler Comp_assembler;

        CompanhiaController(CompanhiaRepository Comp_repository,CompanhiaResouceAssembler Comp_assembler){
            this.Comp_repository = Comp_repository;
            this.Comp_assembler = Comp_assembler;
        }

        @GetMapping(value = "/companhias",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Companhia>> AllCompanhias(){

            List<Resource<Companhia>> companhias = Comp_repository.findAll().stream()
                .map(companhia -> new Resource<>(companhia,
                linkTo(methodOn(CompanhiaController.class).one(companhia.getId())).withSelfRel(),
                linkTo(methodOn(CompanhiaController.class).AllCompanhias()).withRel("Companhias"))).collect(Collectors.toList());

            return new Resources<>(companhias,linkTo(methodOn(CompanhiaController.class).AllCompanhias()).withSelfRel());

        }


        @GetMapping(value = "/companhias/{id_companhia}",produces = "application/json; charset=UTF-8")
        public Resource<Companhia> one(@PathVariable Long id_companhia){
            Companhia companhia = Comp_repository.findById(id_companhia).orElseThrow(()-> new CompanhiaNotFoundException(id_companhia));

            return Comp_assembler.toResource(companhia);
        }
}