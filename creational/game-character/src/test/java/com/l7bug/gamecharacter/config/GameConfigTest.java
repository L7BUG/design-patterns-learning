package com.l7bug.gamecharacter.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameConfigTest {

	@BeforeEach
	void setUp() {

	}

	@Test
	void name() {
		GameConfig.CONFIG.setBrightness(100);
		System.err.println(System.getProperty("user.home"));
	}
}