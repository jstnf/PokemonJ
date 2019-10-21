
public class ItemStack
{
	private Item item;
	private int stackAmount;

	/**
	 * Instance of ItemStack, default amount of items is 1.
	 * 
	 * @param item
	 *            The item in the stack.
	 */
	public ItemStack(Item item)
	{
		this.item = item;
		stackAmount = 1;
	}

	/**
	 * Instance of ItemStack with defined amount of items.
	 * 
	 * @param item
	 *            The item in the stack.
	 * @param numItems
	 *            The number of that item in the stack.
	 */
	public ItemStack(Item item, int numItems)
	{
		this.item = item;
		stackAmount = numItems;
	}

	/**
	 * Returns the name of the item in the stack.
	 * 
	 * @return name of the item in the stack
	 */
	public String getItemName()
	{
		return item.getName();
	}

	@Deprecated
	/**
	 * Returns the category of the item in the stack.
	 * 
	 * @return numerical ID of item category
	 */
	public int getItemCategory()
	{
		return item.getCategory();
	}

	/**
	 * Returns the category of the item in the stack.
	 * 
	 * @return ItemType of item in the stack
	 */
	public ItemType getItemType()
	{
		return item.getItemType();
	}

	/**
	 * Returns the description of the item in the stack.
	 * 
	 * @return description of item in stack
	 */
	public String getItemDescription()
	{
		return item.getDescription();
	}

	/**
	 * Uses an item in the stack.
	 * 
	 * @return true if the item can be used, false if cannot.
	 */
	public boolean use()
	{
		if (!hasItems())
		{
			return false;
		}
		else
		{
			stackAmount--;
			return true;
		}
	}

	/**
	 * Checks if there are any items in the stack.
	 * 
	 * @return true if any items in the stack, false otherwise.
	 */
	public boolean hasItems()
	{
		if (stackAmount <= 0)
		{
			return false;
		}
		return true;
	}

	// GETTERS AND SETTERS

	/**
	 * @return The item in the stack.
	 */
	public Item getItem()
	{
		return item.getItem();
	}

	/**
	 * @param item
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * @return Number of items in the stack.
	 */
	public int getStackAmount()
	{
		return stackAmount;
	}

	/**
	 * @param stackAmount
	 */
	public void setStackAmount(int stackAmount)
	{
		this.stackAmount = stackAmount;
	}
}
