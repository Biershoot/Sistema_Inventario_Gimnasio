package zona_fit.sistemazonafit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import zona_fit.sistemazonafit.modelo.Cliente;

public interface ClienteRepositorio  extends JpaRepository<Cliente, Integer> {
}
