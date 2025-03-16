package manugtx.jwtexample.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.user.username = :username")
    List<Product> getProductsByUser(@Param("username") String username);

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.user.username = :username")
    Optional<Product> getProductByUser(@Param("id") Long id, @Param("username") String username);
}
