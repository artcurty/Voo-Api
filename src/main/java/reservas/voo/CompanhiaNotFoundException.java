package reservas.voo;


class CompanhiaNotFoundException extends RuntimeException {

    CompanhiaNotFoundException(Long id) {
        super("Não é possivel encontrar uma companhia com o ID: " + id);
    }
}