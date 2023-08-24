package pe.Examen.BackEnd_Klauz.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PostRemove;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.Examen.BackEnd_Klauz.model.Detallepedido;
import pe.Examen.BackEnd_Klauz.model.Pedidos;
import pe.Examen.BackEnd_Klauz.model.Productos;
import pe.Examen.BackEnd_Klauz.repository.DetallepedidoRepository;
import pe.Examen.BackEnd_Klauz.repository.PedidosRepository;
import pe.Examen.BackEnd_Klauz.repository.ProductosRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/pedidos")
@AllArgsConstructor
public class PedidosController {
    private PedidosRepository pedidosRepository;
    private DetallepedidoRepository detallepedidoRepository;
    private ProductosRepository productosRepository;

    @GetMapping("/buscarId/{id}")
    Pedidos getPedido(@PathVariable Integer id){
        return pedidosRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/crear")
    Pedidos crearPedido(@RequestBody Pedidos pedidoform){
        Pedidos pedidos=new Pedidos();
        List<Detallepedido> ventadetalle=new ArrayList<>();
        double total=0;
        if(pedidoform.getDetalle().isEmpty()){
            throw new EntityNotFoundException("El detalle es obligatorio");
        }
        for(Detallepedido det:pedidoform.getDetalle()){
            Detallepedido detallepedido=new Detallepedido();
            detallepedido.setProductos(det.getProductos());
            detallepedido.setCantidad(det.getCantidad());
            detallepedido.setPrecio(det.getPrecio());
            detallepedido.setSubtotal(det.getCantidad()*det.getPrecio());
            detallepedido.setPedidos(pedidos);
            ventadetalle.add(detallepedido);
            total+=detallepedido.getSubtotal();
        }
        pedidos.setClientes(pedidoform.getClientes());
        pedidos.setDiaspago(pedidoform.getDiaspago());
        pedidos.setDireccion(pedidoform.getDireccion());
        pedidos.setFecemision(pedidoform.getFecemision());
        pedidos.setFecregistro(LocalDateTime.now());
        pedidos.setForpag(pedidoform.getForpag());
        pedidos.setVendedores(pedidoform.getVendedores());
        pedidos.setEstadopedido(Pedidos.Estado.PENDIENTE);
        pedidos.setMontotal(total);
        pedidos.setDetalle(ventadetalle);
        return pedidosRepository.save(pedidos);
    }
    @PutMapping("/actualizarPago/{id}")
    Pedidos update(
            @PathVariable Integer id
    ) {
        Pedidos pedido = pedidosRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        pedido.setEstadopedido(Pedidos.Estado.PAGADO);

        return pedidosRepository.save(pedido);
    }
}
