package com.imti;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Account {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  @JsonProperty("account_number")
  private long accountNumber;
  @JsonProperty("balance")
  private long balance;
  @JsonProperty("firstname")
  private String firstname;
  @JsonProperty("lastname")
  private String lastname;
  @JsonProperty("age")
  private int age;
  @JsonProperty("gender")
  private String gender;
  @JsonProperty("address")
  private String address;
  @JsonProperty("employer")
  private String employer;
  @JsonProperty("email")
  private String email;
  @JsonProperty("city")
  private String city;
  @JsonProperty("state")
  private String state;


  public static Map<String, Object> getAsMap(final Account account) {
    return OBJECT_MAPPER.convertValue(account, new TypeReference<Map<String, Object>>() {
    });
  }


  public long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(final long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(final long balance) {
    this.balance = balance;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(final String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(final String lastname) {
    this.lastname = lastname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(final String gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getEmployer() {
    return employer;
  }

  public void setEmployer(final String employer) {
    this.employer = employer;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(final String state) {
    this.state = state;
  }
}
