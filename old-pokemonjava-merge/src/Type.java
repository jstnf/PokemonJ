import java.util.ArrayList;

public enum Type
{
	NO_TYPE(),
	NORMAL(),
	FIRE(),
	WATER(),
	GRASS(),
	ELECTRIC(),
	FLYING(),
	ROCK(),
	GROUND(),
	STEEL(),
	POISON(),
	PSYCHIC(),
	DARK(),
	GHOST(),
	BUG(),
	DRAGON(),
	FAIRY(),
	ICE(),
	FIGHTING();

	private ArrayList<Type> immune, resistance, weakness;

	private Type()
	{
		init();
	}

	private void init()
	{
		switch (this)
		{
			case NO_TYPE:
				break;
			case NORMAL:
				break;
			case FIRE:
				break;
			case WATER:
				break;
			case GRASS:
				break;
			case ELECTRIC:
				break;
			case FLYING:
				break;
			case ROCK:
				break;
			case GROUND:
				break;
			case STEEL:
				break;
			case POISON:
				break;
			case PSYCHIC:
				break;
			case DARK:
				break;
			case GHOST:
				break;
			case BUG:
				break;
			case DRAGON:
				break;
			case FAIRY:
				break;
			case ICE:
				break;
			default:
				break;
		}
	}
}
