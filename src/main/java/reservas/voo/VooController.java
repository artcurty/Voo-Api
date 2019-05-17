package reservas.voo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VooController {
        private final VooRepository V_repository;

        VooController(VooRepository V_repository)
        {
            this.V_repository = V_repository;
        }

            @GetMapping(value = "/voos",produces = "application/json; charset=UTF-8")
                public  List<Voo> AllVoo(){
                    List<Voo> voo = V_repository.findAll();

                    return voo;
                }
            @GetMapping(value = "/voos/{id_voo}",produces = "application/json; charset=UTF-8")
            public Voo one(@PathVariable Long id_voo){
                return V_repository.findById(id_voo).orElseThrow(()-> new NotFoundException(id_voo));
            }
}
