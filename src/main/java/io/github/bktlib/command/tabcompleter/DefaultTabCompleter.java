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

package io.github.bktlib.command.tabcompleter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.github.bktlib.command.CommandBase;
import io.github.bktlib.command.CommandSource;

import java.util.List;

public final class DefaultTabCompleter implements TabCompleter {
  private static final List<String> EMPTY_LIST = ImmutableList.of();

  @Override
  public List<String> onTabComplete(CommandSource source, CommandBase command, String[] args) {
    return EMPTY_LIST;
  }
}
