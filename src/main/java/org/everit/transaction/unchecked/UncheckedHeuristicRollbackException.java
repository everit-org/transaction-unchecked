package org.everit.transaction.unchecked;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

import javax.transaction.HeuristicRollbackException;

/**
 * Wraps an {@link HeuristicRollbackException} with an unchecked exception.
 */
public abstract class UncheckedHeuristicRollbackException extends RuntimeException {

  private static final long serialVersionUID = -8590576876339348244L;

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code HeuristicRollbackException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicRollbackException(final HeuristicRollbackException cause) {
    super(cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code HeuristicRollbackException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicRollbackException(final String message,
      final HeuristicRollbackException cause) {
    super(message, cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code HeuristicRollbackException} which is the cause of this exception.
   */
  @Override
  public synchronized HeuristicRollbackException getCause() {
    return (HeuristicRollbackException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an
   *           {@code HeuristicRollbackException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof HeuristicRollbackException)) {
      throw new InvalidObjectException("Cause must be an HeuristicRollbackException");
    }
  }

}
