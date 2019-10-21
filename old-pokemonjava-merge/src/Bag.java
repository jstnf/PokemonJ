import java.util.ArrayList;

public class Bag
{
	private ArrayList<ItemStack> contents;

	public Bag()
	{
		contents = new ArrayList<ItemStack>();
	}

	public ItemStack find(String itemName)
	{
		for (ItemStack stack : contents)
		{
			if (stack.getItem().getName().equalsIgnoreCase(itemName))
			{
				return stack;
			}
		}
		return null;
	}

	public boolean use(String itemName)
	{
		if (find(itemName) != null)
		{
			// item use code here
			return true;
		}
		return false;
	}
}
