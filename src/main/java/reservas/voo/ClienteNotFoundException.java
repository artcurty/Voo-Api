package reservas.voo;

class ClienteNotFoundException extends RuntimeException {

    ClienteNotFoundException(Long id) {
        super("Não é possivel encontrar o cliente com id: " + id);
    }
}