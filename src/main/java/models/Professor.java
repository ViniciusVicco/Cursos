package models;

import javax.persistence.Entity;

@Entity
public class Professor extends Pessoa {
	private double Saldo;

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

}
