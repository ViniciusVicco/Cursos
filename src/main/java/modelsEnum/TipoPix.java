package modelsEnum;

public enum TipoPix {
	TELEFONE(0, "Telefone"), CPF(1, "CPF"), CHAVEALEATORIA(2, "Chave Aletória");

	private Integer index;
	private String label;

	TipoPix(int i, String string) {
		this.index = i;
		this.label = string;
	}

	public String getLabel() {
		return label;
	}

	public Integer getIndex() {
		return index;
	}

	public static TipoPix valueOf(Integer id) {
		if (id == null)
			return null;
		for (TipoPix tipoPix : TipoPix.values()) {
			if (tipoPix.getIndex().equals(id))
				return tipoPix;
		}
		return null;
	}

}
