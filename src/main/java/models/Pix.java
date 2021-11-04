package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import modelsEnum.TipoPix;

@Entity
@DiscriminatorValue(value = "P")
public class Pix extends FormaPagamento {

	@Size(min = 8, max = 120)
	private String chave;

	@Enumerated(EnumType.ORDINAL)
	private TipoPix tipoPix;

	public TipoPix getTipoPix() {
		return tipoPix;
	}

	public void setTipoPix(TipoPix tipoPix) {
		this.tipoPix = tipoPix;
	}

	public String getChave() {
		return chave;
	}

	@Override
	public String toString() {
		return "Pix [chave=" + chave + ", tipoPix=" + tipoPix + "]";
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

}
