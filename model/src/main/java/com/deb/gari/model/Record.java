package com.deb.gari.model;

/**
 * @author Debender Prasad
 */

public interface Record {

    String getAccountNumber();

    String getDescription();

    String getStartBalance();

    String getMutation();

    String getEndBalance();

    String getReference();

}
