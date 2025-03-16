package manugtx.jwtexample.product;

import manugtx.jwtexample.dto.ProductDTO;
import manugtx.jwtexample.user.User;
import manugtx.jwtexample.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public ProductDTO createProduct(ProductDTO productDTO, String username) {
        User user = userService.getUserByUsername(username);
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setUser(user);
        product = productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }

    public List<ProductDTO> getProductsForUser(String username) {
        return productRepository.getProductsByUser(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }
}
