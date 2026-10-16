package com.l7bug.gamecharacter.hero;

/**
 * AgilityHero
 *
 * @author l
 * @since 2026/9/9 16:20
 */
public class AgilityHero extends AbstractHero {
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
		return 4;
	}

	@Override
	protected float growthAgility() {
		return 8;
	}
}
