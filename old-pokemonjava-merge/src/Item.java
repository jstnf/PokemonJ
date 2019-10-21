
public abstract class Item implements Comparable {
	private String name;
	
	@Deprecated
	private int category;
	/**
	 * 0 - GENERAL 
	 * 1 - MEDICINE 
	 * 2 - POKEBALL 
	 * 3 - BERRY 
	 * 4 - BATTLE-ITEM 
	 * 5 - KEY-ITEM
	 * 6 - MAIL
	 */
	private ItemType itemType;
	
	private boolean inBattleByPokemon;
	private boolean inBattleByTrainer;
	
	private String description;
	
	public Item(String name) {
		this.name = name;
	}
	
	@Deprecated
	public Item(String name, int type, boolean pokemonUse, boolean trainerUse, String desc) {
		this.name = name;
		category = type;
		inBattleByPokemon = pokemonUse;
		inBattleByTrainer = trainerUse;
		description = desc;
	}
	
	public Item(String name, ItemType type, boolean pokemonUse, boolean trainerUse, String desc) {
		this.name = name;
		itemType = type;
		inBattleByPokemon = pokemonUse;
		inBattleByTrainer = trainerUse;
		description = desc;
	}
	
	public Item getItem() {
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	@Deprecated
	public int getCategory() {
		return category;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean pokemonUse() {
		return inBattleByPokemon;
	}
	
	public boolean trainerUse() {
		return inBattleByTrainer;
	}
	
	@Override
	public int compareTo(Object arg0) {
		if (arg0.getClass().getName().equals(this.name)) {
			return 0;
		}
		return 1;
	}
	
}
