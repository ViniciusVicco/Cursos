package modelsEnum;

public enum CategoriaCurso {
	FINANCEIRO(1, "Financeiro"), TECNOLOGIA(2,"Tecnologia"), MECANICO(3,"Mecanico"), GASTRONOMIA(4,"Gastronomia"), AUTOCONHECIMENTO(5, "Autoconhecimento");
	
	private int index;
	private String label; 
	
	CategoriaCurso(int i, String string) {
		
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
