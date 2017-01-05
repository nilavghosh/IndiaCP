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
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.barclays.indiacp.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * SettlementDetails
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-05T14:40:19.760Z")
public class SettlementDetails   {
  /**
   * partyType
   */
  public enum PartyTypeEnum {
    ISSUER("ISSUER"),
    
    INVESTOR("INVESTOR"),
    
    IPA("IPA");

    private String value;

    PartyTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PartyTypeEnum fromValue(String text) {
      for (PartyTypeEnum b : PartyTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("partyType")
  private PartyTypeEnum partyType = null;

  @JsonProperty("paymentAccountDetails")
  private PaymentAccountDetails paymentAccountDetails = null;

  @JsonProperty("depositoryAccountDetails")
  private List<DepositoryAccountDetails> depositoryAccountDetails = new ArrayList<DepositoryAccountDetails>();

  public SettlementDetails partyType(PartyTypeEnum partyType) {
    this.partyType = partyType;
    return this;
  }

   /**
   * partyType
   * @return partyType
  **/
  @ApiModelProperty(value = "partyType")
  public PartyTypeEnum getPartyType() {
    return partyType;
  }

  public void setPartyType(PartyTypeEnum partyType) {
    this.partyType = partyType;
  }

  public SettlementDetails paymentAccountDetails(PaymentAccountDetails paymentAccountDetails) {
    this.paymentAccountDetails = paymentAccountDetails;
    return this;
  }

   /**
   * Get paymentAccountDetails
   * @return paymentAccountDetails
  **/
  @ApiModelProperty(value = "")
  public PaymentAccountDetails getPaymentAccountDetails() {
    return paymentAccountDetails;
  }

  public void setPaymentAccountDetails(PaymentAccountDetails paymentAccountDetails) {
    this.paymentAccountDetails = paymentAccountDetails;
  }

  public SettlementDetails depositoryAccountDetails(List<DepositoryAccountDetails> depositoryAccountDetails) {
    this.depositoryAccountDetails = depositoryAccountDetails;
    return this;
  }

  public SettlementDetails addDepositoryAccountDetailsItem(DepositoryAccountDetails depositoryAccountDetailsItem) {
    this.depositoryAccountDetails.add(depositoryAccountDetailsItem);
    return this;
  }

   /**
   * Get depositoryAccountDetails
   * @return depositoryAccountDetails
  **/
  @ApiModelProperty(value = "")
  public List<DepositoryAccountDetails> getDepositoryAccountDetails() {
    return depositoryAccountDetails;
  }

  public void setDepositoryAccountDetails(List<DepositoryAccountDetails> depositoryAccountDetails) {
    this.depositoryAccountDetails = depositoryAccountDetails;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettlementDetails settlementDetails = (SettlementDetails) o;
    return Objects.equals(this.partyType, settlementDetails.partyType) &&
        Objects.equals(this.paymentAccountDetails, settlementDetails.paymentAccountDetails) &&
        Objects.equals(this.depositoryAccountDetails, settlementDetails.depositoryAccountDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyType, paymentAccountDetails, depositoryAccountDetails);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettlementDetails {\n");
    
    sb.append("    partyType: ").append(toIndentedString(partyType)).append("\n");
    sb.append("    paymentAccountDetails: ").append(toIndentedString(paymentAccountDetails)).append("\n");
    sb.append("    depositoryAccountDetails: ").append(toIndentedString(depositoryAccountDetails)).append("\n");
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

