package com.github.psinalberth.checklist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.github.psinalberth.checklist.model.Periodicidade;

public class PeriodicidateTest {
	
	Periodicidade per;
	
	@Before
	public void setup() {
		
		per = Periodicidade.DIARIO;
	}
	
	@Test
	public void deveRecuperarEnum() {
		
		Periodicidade p2 = Periodicidade.getEnum(2);
		
		assertNotNull(p2);
		assertEquals(p2, per);
	}
}
