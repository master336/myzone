package com.web.core.utility;

public class IdsException extends RuntimeException
{
  private static final long serialVersionUID = -2908870392455830951L;

  public IdsException()
  {
  }

  public IdsException(String message)
  {
    super(message);
  }

  public IdsException(String message, Throwable cause) {
    super(message, cause);
  }

  public IdsException(Throwable cause) {
    super(cause);
  }
}
