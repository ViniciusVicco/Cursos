package models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Compra {
	@Id
	@GeneratedValue
	Integer id;
	@Column
	double valorTotal;
	@Column
	LocalDate dataPagamento;
}
