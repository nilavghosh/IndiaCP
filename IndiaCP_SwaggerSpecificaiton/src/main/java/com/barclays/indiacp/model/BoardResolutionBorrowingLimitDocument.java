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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * BoardResolutionBorrowingLimitDocument
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-06T23:16:51.945Z")
public class BoardResolutionBorrowingLimitDocument   {
  @JsonProperty("legalEntityId")
  private String legalEntityId = null;

  @JsonProperty("boardResolutionBorrowingLimit")
  private Integer boardResolutionBorrowingLimit = null;

  @JsonProperty("boardResolutionIssuanceDate")
  private Date boardResolutionIssuanceDate = null;

  @JsonProperty("boardResolutionExpiryDate")
  private Date boardResolutionExpiryDate = null;

  @JsonProperty("modifiedBy")
  private String modifiedBy = null;

  @JsonProperty("lastModifiedDate")
  private Date lastModifiedDate = null;

  public BoardResolutionBorrowingLimitDocument legalEntityId(String legalEntityId) {
    this.legalEntityId = legalEntityId;
    return this;
  }

   /**
   * Unique identifier of the Legal Entity that this document is issued by
   * @return legalEntityId
  **/
  @ApiModelProperty(value = "Unique identifier of the Legal Entity that this document is issued by")
  public String getLegalEntityId() {
    return legalEntityId;
  }

  public void setLegalEntityId(String legalEntityId) {
    this.legalEntityId = legalEntityId;
  }

  public BoardResolutionBorrowingLimitDocument boardResolutionBorrowingLimit(Integer boardResolutionBorrowingLimit) {
    this.boardResolutionBorrowingLimit = boardResolutionBorrowingLimit;
    return this;
  }

   /**
   * Borrowing Limit approved by board
   * @return boardResolutionBorrowingLimit
  **/
  @ApiModelProperty(value = "Borrowing Limit approved by board")
  public Integer getBoardResolutionBorrowingLimit() {
    return boardResolutionBorrowingLimit;
  }

  public void setBoardResolutionBorrowingLimit(Integer boardResolutionBorrowingLimit) {
    this.boardResolutionBorrowingLimit = boardResolutionBorrowingLimit;
  }

  public BoardResolutionBorrowingLimitDocument boardResolutionIssuanceDate(Date boardResolutionIssuanceDate) {
    this.boardResolutionIssuanceDate = boardResolutionIssuanceDate;
    return this;
  }

   /**
   * Date on which the BR was issued
   * @return boardResolutionIssuanceDate
  **/
  @ApiModelProperty(value = "Date on which the BR was issued")
  public Date getBoardResolutionIssuanceDate() {
    return boardResolutionIssuanceDate;
  }

  public void setBoardResolutionIssuanceDate(Date boardResolutionIssuanceDate) {
    this.boardResolutionIssuanceDate = boardResolutionIssuanceDate;
  }

  public BoardResolutionBorrowingLimitDocument boardResolutionExpiryDate(Date boardResolutionExpiryDate) {
    this.boardResolutionExpiryDate = boardResolutionExpiryDate;
    return this;
  }

   /**
   * Date on which the BR will become obsolete
   * @return boardResolutionExpiryDate
  **/
  @ApiModelProperty(value = "Date on which the BR will become obsolete")
  public Date getBoardResolutionExpiryDate() {
    return boardResolutionExpiryDate;
  }

  public void setBoardResolutionExpiryDate(Date boardResolutionExpiryDate) {
    this.boardResolutionExpiryDate = boardResolutionExpiryDate;
  }

  public BoardResolutionBorrowingLimitDocument modifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
    return this;
  }

   /**
   * Unique identifier of the Logged-in User that performed the action. This is required for Audit History
   * @return modifiedBy
  **/
  @ApiModelProperty(value = "Unique identifier of the Logged-in User that performed the action. This is required for Audit History")
  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public BoardResolutionBorrowingLimitDocument lastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

   /**
   * Last Modified Date for this BR upload. This is required for Audit History
   * @return lastModifiedDate
  **/
  @ApiModelProperty(value = "Last Modified Date for this BR upload. This is required for Audit History")
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardResolutionBorrowingLimitDocument boardResolutionBorrowingLimitDocument = (BoardResolutionBorrowingLimitDocument) o;
    return Objects.equals(this.legalEntityId, boardResolutionBorrowingLimitDocument.legalEntityId) &&
        Objects.equals(this.boardResolutionBorrowingLimit, boardResolutionBorrowingLimitDocument.boardResolutionBorrowingLimit) &&
        Objects.equals(this.boardResolutionIssuanceDate, boardResolutionBorrowingLimitDocument.boardResolutionIssuanceDate) &&
        Objects.equals(this.boardResolutionExpiryDate, boardResolutionBorrowingLimitDocument.boardResolutionExpiryDate) &&
        Objects.equals(this.modifiedBy, boardResolutionBorrowingLimitDocument.modifiedBy) &&
        Objects.equals(this.lastModifiedDate, boardResolutionBorrowingLimitDocument.lastModifiedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(legalEntityId, boardResolutionBorrowingLimit, boardResolutionIssuanceDate, boardResolutionExpiryDate, modifiedBy, lastModifiedDate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoardResolutionBorrowingLimitDocument {\n");
    
    sb.append("    legalEntityId: ").append(toIndentedString(legalEntityId)).append("\n");
    sb.append("    boardResolutionBorrowingLimit: ").append(toIndentedString(boardResolutionBorrowingLimit)).append("\n");
    sb.append("    boardResolutionIssuanceDate: ").append(toIndentedString(boardResolutionIssuanceDate)).append("\n");
    sb.append("    boardResolutionExpiryDate: ").append(toIndentedString(boardResolutionExpiryDate)).append("\n");
    sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
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

