package pe.Examen.BackEnd_Klauz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.Examen.BackEnd_Klauz.model.Vendedores;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedores,Integer> {
    List<Vendedores> findByFullnameContaining(String nombre);
}
