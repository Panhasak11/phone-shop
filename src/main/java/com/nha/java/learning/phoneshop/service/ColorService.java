package com.nha.java.learning.phoneshop.service;

import com.nha.java.learning.phoneshop.entity.Color;

public interface ColorService {

	Color creat(Color color);
	Color getById(Long colorId);
}
