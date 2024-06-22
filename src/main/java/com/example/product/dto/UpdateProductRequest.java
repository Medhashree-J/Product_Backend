package com.example.product.dto;

import com.example.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {
    private String name;

    private Integer quantity;

    private Double price;

    public Product mapToProduct(){
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
