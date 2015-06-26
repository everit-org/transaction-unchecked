/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.transaction.unchecked;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

import javax.transaction.NotSupportedException;

/**
 * Wraps an {@link NotSupportedException} with an unchecked exception.
 */
public class UncheckedNotSupportedException extends RuntimeException {

  private static final long serialVersionUID = 5495939523177836180L;

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code NotSupportedException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedNotSupportedException(final NotSupportedException cause) {
    super(cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code NotSupportedException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedNotSupportedException(final String message,
      final NotSupportedException cause) {
    super(message, cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code NotSupportedException} which is the cause of this exception.
   */
  @Override
  public synchronized NotSupportedException getCause() {
    return (NotSupportedException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an {@code NotSupportedException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof NotSupportedException)) {
      throw new InvalidObjectException("Cause must be an NotSupportedException");
    }
  }

}
