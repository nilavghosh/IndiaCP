/*
 * IndiaCP API
 * This API will drive the UI
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.barclays.indiacp.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentAccountDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-14T02:47:08.972Z")
public class PaymentAccountDetails   {
  @JsonProperty("creditorName")
  private String creditorName = null;

  @JsonProperty("bankAccountNo")
  private String bankAccountNo = null;

  @JsonProperty("bankAccountType")
  private String bankAccountType = null;

  @JsonProperty("bankName")
  private String bankName = null;

  @JsonProperty("rtgsIfscCode")
  private String rtgsIfscCode = null;

  public PaymentAccountDetails creditorName(String creditorName) {
    this.creditorName = creditorName;
    return this;
  }

   /**
   * Name in which the payment has to be made. For e.g. Barclays Investments & Loans (India) Ltd CP
   * @return creditorName
  **/
  @JsonProperty("creditorName")
  @ApiModelProperty(value = "Name in which the payment has to be made. For e.g. Barclays Investments & Loans (India) Ltd CP")
  public String getCreditorName() {
    return creditorName;
  }

  public void setCreditorName(String creditorName) {
    this.creditorName = creditorName;
  }

  public PaymentAccountDetails bankAccountNo(String bankAccountNo) {
    this.bankAccountNo = bankAccountNo;
    return this;
  }

   /**
   * Bank account no. at the IPA Bank
   * @return bankAccountNo
  **/
  @JsonProperty("bankAccountNo")
  @ApiModelProperty(value = "Bank account no. at the IPA Bank")
  public String getBankAccountNo() {
    return bankAccountNo;
  }

  public void setBankAccountNo(String bankAccountNo) {
    this.bankAccountNo = bankAccountNo;
  }

  public PaymentAccountDetails bankAccountType(String bankAccountType) {
    this.bankAccountType = bankAccountType;
    return this;
  }

   /**
   * Bank account type. For e.g. current account
   * @return bankAccountType
  **/
  @JsonProperty("bankAccountType")
  @ApiModelProperty(value = "Bank account type. For e.g. current account")
  public String getBankAccountType() {
    return bankAccountType;
  }

  public void setBankAccountType(String bankAccountType) {
    this.bankAccountType = bankAccountType;
  }

  public PaymentAccountDetails bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

   /**
   * Name of the IPA Bank
   * @return bankName
  **/
  @JsonProperty("bankName")
  @ApiModelProperty(value = "Name of the IPA Bank")
  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public PaymentAccountDetails rtgsIfscCode(String rtgsIfscCode) {
    this.rtgsIfscCode = rtgsIfscCode;
    return this;
  }

   /**
   * RTGS IFSC code of the IPA Bank to receive payments
   * @return rtgsIfscCode
  **/
  @JsonProperty("rtgsIfscCode")
  @ApiModelProperty(value = "RTGS IFSC code of the IPA Bank to receive payments")
  public String getRtgsIfscCode() {
    return rtgsIfscCode;
  }

  public void setRtgsIfscCode(String rtgsIfscCode) {
    this.rtgsIfscCode = rtgsIfscCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentAccountDetails paymentAccountDetails = (PaymentAccountDetails) o;
    return Objects.equals(this.creditorName, paymentAccountDetails.creditorName) &&
        Objects.equals(this.bankAccountNo, paymentAccountDetails.bankAccountNo) &&
        Objects.equals(this.bankAccountType, paymentAccountDetails.bankAccountType) &&
        Objects.equals(this.bankName, paymentAccountDetails.bankName) &&
        Objects.equals(this.rtgsIfscCode, paymentAccountDetails.rtgsIfscCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditorName, bankAccountNo, bankAccountType, bankName, rtgsIfscCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentAccountDetails {\n");
    
    sb.append("    creditorName: ").append(toIndentedString(creditorName)).append("\n");
    sb.append("    bankAccountNo: ").append(toIndentedString(bankAccountNo)).append("\n");
    sb.append("    bankAccountType: ").append(toIndentedString(bankAccountType)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    rtgsIfscCode: ").append(toIndentedString(rtgsIfscCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

