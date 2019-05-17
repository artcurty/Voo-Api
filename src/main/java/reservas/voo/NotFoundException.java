package reservas.voo;


class NotFoundException extends RuntimeException {

    NotFoundException(Long id) {
        super("Não é possivel encontrar esse id: " + id + "tente um id diferente");
    }
}