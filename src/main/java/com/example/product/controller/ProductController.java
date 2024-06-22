package com.example.product.controller;

import com.example.product.dto.CreateProductRequest;
import com.example.product.dto.UpdateProductRequest;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public Integer createProduct(@RequestBody CreateProductRequest request){
        return this.productService.create(request);
    }

    //how can I take list of products into a dto and how to convert it
    @PostMapping("/list-of-products")
    public List<Product> createProducts(@RequestBody List<Product> products){
        return this.productService.createProducts(products);
    }

    @GetMapping("/productId/{id}")
    public Product getProductDetailsById(@PathVariable("id") Integer id){
        return this.productService.getProductDetailsById(id);
    }

    @GetMapping("/{name}")
    public Product getProductDetailsByName(@PathVariable("name") String name){
        return this.productService.getProductDetailsByName(name);
    }

    @GetMapping("/all")
    public List<Product> getProdList(){
        return this.productService.getProdList();
    }
    /**
     * get list of products
     * post list of products
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Integer id,@RequestBody UpdateProductRequest request){
        return this.productService.update(id,request);
    }

    @PatchMapping("")
    public Product updateProductWithPartialDetails(@RequestParam("id") Integer id,@RequestBody UpdateProductRequest request){
        return this.productService.updateWithMerge(id,request);
    }

    @DeleteMapping("")
    public String deleteProduct(@RequestParam("id") Integer id){
        return this.productService.deleteProduct(id);
    }
}
