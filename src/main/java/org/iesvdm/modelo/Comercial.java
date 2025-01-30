package org.iesvdm.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {

	private int id;

	@NotBlank(message= "Este campo es obligatorio.")
	@Size(max = 30, message = "No se permiten más de 30 caracteres.")
	private String nombre;

	@NotBlank(message= "Este campo es obligatorio.")
	@Size(max = 30, message = "No se permiten más de 30 caracteres.")
	private String apellido1;

	private String apellido2;
	private float comision;
	
}
