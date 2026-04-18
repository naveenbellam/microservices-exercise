package product_service.product_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import product_service.product_service.Entity.ProductEntity;
import product_service.product_service.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductEntity product) {
        return productService.addProduct(product);
    }

    // GET ALL
    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable String id,
                                       @RequestBody ProductEntity product) {
        return productService.updateProduct(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/paged")
    public Page<ProductEntity> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return productService.getProducts(page, size, sortBy);
    }

    @GetMapping("/filter")
    public List<ProductEntity> getFilteredProducts(
            @RequestParam double minPrice) {

        return productService.getFilteredProducts(minPrice);
    }

    @GetMapping("/names")
    public List<String> getProductNames() {
        return productService.getProductNames();
    }

    @GetMapping("/expensive")
    public List<ProductEntity> getExpensiveProducts(@RequestParam Double price) {
        return productService.getExpensiveProducts(price);
    }
}