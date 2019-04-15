package com.github.psinalberth.checklist.model;

public enum Periodicidade {
	
	A_CADA_30SEG(1),
	A_CADA_HORA (2),
	DIARIO (3),
	SEMANAL(4),
	MENSAL (5),
	ANUAL (6);
	
	private final int value;
	
	private Periodicidade(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static Periodicidade getEnum(int i) {
		
		if (i == 0 || i > Periodicidade.values().length)
			return null;
		
		return Periodicidade.values()[i - 1];
	}
	
	public static String getEnumValue(int i) {
		
		if (i == 0 || i > Periodicidade.values().length)
			return null;
		
		return Periodicidade.values()[i - 1].name();
	}
}
