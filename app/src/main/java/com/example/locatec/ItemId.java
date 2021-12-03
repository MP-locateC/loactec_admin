package com.example.locatec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ItemId {
  Long productId;

  public ItemId(Long productId) {
    this.productId = productId;
  }
}
