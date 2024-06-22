package com.example.product.dto;

import com.example.product.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank
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
