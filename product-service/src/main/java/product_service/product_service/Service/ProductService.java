package product_service.product_service.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import product_service.product_service.Entity.ProductEntity;
import product_service.product_service.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository products;
    //post

    public void addProduct(ProductEntity product){
        products.save(product);
    }
    //get
    public  List<ProductEntity> getAllProducts(){
        return  products.findAll();
    }
    //put
    public  void updateProduct(ProductEntity product){
        ProductEntity product1 =products.findById(product.getId()).orElse(null);
        if(product1!=null && product1.getId()==product.getId() ) {
            products.delete(product1);
            products.save(product);
        }
    }
    //Delete
    public  void deleteProduct(ProductEntity product){
        products.delete(product);
    }

    // Validate stock
    public  Boolean validateStock(String productId){
        List<ProductEntity> pro = products.findAll();
        for(ProductEntity p : pro){
            if(p.getId() == productId && p.getStock()>0)
                return true;
        }
        return false;
    }

}