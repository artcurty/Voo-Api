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
public class CompanhiaController {

        private final CompanhiaRepository Comp_repository;

        CompanhiaController(CompanhiaRepository Comp_repository){
            this.Comp_repository = Comp_repository;
        }

        @GetMapping(value = "/companhias",produces = "application/json; charset=UTF-8")
        public List<Companhia> AllCompanhias(){

                List<Companhia> Cp = Comp_repository.findAll();

            return Cp;
        }

        @GetMapping(value = "/companhias/{id_companhia}",produces = "application/json; charset=UTF-8")
        public Companhia one(@PathVariable Long id_companhia){
            return Comp_repository.findById(id_companhia).orElseThrow(()-> new NotFoundException(id_companhia));
        }

}