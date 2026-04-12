package product_service.product_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product_service.product_service.Entity.ProductEntity;
import product_service.product_service.Service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @PostMapping("/addProducts")
    public void addProduct(@RequestBody ProductEntity product)
    {
        productService.addProduct(product);
    }
    //put
    @PutMapping("/update")
    public  void updateProduct(@RequestBody ProductEntity product){
        productService.updateProduct(product);
    }

    // deletee
    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody ProductEntity product){
        productService.deleteProduct(product);
    }

}