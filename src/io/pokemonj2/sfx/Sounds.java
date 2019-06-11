package io.pokemonj2.sfx;

public enum Sounds
{
	NORMAL_HIT("/sfx/firered_000D.wav"),
	SUPER_EFF_HIT("/sfx/firered_000E.wav"),
	HEAL1("/sfx/firered_00AC.wav"),
	NOT_EFF_HIT("/sfx/firered_00BA.wav"),
	SEND_OUT1("/sfx/firered_00D9.wav"),
	POWER_UP("/sfx/firered_00E8.wav"),
	POWER_DOWN("/sfx/firered_00EE.wav"),
	HEAL2("/sfx/firered_00FE.wav"),
	ITEM_USE("/sfx/firered_0001.wav"),
	CONFIRM("/sfx/firered_0005.wav"),
	OPEN_MENU("/sfx/firered_0006.wav"),
	SWITCH("/sfx/firered_0035.wav"),
	SEND_OUT2("/sfx/firered_0036.wav"),
	RECEIVE("/sfx/receive.wav"),
	BATTLE_START("/sfx/battle-start.wav");

	private String path;

	Sounds(String path)
	{
		this.path = path;
	}

	public String toString()
	{
		return path;
	}
}