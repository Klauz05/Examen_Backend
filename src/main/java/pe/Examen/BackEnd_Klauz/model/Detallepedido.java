package pe.Examen.BackEnd_Klauz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Detallepedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idproducto",referencedColumnName = "id")
    private Productos productos;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="idpedido",referencedColumnName = "id")
    private Pedidos pedidos;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;


}
