package org.iesvdm.modelo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	private long id;

	@NotBlank(message= "Este campo es obligatorio.")
	@Size(max = 30, message = "No se permiten más de 30 caracteres.")
	private String nombre;

	@NotBlank(message= "Este campo es obligatorio.")
	@Size(max = 30, message = "No se permiten más de 30 caracteres.")
	private String apellido1;

	private String apellido2;

	@NotBlank(message= "Este campo es obligatorio.")
	@Size(max = 50, message = "No se permiten más de 50 caracteres.")
	private String ciudad;

	@Min(value = 100, message = "El rango de las categorías es de 100 a 1000.")
	@Max(value = 1000, message = "El rango de las categorías es de 100 a 1000.")
	private int categoria;
	
}
