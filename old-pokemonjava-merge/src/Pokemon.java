import java.util.Map;

public class Pokemon extends PokemonInfo
{
	private String nickname;
	private int[] stats, individualValues, effortValues;

	public Pokemon(int pokedexNumber)
	{
		super(pokedexNumber);
	}
}
