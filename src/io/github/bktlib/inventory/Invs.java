/*
 *  Copyright (C) 2016 Leonardosc
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package io.github.bktlib.inventory;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.google.common.base.Preconditions.*;

/**
 * Classe utilitaria com metodos frequentemente usados para manipulacao de
 * inventarios
 * 
 * @author leonardosc
 */
public class Invs
{
	/**
	 * Preenche o inventario com o {@code item}
	 * 
	 * @param inv
	 *            Inventario a ser preenchido.
	 * @param item
	 *            Item com que deseja preencher o {@code inv}
	 */
	public static void fill( final Inventory inv, final ItemStack item )
	{
		checkNotNull( inv, "inv cannot be null" );

		IntStream.rangeClosed( 
				0, inv.getSize() - 1 
		).forEach( idx -> inv.setItem( idx, item ) );
	}

	/**
	 * @see #fill(Inventory, ItemStack)
	 */
	public static void fill( final Supplier<? extends Inventory> supplier, final ItemStack item )
	{
		fill( supplier.get(), item );
	}

	/**
	 * Preenche o inventario com o {@code material}
	 * 
	 * @param inv
	 *            Inventario a ser preenchido.
	 * @param material
	 *            Material com que deseja preencher o {@code inv}
	 */
	public static void fill( final Inventory inv, final Material material )
	{
		checkNotNull( inv, "inv cannot be null" );

		if ( material == Material.AIR ) return;

		fill( inv, new ItemStack( material ) );
	}

	/**
	 * @see #fill(Inventory, Material)
	 */
	public static void fill( final Supplier<? extends Inventory> supplier, Material mat )
	{
		fill( supplier.get(), mat );
	}

	/**
	 * Verifica se o {@code inv} está vazio.
	 * 
	 * @param inv
	 *            Invetario a ser verificado.
	 * @return se o {@code inv} está vazio.
	 */
	public static boolean isEmpty( final Inventory inv )
	{
		checkNotNull( inv, "inv cannot be null" );

		return stream( inv ).allMatch( Objects::isNull );
	}

	/**
	 * @see #isEmpty(Inventory)
	 */
	public static boolean isEmpty( final Supplier<? extends Inventory> supplier )
	{
		return isEmpty( supplier.get() );
	}

	/**
	 * Cria uma nova {@link Stream} dos items do {@code inv}
	 * 
	 * @param inv
	 *            Inventario Inventario em sí
	 * @return Uma nova {@link Stream} dos items do {@code inv}
	 */
	public static Stream<ItemStack> stream( final Inventory inv )
	{
		checkNotNull( inv, "inv cannot be null" );

		return Stream.of( inv.getContents() );
	}

	/**
	 * @see #stream(Inventory)
	 */
	public static Stream<ItemStack> stream( final Supplier<? extends Inventory> supplier )
	{
		return stream( supplier.get() );
	}

	/**
	 * Verifica se todos os slots estão com pelo menos 1 item.
	 * 
	 * @param inv
	 *            Inventario a ser checado
	 * @return Se o inventario está cheio
	 */
	public static boolean isFull( final Inventory inv )
	{
		checkNotNull( inv, "inv cannot be null" );

		return inv.firstEmpty() == -1;
	}

	/**
	 * @see #isFull(Inventory)
	 */
	public static boolean isFull( final Supplier<? extends Inventory> inv )
	{
		return isFull( inv.get() );
	}

	/**
	 * Verifica se TODOS os slots do inventario estão com stacks cheias.
	 * 
	 * @param inv
	 *            Inventario a ser checado
	 * @return Se o inventario está completamente cheio
	 */
	public static boolean isCompletelyFull( final Inventory inv )
	{
		return !isFull( inv ) && stream( inv )
                                 .filter( Objects::nonNull )
                                 .allMatch( item -> item.getAmount() == item.getMaxStackSize() );
	}

	/**
	 * @see #isCompletelyFull(Inventory)
	 */
	public static boolean isCompletelyFull( final Supplier<? extends Inventory> inv )
	{
		return isCompletelyFull( inv.get() );
	}
	
	private Invs() 
	{
        throw new UnsupportedOperationException();
	}
}
