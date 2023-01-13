package com.gonion.identity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
  private String mailFrom;
  private String mailTo;
  private String mailCc;
  private String mailBcc;
  private String mailSubject;
  private String mailContent;
}
