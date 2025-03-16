package manugtx.jwtexample.product;

import jakarta.validation.Valid;
import manugtx.jwtexample.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO, @AuthenticationPrincipal UserDetails userDetails) {
        ProductDTO createdProduct = productService.createProduct(productDTO, userDetails.getUsername());
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProductsForUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<ProductDTO> products = productService.getProductsForUser(userDetails.getUsername());
        return ResponseEntity.ok(products);
    }
}
