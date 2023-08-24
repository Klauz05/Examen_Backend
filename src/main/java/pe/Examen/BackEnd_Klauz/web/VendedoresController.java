package pe.Examen.BackEnd_Klauz.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.Examen.BackEnd_Klauz.exception.BadRequestException;
import pe.Examen.BackEnd_Klauz.model.Productos;
import pe.Examen.BackEnd_Klauz.model.Vendedores;
import pe.Examen.BackEnd_Klauz.repository.VendedorRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendedores")
@AllArgsConstructor
public class VendedoresController {
    private VendedorRepository vendedorRepository;
    @GetMapping("/list")
    List<Vendedores> list(){
        return vendedorRepository.findAll();
    }

    @GetMapping("/buscar/{nombre}")
    List <Vendedores> getVendedoresbynombre(@PathVariable String nombre){
        return vendedorRepository.findByFullnameContaining(nombre);
    }
    @GetMapping("buscarId/{id}")
    Vendedores getVendedorbyid(@PathVariable Integer id){
        return vendedorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Vendedores crearVendedor(@RequestBody @Validated Vendedores vendedor) {

        vendedor.setFullname(vendedor.getNombre()+" " +vendedor.getApellidos());
        return vendedorRepository.save(vendedor);
    }

}
