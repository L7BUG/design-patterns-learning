package com.l7bug.gamecharacter.hero;

import com.l7bug.gamecharacter.hero.equipment.Equipment;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 抽象英雄
 *
 * @author l
 * @since 2026/8/26 11:20
 */
public abstract class AbstractHero {
	private final Equipment[] equipment = new Equipment[6];
	private int level = 0;
	/**
	 * 力量
	 */
	private float strength;
	/**
	 * 敏捷
	 */
	private float intelligence;
	/**
	 * 智力
	 */
	private float agility;

	/**
	 * 获取最大生命值
	 *
	 * @return 最大生命值
	 */
	public int getMaxHp() {
		return (int) (strength * 33);
	}

	/**
	 * 最大护甲值
	 *
	 * @return 最大护甲值
	 */
	public float getArmor() {
		return intelligence * 0.33F;
	}

	/**
	 * 最大法力值
	 *
	 * @return 最大法力值
	 */
	public int maxMagic() {
		return (int) (agility * 13);
	}

	abstract public float getAttackPower();

	public void uplevel() {
		this.level++;
		this.strength += this.growthStrength();
		this.intelligence += this.growthIntelligence();
		this.agility += this.growthAgility();
	}

	abstract protected float growthStrength();

	abstract protected float growthIntelligence();

	abstract protected float growthAgility();

	public int getLevel() {
		return level;
	}

	public float getStrength() {
		return strength;
	}

	public float getIntelligence() {
		return intelligence;
	}

	public float getAgility() {
		return agility;
	}

	public Equipment[] getEquipment() {
		return equipment;
	}

	public void addEquipment(Equipment equipment) {
		for (int i = 0; i < this.equipment.length; i++) {
			if (this.equipment[i] == null) {
				this.equipment[i] = equipment;
				return;
			}
		}
		throw new RuntimeException("没有可用的装备槽");
	}

	public String getEquipmentName() {
		return Arrays.stream(this.getEquipment()).filter(Objects::nonNull).map(Equipment::name).collect(Collectors.joining(","));
	}
}
