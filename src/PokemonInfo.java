import java.util.Map;

public class PokemonInfo
{
	private int pokedex, baseExp, catchRate, happiness, stepsToHatch;
	private double height, weight;
	private String name, kind, entry;
	private Type type1, type2;
	private Map<Integer, Move> moves;
	private int[] baseStats;
	private GenderRate genderRate;
	private GrowthRate growthRate;
	private PokemonColor color;
	private EvolutionMethod evolutionMethod;
	private PokemonInfo evolutionSpecies;

	private int shape;

	public PokemonInfo(int pokedexNumber)
	{
		this.pokedex = pokedexNumber;
	}
	
	// Getters and Setters

	/**
	 * Gets the Pokedex number of the Pokemon.
	 * @return Pokedex Number
	 */
	public int getPokedex()
	{
		return pokedex;
	}
}
