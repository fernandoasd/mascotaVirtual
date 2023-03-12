package com.example.mascotaVirtual;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@SpringBootApplication
public class MascotaVirtualApplication {
	//declaración de atributos
	private String nombreMascota;
	private int nivelMascota;
	private boolean estaHambrienta;
	private String estadoAnimoMascota;
	private int minutosMascotaUltimaComida;
	private int minutosAhora;

	//constructores
	public MascotaVirtualApplication()
	{
	}
	public MascotaVirtualApplication(String nombreMascota)
	{
		this.nombreMascota = nombreMascota;
		estadoAnimoMascota = "aburrida"; //al crear mascota esta aburrida, hambrienta y a nivel 0
		estaHambrienta = true;
		nivelMascota = 0;
	}

	//métodos
	public int mostrarNivel ()
	{
		return nivelMascota;
	}

	private void comer()
	{
		estaHambrienta = false;
		switch (estadoAnimoMascota)
		{
			case "aburrida":
				if (contadorMinutosAburrimiento()>80)
				{
					estadoAnimoMascota = "contenta";
				}
				break;
			default:
				nivelMascota++;
		}
	}
	private void jugar()
	{
		if (estaHambrienta == false)
		{
			switch (estadoAnimoMascota) {
				case "contenta":
					nivelMascota += 2;
					break;
				default:
					estadoAnimoMascota = "contenta";
			}
		}
	}

	private int contadorMinutosAburrimiento()
	{
		return 999;
	}











	public static void main(String[] args) {

		SpringApplication.run(MascotaVirtualApplication.class, args);
		System.out.println("Mascota virtual!");
	}

}
