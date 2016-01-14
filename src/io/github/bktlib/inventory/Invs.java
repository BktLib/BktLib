package io.github.bktlib.inventory;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Preconditions;


/**
 * Classe utilitaria para com metodos frequentemente 
 * usados para  manipulacao de inventarios
 * 
 * @author leonardosc
 */
public class Invs
{
	private Invs() {}

	/**
	 * @param inv
	 * @param item
	 */
	public static void fill( Inventory inv, ItemStack item )
	{
		Preconditions.checkNotNull( inv, "inv cannot be null" );
		
		for ( int i = 0; i < inv.getSize(); i++ )
		{
		 	inv.setItem( i, item );
		}
	}

	/**
	 * @param supplier
	 * @param item
	 */
	public static void fill( Supplier<? extends Inventory> supplier, ItemStack item )
	{
		fill( supplier.get(), item );
	}
	
	/**
	 * @param inv
	 * @param material
	 */
	public static  void fill( Inventory inv, Material material )
	{
		Preconditions.checkNotNull( inv, "inv cannot be null" );
		
		if ( material == Material.AIR )
			return;
		
		fill( inv, new ItemStack( material ) );
	}

	/**
	 * @param supplier
	 * @param mat
	 */
	public static void fill( Supplier<? extends Inventory> supplier, Material mat )
	{
		fill( supplier.get(), mat );
	}
	
	/**
	 * @param inv
	 * @return
	 */
	public static boolean isEmpty( Inventory inv )
	{
		Preconditions.checkNotNull( inv, "inv cannot be null" );
		
		return !stream( inv )
				.filter( Objects::nonNull )
				.findAny()
				.isPresent();
	}
	
	/**
	 * @param supplier
	 * @return
	 */
	public static boolean isEmpty( Supplier<? extends Inventory> supplier )
	{
		return isEmpty( supplier.get() );
	}
    
    /**
     * @param inv
     */
    public static void clear( Inventory inv )
    {
        fill( inv, (ItemStack) null );
    }
    
    /**
     * @param supplier
     */
    public static void clear( Supplier<? extends Inventory> supplier )
    {
    	clear( supplier.get() );
    }
    
    /**
     * @param inv
     * @return
     */
    public static Stream<ItemStack> stream( Inventory inv )
    {
    	Preconditions.checkNotNull( inv, "inv cannot be null" );
    	
    	return Stream.of( inv.getContents() );
    }
    
    /**
     * @param supplier
     * @return
     */
    public static Stream<ItemStack> stream( Supplier<? extends Inventory> supplier )
    {
    	return stream( supplier.get() );
    }
}