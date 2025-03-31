package gimeast.restfulapiex2.products.repository;

import gimeast.restfulapiex2.products.dto.ProductDTO;
import gimeast.restfulapiex2.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductSearch {

//    @EntityGraph(attributePaths = {"images"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("select p from ProductEntity p where p.pno = :pno")
//    Optional<ProductEntity> getProduct(@Param("pno") Long pno);
    @Query("select p from ProductEntity p left join fetch p.images pi where p.pno = :pno")
    Optional<ProductEntity> getProduct(@Param("pno") Long pno);

    @Query("select p from ProductEntity p left join fetch p.images pi where p.pno = :pno")
    Optional<ProductDTO> getProductDTO(@Param("pno") Long pno);
}
