package pe.Examen.BackEnd_Klauz.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.Examen.BackEnd_Klauz.model.Clientes;
import pe.Examen.BackEnd_Klauz.model.Vendedores;
import pe.Examen.BackEnd_Klauz.repository.ClientesRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@AllArgsConstructor
public class ClientesController {
private ClientesRepository clientesRepository;

@GetMapping("/list")
    List<Clientes> list(){
    return clientesRepository.findAll();
}

@GetMapping("buscar/{razSoc}")
    List<Clientes> getClienteByRazSoc(@PathVariable String razSoc){
return clientesRepository.findByRazsocContaining(razSoc);
}
    @GetMapping("buscarId/{id}")
    Clientes getClientebyid(@PathVariable Integer id){
        return clientesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Clientes crearCliente(@RequestBody @Validated Clientes clientes) {

        return clientesRepository.save(clientes);
    }
}
