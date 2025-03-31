package gimeast.restfulapiex2.products.repository;

import gimeast.restfulapiex2.products.dto.ProductListDTO;
import gimeast.restfulapiex2.products.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {
    Page<ProductListDTO> list(Pageable pageable);
    Page<ProductEntity> entityListWithAllImagesByBatch(Pageable pageable);


}
