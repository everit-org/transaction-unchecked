package org.everit.transaction.unchecked;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

import javax.transaction.HeuristicCommitException;

/**
 * Wraps an {@link HeuristicCommitException} with an unchecked exception.
 */
public abstract class UncheckedHeuristicCommitException extends RuntimeException {

  private static final long serialVersionUID = -8590576876339348244L;

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code HeuristicCommitException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicCommitException(final HeuristicCommitException cause) {
    super(cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code HeuristicCommitException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicCommitException(final String message,
      final HeuristicCommitException cause) {
    super(message, cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code HeuristicCommitException} which is the cause of this exception.
   */
  @Override
  public synchronized HeuristicCommitException getCause() {
    return (HeuristicCommitException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an
   *           {@code HeuristicCommitException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof HeuristicCommitException)) {
      throw new InvalidObjectException("Cause must be an HeuristicCommitException");
    }
  }

}
