
public class Test
{
	public static void main(String[] args)
	{
		Item it = new Medicine("Potion", 20, true, false, true,
				"A mysterious Potion that restores 20HP of any Pokemon.");
		ItemStack stack = new ItemStack(it, 20);
		System.out.println(stack.getStackAmount() + "x " + stack.getItemName());
		System.out.println(stack.getItemDescription());
		
		if (EvolutionMethod.ITEM.isCancellable()) {
			
		}
	}
}
