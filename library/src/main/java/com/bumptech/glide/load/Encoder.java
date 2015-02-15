package com.bumptech.glide.load;

import java.io.OutputStream;
import java.util.Map;

/**
 * An interface for writing data to some persistent data store (i.e. a local File cache).
 *
 * @param <T> The type of the data that will be written.
 */
public interface Encoder<T> {

  /**
   * Writes the given data to the given output stream and returns True if the write completed
   * successfully and should be committed.
   *
   * @param data    The data to write.
   * @param os      The OutputStream to write the data to.
   * @param options The set of options to apply when encoding.
   */
  boolean encode(T data, OutputStream os, Map<String, Object> options);
}
