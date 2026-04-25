package product_service.product_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import product_service.product_service.Entity.ProductEntity;
import product_service.product_service.Repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity addProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity updateProduct(String id, ProductEntity product) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return productRepository.save(existing);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public boolean validateStock(String productId) {
        return productRepository.findById(productId)
                .map(p -> p.getStock() != null && p.getStock() > 0)
                .orElse(false);
    }

    public Page<ProductEntity> getProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageable);
    }

    public List<ProductEntity> getFilteredProducts(double minPrice) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getPrice() > minPrice)
                .collect(Collectors.toList());
    }

    public List<String> getProductNames() {
        return productRepository.findAll()
                .stream()
                .map(ProductEntity::getName)
                .collect(Collectors.toList());
    }

    public List<ProductEntity> getExpensiveProducts(Double price) {
        return productRepository.findExpensiveProducts(price);
    }

    public ProductEntity getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}