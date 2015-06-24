package org.everit.transaction.unchecked;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

import javax.transaction.HeuristicMixedException;

/**
 * Wraps an {@link HeuristicMixedException} with an unchecked exception.
 */
public abstract class UncheckedHeuristicMixedException extends RuntimeException {

  private static final long serialVersionUID = -8590576876339348244L;

  /**
   * Constructs an instance of this class.
   *
   * @param cause
   *          the {@code HeuristicMixedException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicMixedException(final HeuristicMixedException cause) {
    super(cause);
  }

  /**
   * Constructs an instance of this class.
   *
   * @param message
   *          the detail message, can be null
   * @param cause
   *          the {@code HeuristicMixedException}
   *
   * @throws NullPointerException
   *           if the cause is {@code null}
   */
  public UncheckedHeuristicMixedException(final String message,
      final HeuristicMixedException cause) {
    super(message, cause);
  }

  /**
   * Returns the cause of this exception.
   *
   * @return the {@code HeuristicMixedException} which is the cause of this exception.
   */
  @Override
  public synchronized HeuristicMixedException getCause() {
    return (HeuristicMixedException) super.getCause();
  }

  /**
   * Called to read the object from a stream.
   *
   * @throws InvalidObjectException
   *           if the object is invalid or has a cause that is not an
   *           {@code HeuristicMixedException}
   */
  private void readObject(final ObjectInputStream s)
      throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    Throwable cause = super.getCause();
    if (!(cause instanceof HeuristicMixedException)) {
      throw new InvalidObjectException("Cause must be an HeuristicMixedException");
    }
  }

}
