package io.github.bktlib.ui.events;

/**
 * Representa o evento de quando o jogador
 * clica em um item no menu.
 * 
 * @author Leonardosc
 */
public class ItemClickedEvent
{	
	private MouseButton button;
	
	/**
	 * @param button Bot�o com que foi clicado
	 */
	public ItemClickedEvent( MouseButton button )
	{
		this.button = button;
	}
	
	/**
	 * @return O bot�o que acionou o evento.
	 */
	public MouseButton getButton()
	{
		return button;
	}
	
	public enum MouseButton
	{
		/**
		 * Representa o bot�o esquerdo do mouse
		 */
		LEFT,
		
		/**
		 * Representa o bot�o direito do mouse
		 */
		RIGHT
	}
}
