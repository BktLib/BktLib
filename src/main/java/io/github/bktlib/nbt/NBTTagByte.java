package io.github.bktlib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase.NBTPrimitive {

  private byte data;

  NBTTagByte() {}

  public NBTTagByte(byte data) {
    this.data = data;
  }

  void write(DataOutput output) throws IOException {
    output.writeByte(this.data);
  }

  void read(DataInput input, int depth, NBTReadLimiter readLimiter) throws IOException {
    readLimiter.read(8L);
    this.data = input.readByte();
  }

  public byte getId() {
    return (byte) 1;
  }

  public String toString() {
    return "" + this.data + "b";
  }

  public NBTBase copy() {
    return new NBTTagByte(this.data);
  }

  public boolean equals(Object obj) {
    if (super.equals(obj)) {
      NBTTagByte tagByte = (NBTTagByte) obj;
      return this.data == tagByte.data;
    } else {
      return false;
    }
  }

  public int hashCode() {
    return super.hashCode() ^ this.data;
  }

  public long getLong() {
    return (long) this.data;
  }

  public int getInt() {
    return this.data;
  }

  public short getShort() {
    return (short) this.data;
  }

  public byte getByte() {
    return this.data;
  }

  public double getDouble() {
    return (double) this.data;
  }

  public float getFloat() {
    return (float) this.data;
  }
}
