package com.l7bug.gamecharacter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * GameConfig
 *
 * @author l
 * @since 2026/8/20 10:35
 */
public enum GameConfig {
	CONFIG;
	private final String version;
	private final String gameName;
	private GameDifficulty gameDifficulty = GameDifficulty.MEDIUM;
	/**
	 * 亮度
	 */
	private int brightness = 50;

	GameConfig() {
		Logger log = LoggerFactory.getLogger(GameConfig.class);
		try (InputStream resourceAsStream = GameConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (resourceAsStream == null) {
				throw new RuntimeException("异常中止：没有游戏配置文件！");
			}
			try (InputStreamReader reader = new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8)) {
				Properties props = new Properties();
				props.load(reader);
				this.gameName = props.getProperty("gameName");
				this.version = props.getProperty("version");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		File userConfigFile = new File(this.getUserConfigPath());
		File parentFile = userConfigFile.getParentFile();

		if (!parentFile.isDirectory()) {
			parentFile.mkdirs();
		}
		if (userConfigFile.isFile()) {
			try (InputStream inputStream = new FileInputStream(this.getUserConfigPath())) {
				try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
					Properties props = new Properties();
					props.load(reader);
					try {
						this.brightness = Integer.parseInt(props.getOrDefault("brightness", "50").toString());
					} catch (NumberFormatException e) {
						log.warn("[获取用户配置-亮度]::失败,启用默认值:{}", this.brightness, e);
					}

					try {
						this.gameDifficulty = GameDifficulty.valueOf(props.getOrDefault("gameDifficulty", "MEDIUM").toString());
					} catch (IllegalArgumentException e) {
						log.warn("[获取用户配置-难度]::失败,启用默认值:{}", this.gameDifficulty, e);
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void setGameDifficulty(GameDifficulty gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
		saveFile();
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
		saveFile();
	}

	private void saveFile() {
		try (OutputStream resourceAsStream = new FileOutputStream(this.getUserConfigPath())) {
			resourceAsStream.write(this.toUserConfigString().getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getUserConfigPath() {
		return String.join(File.separator, System.getProperty("user.home"), ".config", "design-patterns-learning", "game-character", "user-config.properties");
	}

	private String toUserConfigString() {
		return """
			gameDifficulty=%s
			brightness=%s
			""".formatted(this.gameDifficulty, this.brightness);
	}

	public String getVersion() {
		return version;
	}

	public String getGameName() {
		return gameName;
	}

	public GameDifficulty getGameDifficulty() {
		return gameDifficulty;
	}

	public int getBrightness() {
		return brightness;
	}
}
