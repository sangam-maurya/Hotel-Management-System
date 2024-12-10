package com.example.Service;

import com.example.entity.Product;
import com.example.payload.ProductRequestDto;
import com.example.reposetry.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product updateProduct(Long id, ProductRequestDto productRequest) {
        try {
            // Fetch the existing product by ID
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));

            // Update the product fields
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setVersion(productRequest.getVersion()); // Optimistic locking version

            // Save and return the updated product
            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("Error updating product: " + e.getMessage());
        }
    }

}
