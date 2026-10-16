package com.l7bug.gamecharacter.factory;

import com.l7bug.gamecharacter.hero.AbstractHero;
import com.l7bug.gamecharacter.hero.AgilityHero;

/**
 * AgilityHeroFactory
 *
 * @author l
 * @since 2026/9/9 17:02
 */
public class AgilityHeroFactory implements HeroFactory {
	@Override
	public AbstractHero createHero() {
		AbstractHero hero = new AgilityHero();
		hero.uplevel();
		return hero;
	}
}
