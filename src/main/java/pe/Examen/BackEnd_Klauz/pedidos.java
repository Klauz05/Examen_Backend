package pe.Examen.BackEnd_Klauz;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime fecemision;
    private int diaspago;
    private String direccion;
    @Column(name = "montotal")
    private Double monTotal;
    private LocalDateTime fecregistro;


}
