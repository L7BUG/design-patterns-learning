package com.l7bug.gamecharacter.factory;

import com.l7bug.gamecharacter.hero.AbstractHero;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
class AgilityHeroFactoryTest {

	@Test
	void createHero() {
		AgilityHeroFactory agilityHeroFactory = new AgilityHeroFactory();
		AbstractHero hero = agilityHeroFactory.createHero();
		log.info("等级::{}", hero.getLevel());
	}

}