package Gramatica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Datos
{
	private BufferedReader obb = new BufferedReader(new InputStreamReader(System.in));

	public int Entero(String msj)
	{
		int num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Integer.parseInt(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Entero(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

	public double Doble(String msj)
	{
		double num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Double.parseDouble(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Doble(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

	public String Cadena(String msj)
	{
		String cad = "";
		try
		{
			System.out.print(msj + " ");
			cad = obb.readLine().trim();
		}
		catch (StringIndexOutOfBoundsException e)
		{
			cad = this.Cadena(msj);

		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return cad;
	}

	public char Caracter(String msj)
	{
		char car = ' ';
		try
		{
			System.out.print(msj + " ");
			car = obb.readLine().trim().charAt(0);
		}
		catch (StringIndexOutOfBoundsException e)
		{
			car = Caracter(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return car;
	}

	public boolean Boleano(String msj)
	{
		boolean val = true;
		try
		{
			System.out.println(msj + " ");
			val = Boolean.parseBoolean(obb.readLine().trim());
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada...");
		}
		return val;
	}

	public void Print(String msj)
	{
		System.out.print(msj);

	}

	public void Println(String msj)
	{
		System.out.println(msj);
	}

	public byte Byte(String msj)
	{
		byte num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Byte.parseByte(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Byte(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

	public float Flotante(String msj)
	{
		float num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Float.parseFloat(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Flotante(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

	public short Corto(String msj)
	{
		short num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Short.parseShort(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Corto(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

	public long Largo(String msj)
	{
		long num = 0;
		try
		{
			System.out.print(msj + " ");
			num = Long.parseLong(obb.readLine().trim());
		}
		catch (NumberFormatException e)
		{
			num = Largo(msj);
		}
		catch (IOException e)
		{
			System.out.println("Error en el dispositivo de entrada ");
		}
		return num;
	}

}
