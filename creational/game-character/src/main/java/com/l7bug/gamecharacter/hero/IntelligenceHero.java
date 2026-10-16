package com.l7bug.gamecharacter.hero;

/**
 * 敏捷型英雄
 *
 * @author l
 * @since 2026/8/26 11:55
 */
public class IntelligenceHero extends AbstractHero {
	@Override
	public float getAttackPower() {
		return this.getLevel() * 3 + this.getStrength();
	}

	@Override
	protected float growthStrength() {
		return 3;
	}

	@Override
	protected float growthIntelligence() {
		return 8;
	}

	@Override
	protected float growthAgility() {
		return 4;
	}
}
