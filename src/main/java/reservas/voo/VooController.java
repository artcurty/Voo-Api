package reservas.voo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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


        // Lista todos os voos padrão
/*
        @GetMapping(value = "/voos",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Voo>> AllVoos(){

            List<Resource<Voo>> voos = V_repository.findAll().stream()
                    .map(voo -> new Resource<>(voo,linkTo(methodOn(VooController.class).one(voo.getId())).withSelfRel(),
                            linkTo(methodOn(VooController.class).AllVoos()).withRel("Voos"))).collect(Collectors.toList());

            return new Resources<>(voos,linkTo(methodOn(VooController.class).AllVoos()).withSelfRel());

        }


 */
        //Lista todos os voos, de acordo com a origem ou destino

        @GetMapping(value = "/voos",produces = "application/json; charset=UTF-8")
        public Resources<Resource<Voo>> AllVoos(@RequestParam (value = "Origem",defaultValue = "All") String Origem, @RequestParam (value = "Destino",defaultValue = "All") String Destino, @RequestParam (value = "numVagaLivre", defaultValue = "0")int numVagaLivre )
        {
            List<Voo> voos;

            List<Resource<Voo>> Voo_resource;

                    if((Origem.equals("All")) && (Destino.equals("All"))){

                        voos = V_repository.findVoosByNumVagaLivre(numVagaLivre);
                    }
                       else if (Origem.equals("All")){

                        voos = V_repository.findVoosByDestinoAndNumVagaLivre(Destino, numVagaLivre);
                    }
                       else if (Destino.equals("All")){
                        voos = V_repository.findVoosByOrigemAndNumVagaLivre(Origem,numVagaLivre);
                    }
                       else{
                        voos = V_repository.findVoosByOrigemAndDestinoAndNumVagaLivre(Origem, Destino, numVagaLivre);
                    }

             Voo_resource = voos.stream().map(V_assembler::toResource).collect(Collectors.toList());

            return new Resources<>(Voo_resource,linkTo(methodOn(VooController.class).AllVoos(Origem,Destino,numVagaLivre)).withSelfRel());

        }

        @GetMapping(value = "/voos/{id_voo}",produces = "application/json; charset=UTF-8")
        public Resource<Voo> one(@PathVariable Long Voo_Id){

            Voo voo = V_repository.findById(Voo_Id).orElseThrow(()-> new VooNotFoundException(Voo_Id));

            return V_assembler.toResource(voo);
        }

        @GetMapping(value = "/companhias/{Companhia_Id}/voos",produces = "application/json; charset=UTF-8")
        public  Resources<Resource<Voo>> AllVooByCompanhia(@PathVariable Long Companhia_Id){

            List<Voo> voos = V_repository.findVoosByCompanhia_Id(Companhia_Id);
            List<Resource<Voo>> Voo_Resource;

            Voo_Resource = voos.stream().map(V_assembler::toResource).collect(Collectors.toList());

            return new Resources<>(Voo_Resource, linkTo(methodOn(ClienteController.class).allClienteByVoo(Companhia_Id)).withSelfRel());

        }





}