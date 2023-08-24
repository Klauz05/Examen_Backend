package pe.Examen.BackEnd_Klauz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.Examen.BackEnd_Klauz.model.Clientes;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Clientes,Integer> {

    List<Clientes> findByRazsocContaining(String razSoc);
}
