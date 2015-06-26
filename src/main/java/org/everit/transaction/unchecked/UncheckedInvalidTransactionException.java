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

import javax.transaction.InvalidTransactionException;

/**
 * Wraps an {@link InvalidTransactionException} with an unchecked exception.
 */
public class UncheckedInvalidTransactionException extends RuntimeException {

  private static final long serialVersionUID = -5518257356293204217L;

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code InvalidTransactionException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedInvalidTransactionException(final InvalidTransactionException cause) {
    super(cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code InvalidTransactionException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedInvalidTransactionException(final String message,
      final InvalidTransactionException cause) {
    super(message, cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code InvalidTransactionException} which is the cause of this exception.
   */
  @Override
  public synchronized InvalidTransactionException getCause() {
    return (InvalidTransactionException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an
   *           {@code InvalidTransactionException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof InvalidTransactionException)) {
      throw new InvalidObjectException("Cause must be an InvalidTransactionException");
    }
  }

}
