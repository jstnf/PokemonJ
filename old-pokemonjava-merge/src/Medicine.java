
public class Medicine extends Item
{
	private int statusRevoke;
	private int statusInvoke;
	/**
	 * 0 - NONE 1 - BURN 2 - POISON 3 - PARALYSIS 4 - FREEZE 5 - SLEEP 6 - CONFUSION
	 * 7 - ALL
	 */
	private int changeHP;
	private int changePP;

	/**
	 * Instance of a medicine that changes HP or PP only.
	 * 
	 * @param name
	 *            Name of the item
	 * @param points
	 *            Amount by which the stat changes
	 * @param toggle
	 *            If true, changes HP, if false, changes PP
	 * @param pokeUse
	 *            If true, Pokemon can use the item in battle
	 * @param trainerUse
	 *            if true, the player and CPU can use the item in battle
	 */
	public Medicine(String name, int points, boolean toggle, boolean pokeUse, boolean trainerUse, String desc)
	{
		super(name, ItemType.MEDICINE, pokeUse, trainerUse, desc);
		if (toggle)
		{
			statusRevoke = 0;
			statusInvoke = 0;
			changeHP = points;
			changePP = 0;
		}
		else
		{
			statusRevoke = 0;
			statusInvoke = 0;
			changeHP = 0;
			changePP = points;
		}
	}

	/**
	 * Instance of a medicine that changes both status and HP.
	 * 
	 * @param name
	 *            Name of the item
	 * @param statTake
	 *            ID of status to be removed
	 * @param statGive
	 *            ID of status to be given
	 * @param points
	 *            Amount by which HP changes
	 * @param pokeUse
	 *            If true, Pokemon can use the item in battle
	 * @param trainerUse
	 *            if true, the player and CPU can use the item in battle
	 */
	public Medicine(String name, int statTake, int statGive, int points, boolean pokeUse, boolean trainerUse,
			String desc)
	{
		super(name, ItemType.MEDICINE, pokeUse, trainerUse, desc);
		statusRevoke = statTake;
		statusInvoke = statGive;
		changeHP = points;
		changePP = 0;
	}

	/**
	 * Instance of a medicine that changes status only.
	 * 
	 * @param name
	 *            Name of the item
	 * @param statTake
	 *            ID of status to be removed
	 * @param statGive
	 *            ID of status to be given
	 * @param pokeUse
	 *            If true, Pokemon can use the item in battle
	 * @param trainerUse
	 *            if true, the player and CPU can use the item in battle
	 */
	public Medicine(String name, int statTake, int statGive, boolean pokeUse, boolean trainerUse, String desc)
	{
		super(name, ItemType.MEDICINE, pokeUse, trainerUse, desc);
		statusRevoke = statTake;
		statusInvoke = statGive;
		changeHP = 0;
		changePP = 0;
	}
}
