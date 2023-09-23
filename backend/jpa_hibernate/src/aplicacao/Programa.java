package aplicacao;

import jpa_hibernate.dominio.Pessoa;

public class Programa {
	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(1, "Carlos da Silva", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(1, "Mario de Oliveira", "mario@gmail.com");
		Pessoa p3 = new Pessoa(1, "Ricardo da Silva", "ricardo@gmail.com");

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
	}
}
