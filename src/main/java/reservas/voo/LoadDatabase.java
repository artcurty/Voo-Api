package reservas.voo;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


@Configuration
@Slf4j
public class LoadDatabase {

    private static int getRandomIntegerBetweenRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    // Populate Database
    @Bean
    CommandLineRunner initDatabase(CompanhiaRepository Comp_repository, VooRepository V_repository, ClienteRepository Cl_repository){
        return args -> {
            // lista de companhias aereas

            List<String> nome_companhias = Arrays.asList("Azul Brazilian Airlines Azul Brazilian Airlines","Air France Air France ","Gol Transportes Aéreos Gol Transportes Aéreos ","LATAM Airlines Group SA LATAM Airlines Group SA ", "Avianca Brazil Avianca Brazil");

            List<String> uf_list = Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RR", "RO", "RJ", "RN", "RS", "SC", "SP", "SE", "TO");

            Faker faker = new Faker(new Locale("pt"));

            List<String> cod_companhia = Arrays.asList("AD", "LA", "O6", "G3", "2Z");

            List<String> sex_list = Arrays.asList("Homem", "Mulher", "Outro");

            List<String> completed_routes = new ArrayList<>();

            int num_ufs = uf_list.size();

                for(int i = 0; i < nome_companhias.size() ;i++){

                    // Random UF index
                    int uf_index = getRandomIntegerBetweenRange(0, 25);

                    // Generating Random entry values for Hotel entity
                    int stars = getRandomIntegerBetweenRange(1, 5);
                    String uf = uf_list.get(uf_index);
                    String name = faker.company().name();

                    // Create Flight Company entity
                    Companhia company = new Companhia(nome_companhias.get(i),cod_companhia.get(i));
                    Comp_repository.save(company);

                    //Generating Random entry values for Bedroom entities
                    int num_flights = getRandomIntegerBetweenRange(1, 20);

                        // Flight loop
                        for (int j = 0; j < num_flights; j++){
                            int num_vagas = getRandomIntegerBetweenRange(1, 25);
                            float valor_passagem = getRandomIntegerBetweenRange(100, 200) * 10;
                            int num_taken_seats = getRandomIntegerBetweenRange(1, num_vagas);
                            int orig_index = getRandomIntegerBetweenRange(0, num_ufs-1);

                            //criando rotas aleatórias
                            while (true) {
                                if (!completed_routes.contains(uf_list.get(orig_index)) || completed_routes.size() == uf_list.size()) {
                                    break;
                                } else {
                                    orig_index = getRandomIntegerBetweenRange(0, num_ufs-1);
                                }
                            }
                            String origem = uf_list.get(orig_index);

                            int dest_index = getRandomIntegerBetweenRange(0, num_ufs-1);

                            while (dest_index == orig_index) {
                                dest_index = getRandomIntegerBetweenRange(0, num_ufs-1);
                            }
                            String destino = uf_list.get(dest_index);

                            int time = getRandomIntegerBetweenRange(5, 23);
                            String hora_d_voo = time + ":00";

                            Voo voo_ida = new Voo(origem,valor_passagem,destino,"Monday",hora_d_voo,num_taken_seats,company);
                            Voo voo_volta = new Voo(origem,valor_passagem,destino, "Friday", hora_d_voo,num_taken_seats,company);

                            V_repository.save(voo_ida);
                            V_repository.save(voo_volta);
                            completed_routes.add(origem);

                            //Cliente loop
                            for(int k = 0; k <= num_taken_seats ;k++){

                                //nome aleatório para cliente, utilizando função faker
                                String nome_cliente = faker.name().fullName();

                                //Selecionando sexo aleatóriamente
                                int rando_sex = getRandomIntegerBetweenRange(0,2);
                                String sexo_cliente = sex_list.get(rando_sex);

                                //Seleciona idade aleatória entre 0 e 70, para o novo usuario
                                int idade = getRandomIntegerBetweenRange(1,70);

                                //salvando novo cliente no repositóro, com voo de ida e volta
                                Cliente novo_cliente = new Cliente(nome_cliente,sexo_cliente,idade,voo_ida);
                                Cliente novo_cliente_volta = new Cliente(nome_cliente,sexo_cliente,idade,voo_volta);
                                Cl_repository.save(novo_cliente);
                                Cl_repository.save(novo_cliente_volta);
                            }
                        }
                }
        };
    }
}