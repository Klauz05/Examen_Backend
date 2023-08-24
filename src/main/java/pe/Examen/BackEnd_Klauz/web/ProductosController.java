package pe.Examen.BackEnd_Klauz.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.Examen.BackEnd_Klauz.model.Productos;
import pe.Examen.BackEnd_Klauz.model.Vendedores;
import pe.Examen.BackEnd_Klauz.repository.ProductosRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/productos")
@AllArgsConstructor
public class ProductosController {
    private ProductosRepository productosRepository;

    @GetMapping("/list")
    List<Productos> list(){
        return productosRepository.findAll();
    }

    @GetMapping("buscar/{nombre}")
    List<Productos> getProductosbynombre(@PathVariable String nombre){
        return productosRepository.findByNombreContaining(nombre);
    }
    @GetMapping("buscarId/{id}")
    Productos getProductosbyid(@PathVariable Integer id){
        return productosRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Productos crearProducto(@RequestBody @Validated Productos productos) {
        return productosRepository.save(productos);
    }
}
