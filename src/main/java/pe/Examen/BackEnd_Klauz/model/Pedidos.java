package pe.Examen.BackEnd_Klauz.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.spi.CascadeStyles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idcli",referencedColumnName = "id")
    private Clientes clientes;
    @ManyToOne
    @JoinColumn(name="idven",referencedColumnName = "id")
    private Vendedores vendedores;
    private LocalDateTime fecemision;
    @Enumerated(EnumType.STRING)
    private Pago forpag;
    private Integer diaspago;
    private String direccion;
    private Double montotal;
    private LocalDateTime fecregistro;
    @Enumerated(EnumType.STRING)
    private Estado estadopedido;
    @OneToMany(mappedBy = "pedidos",cascade = CascadeType.ALL)
    private List<Detallepedido> detalle;
    public enum Estado{
        PENDIENTE,
        PAGADO
    }

    public enum Pago{
        Tarjeta,
        Efectivo,
        Credito

    }


}
