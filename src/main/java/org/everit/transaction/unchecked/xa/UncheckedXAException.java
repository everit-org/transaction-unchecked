package org.everit.transaction.unchecked.xa;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

import javax.transaction.xa.XAException;

/**
 * Wraps an {@link XAException} with an unchecked exception.
 */
public abstract class UncheckedXAException extends RuntimeException {

  private static final long serialVersionUID = -8590576876339348244L;

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code XAException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedXAException(final String message, final XAException cause) {
    super(message, cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code XAException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedXAException(final XAException cause) {
    super(cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code XAException} which is the cause of this exception.
   */
  @Override
  public synchronized XAException getCause() {
    return (XAException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an {@code XAException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof XAException)) {
      throw new InvalidObjectException("Cause must be an XAException");
    }
  }

}
