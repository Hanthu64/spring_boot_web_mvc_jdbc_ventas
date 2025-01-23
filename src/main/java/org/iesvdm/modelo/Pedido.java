package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    int id;
    Double total;
    Date fecha;
    int idCliente;
    int idComercial;
}
