package pe.Examen.BackEnd_Klauz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.Examen.BackEnd_Klauz.model.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosRepository extends JpaRepository<Productos,Integer> {

    List<Productos> findByNombreContaining(String nombre);

}
