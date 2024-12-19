package com.nha.java.learning.phoneshop.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneralUtilTest {
	
	@Test
	public void testToInteger() {
		//Given
		List<String> name = List.of("Dara","Cheata","Thida");
		
		//when
		List<Integer> list = GeneralUtil.toInteger(name);
		
		//Then
		assertEquals(3, list.size());
		assertEquals(4, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(5, list.get(2));
	}
	
	@Test
	public void testGetEventNumber() {
		//Given
		List<Integer> number = List.of(4,5,3,20,6,8);
		//When
		List<Integer> eventNumber = GeneralUtil.getEventNumber(number);
		//then
		assertEquals(4, eventNumber.size());
		assertEquals(4, eventNumber.get(0));
		
	}
}
