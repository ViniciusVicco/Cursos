package modelsEnum;

public enum Genero {
	FEMININO(1,"Feminino"), MASCULINO(2,"Masculino"), OUTRO(3,"Outro");

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
