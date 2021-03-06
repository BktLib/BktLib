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

package io.github.bktlib.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.bktlib.command.tabcompleter.DefaultTabCompleter;
import io.github.bktlib.command.tabcompleter.TabCompleter;
import io.github.bktlib.command.UsageTarget;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Command {

  /**
   * Nome do comando.
   */
  String name();

  /**
   * Permissão do comando.
   */
  String permission() default "";

  /**
   * Descrição do comando.
   */
  String description() default "";

  /**
   * Uso correto do comando.
   */
  String usage() default "";

  /**
   * Lista de aliases do comando.
   */
  String[] aliases() default {};

  /**
   * TabCompleter do comando.
   */
  Class<? extends TabCompleter> tabCompleter() default DefaultTabCompleter.class;

  /**
   * <p>
   * Sub Comandos deve ser registrados seguindo o seguinte padrão.
   * </p>
   * <p>
   * <p>
   * {@code NomeDaClasse::nomeDoMetodo}
   * </p>
   * <p>
   * O {@code NomeDaClasse} pode ser composto pelo nome da classe, sem o
   * pacote, caso a classe esteja no mesmo pacote do comando, {@code this}
   * caso o sub comando esteja na mesma classe do comando, ou o nome da classe
   * completa(com o pacote), por exemplo
   * {@code pacote.com.a.Classe::nomeDoMetodo}
   * </p>
   * <p>
   * <p>
   * O {@code nomeDoMetodo} pode ser composto simplesmente pelo nome do método
   * do sub comando, por uma lista de nomes, por exemplo
   * {@code this::[subComando1, subComando2, SubComando3]} ou pelo caractere
   * {@code *} caso queira que todos os métodos que possuem a anotação
   * {@link SubCommand} sejam incluidos.
   * </p>
   *
   * @return Lista de sub comandos.
   */
  String[] subCommands() default {};

  /**
   * <p>Formato:</p>
   * <p>
   * {@code
   *  index:[lista, de, coisas] ou index:$placeholder$
   * }</p>
   *
   * <p>Por exemplo:</p>
   * <p>
   *   {@code 1:[sugestao1, sugestao2] 2:$players$ 3:[apenas_uma] 4:$mobs$}
   * </p>
   *
   * <p>PlaceHolders:</p>
   * <ul>
   *  <li> $players$ - Lista de jogadores online. </li>
   *  <li> $mobs$ - Lista de mobs. </li>
   *  <li> $items$ - Lista de items. </li>
   *  <li> $enchantments$ - Lista de encantamentos. </li>
   * </ul>
   */
  String tabCompletions() default "";

  /**
   * Destino de uso do comando.
   *
   * @see UsageTarget
   */
  UsageTarget usageTarget() default UsageTarget.BOTH;
}
