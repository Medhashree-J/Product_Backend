package com.example.product.service;

import com.example.product.dto.CreateProductRequest;
import com.example.product.dto.UpdateProductRequest;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private ObjectMapper mapper = new ObjectMapper();
    public Integer create(CreateProductRequest request) {
        Product product = request.mapToProduct();
        this.productRepository.save(product);
        return product.getId();
    }

    public List<Product> createProducts(List<Product> products) {
        return this.productRepository.saveAll(products);
    }
    public Product getProductDetailsById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product getProductDetailsByName(String name) {
        return this.productRepository.findByName(name);
    }
    public List<Product> getProdList() {
        return this.productRepository.findAll();
    }

    public Product update(Integer id, UpdateProductRequest request) {
        Product savedProduct = this.getProductDetailsById(id);
        Product incomingProduct = request.mapToProduct();
       savedProduct.setName(incomingProduct.getName());
       savedProduct.setPrice(incomingProduct.getPrice());
       savedProduct.setQuantity(incomingProduct.getQuantity());
       savedProduct.setUpdatedOn(incomingProduct.getUpdatedOn());
       this.productRepository.save(savedProduct);
       return savedProduct;
    }

    public Product updateWithMerge(Integer id, UpdateProductRequest request) {
        Product sProduct = this.getProductDetailsById(id);

        Product iProduct = request.mapToProduct();

        Product mergedProduct = this.merge(iProduct, sProduct);
        productRepository.save(mergedProduct);
        return mergedProduct;
    }

//    private Product merge(Product incomingProduct, Product savedProduct) {
//        if (incomingProduct.getName() != null) {
//            savedProduct.setName(incomingProduct.getName());
//        }
//        if (incomingProduct.getPrice() != null) {
//            savedProduct.setPrice(incomingProduct.getPrice());
//        }
//        if (incomingProduct.getQuantity() != null) {
//            savedProduct.setQuantity(incomingProduct.getQuantity());
//        }
//        // Add checks and setters for other fields as needed.
//
//        return savedProduct;
//    }

    private Product merge(Product incomingProduct, Product savedProduct) {
        JSONObject incoming = mapper.convertValue(incomingProduct, JSONObject.class);
        JSONObject saved = mapper.convertValue(savedProduct, JSONObject.class);

        Iterator it = incoming.keySet().iterator();  // id, name, email, mobile .....
        while(it.hasNext()){
            String key = (String)it.next();
            if(incoming.get(key) != null) {
                saved.put(key, incoming.get(key));
            }
        }

        return mapper.convertValue(saved, Product.class);
    }


    public String deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
        return "Product with id - " + id +" deleted";
    }



}
