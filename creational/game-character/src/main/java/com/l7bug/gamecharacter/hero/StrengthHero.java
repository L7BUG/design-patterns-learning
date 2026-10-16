package com.l7bug.gamecharacter.hero;

/**
 * 力量型英雄
 *
 * @author l
 * @since 2026/8/26 11:50
 */
public class StrengthHero extends AbstractHero {
	@Override
	public float getAttackPower() {
		return this.getLevel() * 3 + this.getStrength();
	}

	@Override
	protected float growthStrength() {
		return 8;
	}

	@Override
	protected float growthIntelligence() {
		return 4;
	}

	@Override
	protected float growthAgility() {
		return 3;
	}
}
