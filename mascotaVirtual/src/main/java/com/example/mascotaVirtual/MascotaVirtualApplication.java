package com.example.mascotaVirtual;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@SpringBootApplication
public class MascotaVirtualApplication {
	//------------------------declaración de atributos------------------------
	private String nombreMascota;
	private int nivelMascota;
	private boolean estaHambrienta;
	private String estadoAnimoMascota;
	private long inicioMilisegundosMascotaAburrida;
	private long finalMilisegundosMascotaAburrida;

	//------------------------ constructores ------------------------
	public MascotaVirtualApplication()
	{
	}
	public MascotaVirtualApplication(String nombreMascota)
	{
		this.nombreMascota = nombreMascota;
		setEstadoAnimoMascota("aburrida");
		estaHambrienta = true;
		nivelMascota = 0;
	}

	public MascotaVirtualApplication(String nombreMascota, int nivelMascota)
	{
		this.nombreMascota = nombreMascota;
		setEstadoAnimoMascota("aburrida");
		estaHambrienta = true;
		this.nivelMascota = nivelMascota;
	}

	//------------------------métodos------------------------

	public String toString()
	{
		String mensaje;
		return "nombre mascota: " + getNombreMascota() + "\nnivel: " + getNivelMascota() + "\nHambrienta: " + (mensaje= (estaHambrienta) ? "si":"no");
	}

	public boolean equals(Object mascota)
	{
		MascotaVirtualApplication otraMascota = (MascotaVirtualApplication) mascota;
		return (nombreMascota == otraMascota.nombreMascota) && (nivelMascota == otraMascota.nivelMascota);
	}

	private long getFinalMilisegundosMascotaAburrida() {
		return finalMilisegundosMascotaAburrida;
	}

	private long getInicioMilisegundosMascotaAburrida() {
		return inicioMilisegundosMascotaAburrida;
	}

	private String getEstadoAnimoMascota() {
		return estadoAnimoMascota;
	}

	public void setEstadoAnimoMascota(String estadoAnimoMascota)
	{
		this.estadoAnimoMascota = estadoAnimoMascota;
		controlAburrimiento("estadoAnimoMascota");
	}

	private void controlAburrimiento(String estadoAnimoMascota)
	{
		switch (estadoAnimoMascota)
		{
			case "aburrida":
				inicioMilisegundosMascotaAburrida = System.currentTimeMillis();
				break;
			default:
				finalMilisegundosMascotaAburrida = System.currentTimeMillis();
		}
	}

	private long contadorMinutosAburrimiento ()
	{
		long minutosAburrimiento = (finalMilisegundosMascotaAburrida - inicioMilisegundosMascotaAburrida)/60000;
		return minutosAburrimiento;
	}

	public int mostrarNivel ()
	{
		return nivelMascota;
	}

	private void comer()
	{
		String mensaje;
		if (estaHambrienta == true)
		{
			estaHambrienta = false;
			estadoAnimoMascota = "contenta";
			mensaje = nombreMascota + " come y se pone contenta!";
		}

		switch (estadoAnimoMascota)
		{
			case "aburrida":
				if (contadorMinutosAburrimiento()>80)
				{
					estadoAnimoMascota = "contenta";
					mensaje = nombreMascota + " estaba aburrida > 80 min y se pone contenta!";
				}
				break;
			default:
				nivelMascota++;
				mensaje = nombreMascota + " come y al estar contenta sube 1 nivel.";
		}
	}

	private void jugar()
	{
		String mensaje;
		if (estaHambrienta == false)
		{
			switch (estadoAnimoMascota)
			{
				case "contenta":
					nivelMascota += 2;
					mensaje = "Sube dos niveles";
					break;
				default:
					estadoAnimoMascota = "contenta";
					mensaje = "esta " + estadoAnimoMascota;
			}
		}
		else
		{
			mensaje = "no puede jugar por que "+nombreMascota+" esta hambrienta :(";
		}
		System.out.println(mensaje);
	}





	public static void main(String[] args) {

		SpringApplication.run(MascotaVirtualApplication.class, args);
		System.out.println("Mascota virtual!");

		MascotaVirtualApplication mascota1 = new MascotaVirtualApplication("morita", 1);
		MascotaVirtualApplication mascota2 = new MascotaVirtualApplication("morita", 1);

		System.out.println(mascota1);
		System.out.println(mascota2);

		System.out.println(mascota1.equals(mascota2));

		System.out.println("estado de animo: " + mascota1.getEstadoAnimoMascota());

	}
}
