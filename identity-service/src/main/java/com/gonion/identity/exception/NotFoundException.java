package com.gonion.identity.exception;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;

public class NotFoundException extends RuntimeCryptoException {
  public NotFoundException(String message) {
    super(message);
  }
}
