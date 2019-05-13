package reservas.voo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}