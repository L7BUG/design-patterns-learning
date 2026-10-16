package com.l7bug.gamecharacter.hero;

import com.l7bug.gamecharacter.hero.equipment.Equipment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class IntelligenceHeroTest {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void init() {
		IntelligenceHero hero = new IntelligenceHero();
		log.info("等级::{}", hero.getLevel());
		log.info("攻击力::{}", hero.getAttackPower());
		log.info("力量::{}", hero.getStrength());
		log.info("装备::{}", hero.getEquipmentName());
		hero.uplevel();
		hero.addEquipment(new Equipment("圣剑"));
		log.info("等级::{}", hero.getLevel());
		log.info("攻击力::{}", hero.getAttackPower());
		log.info("力量::{}", hero.getStrength());
		log.info("装备::{}", hero.getEquipmentName());
	}
}