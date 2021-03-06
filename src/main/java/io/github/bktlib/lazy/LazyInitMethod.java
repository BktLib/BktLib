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

package io.github.bktlib.lazy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class LazyInitMethod extends LazyInitValue<Method> {

  private Class<?>[] methodParamsType;
  private String methodName;
  private Class<?> clazz;

  /* LazyInit stuffs */
  private String clazzName;
  private String[] methodParamsClassNames;

  public LazyInitMethod(@Nonnull  Class<?> clazz, @Nonnull String methodName, Class<?> ... methodParamsType) {
    this.methodParamsType = methodParamsType;
    this.methodName = methodName;
    this.clazz = clazz;
  }

  public LazyInitMethod(@Nonnull String clazzName, @Nonnull String methodName, Class<?> ... methodParamsType) {
    this.methodParamsType = methodParamsType;
    this.clazzName = clazzName;
    this.methodName = methodName;
  }

  public LazyInitMethod(@Nonnull String clazzName, @Nonnull String methodName, String ... methodParamsType) {
    this.methodParamsClassNames = methodParamsType;
    this.clazzName = clazzName;
    this.methodName = methodName;
  }

  @Nullable
  @Override
  protected Method init() {
    if (clazz == null) {
      try {
        clazz = Class.forName(clazzName);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    if (methodParamsType == null) {
      methodParamsType = Stream.of(methodParamsClassNames)
          .map(name -> {
            try {
              return Class.forName(name);
            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            }
            return null;
          }).toArray(Class<?>[]::new);
    }
    try {
      Method ret = clazz.getDeclaredMethod(methodName, methodParamsType);
      ret.setAccessible(true); /* TODO: Abstrair isso ? Usando decorator ou não... */
      return ret;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return null;
  }
}
