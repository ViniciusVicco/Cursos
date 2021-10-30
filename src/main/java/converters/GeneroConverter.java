//package converters;
//
//import javax.persistence.AttributeConverter;
//
//import modelsEnum.Genero;
//
//public class GeneroConverter implements AttributeConverter<Genero, Integer> {
//
//	@Override
//	public Integer convertToDatabaseColumn(Genero genero) {
//		return genero == null ? null : genero.getIndex();
//
//	}
//
//	@Override
//	public Genero convertToEntityAttribute(Integer dbData) {
//		return Genero.valueOf(dbData);
//
//	}
//
//}
