package com.lvonce.lightserver.dal.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "ownerId",
    "attributeId",
    "amount"
})
public class Resource {

    @Id
    @JsonProperty("id")
    private Long id;
    @JsonProperty("ownerId")
    private Long ownerId;
    @JsonProperty("attributeId")
    private Long attributeId;
    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("ownerId")
    public Long getOwnerId() {
        return ownerId;
    }

    @JsonProperty("ownerId")
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("attributeId")
    public Long getAttributeId() {
        return attributeId;
    }

    @JsonProperty("attributeId")
    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    @JsonProperty("amount")
    public Long getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Resource.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("ownerId");
        sb.append('=');
        sb.append(((this.ownerId == null)?"<null>":this.ownerId));
        sb.append(',');
        sb.append("attributeId");
        sb.append('=');
        sb.append(((this.attributeId == null)?"<null>":this.attributeId));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.attributeId == null)? 0 :this.attributeId.hashCode()));
        result = ((result* 31)+((this.amount == null)? 0 :this.amount.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.ownerId == null)? 0 :this.ownerId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Resource) == false) {
            return false;
        }
        Resource rhs = ((Resource) other);
        return (((((this.attributeId == rhs.attributeId)||((this.attributeId!= null)&&this.attributeId.equals(rhs.attributeId)))&&((this.amount == rhs.amount)||((this.amount!= null)&&this.amount.equals(rhs.amount))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.ownerId == rhs.ownerId)||((this.ownerId!= null)&&this.ownerId.equals(rhs.ownerId))));
    }

}
