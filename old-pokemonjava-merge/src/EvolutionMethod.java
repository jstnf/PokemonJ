
public enum EvolutionMethod
{
	HAPPINESS(true),
	HAPPINESS_DAY(true),
	HAPPINESS_NIGHT(true),
	LEVEL(true),
	ATTACK_GREATER(true),
	ATK_DEF_EQUAL(true),
	DEFENSE_GREATER(true),
	SILCOON(true),
	CASCOON(true),
	NINJASK(true),
	SHEDINJA(true),
	BEAUTY(true),
	DAY_HOLD_ITEM(true),
	NIGHT_HOLD_ITEM(true),
	HAS_MOVE(true),
	HAS_IN_PARTY(true),
	LEVEL_MALE(true),
	LEVEL_FEMALE(true),
	LOCATION(true),

	ITEM(false),
	ITEM_MALE(false),
	ITEM_FEMALE(false),
	TRADE(false),
	TRADE_ITEM(false),
	TRADE_SPECIES(false),

	CUSTOM(true);

	private boolean cancellable;

	private EvolutionMethod(boolean cancellable)
	{
		this.cancellable = cancellable;
	}

	public boolean isCancellable()
	{
		return cancellable;
	}
}
