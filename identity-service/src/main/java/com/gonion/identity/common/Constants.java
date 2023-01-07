package com.gonion.identity.common;

public class Constants {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String BEARER_PREFIX = "Bearer ";
  // This key is generated at allkeysgenerator.com
  public static final String SECRET_KEY =
      "4E645267556B58703273357538782F413F4428472B4B6250655368566D597133";
  public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

  public static final String PASSWORD_PATTERN =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\w\\W]*$";
}
