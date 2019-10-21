
public enum Tile
{

	GRASS(1, false, 0.6),
	BLACK(0, true, 0);
	
	private boolean collision;
	private int tileId;
	private double chanceSpawn;
	
	private Tile(int id, boolean collide, double spawnChance)
	{
		this.tileId = id;
		this.collision = collide;
		this.chanceSpawn = spawnChance;
	}
}
