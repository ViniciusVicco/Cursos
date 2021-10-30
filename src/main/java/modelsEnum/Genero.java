package modelsEnum;

public enum Genero {
	FEMININO(0, "Feminino"), MASCULINO(1, "Masculino"), OUTRO(2, "Outro");

	private int index;
	private String label;

	Genero(int index, String label) {
		this.index = index;
		this.label = label;

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
