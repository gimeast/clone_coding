package gimeast.restfulapiex2.products.repository;

import gimeast.restfulapiex2.common.dto.PageRequestDTO;
import gimeast.restfulapiex2.products.dto.ProductDTO;
import gimeast.restfulapiex2.products.dto.ProductListDTO;
import gimeast.restfulapiex2.products.entity.ProductEntity;
import gimeast.restfulapiex2.products.entity.ProductImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.IntStream;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Commit
    void testInsert() {
        IntStream.rangeClosed(1, 50)
                .mapToObj(i -> ProductEntity.builder()
                        .pname(i + "_새로운 상품")
                        .price(5000)
                        .content(i + "_상품 설명")
                        .writer("user00")
                        .build())
                .forEach(productEntity -> productRepository.save(productEntity));
    }

    @Test
    @Transactional(readOnly = true)
    void testRead() {
        //given
        Long pno = 1L;
        //when
        Optional<ProductEntity> result = productRepository.findById(pno);
        ProductEntity productEntity = result.get();
        //then
        System.out.println("productEntity = " + productEntity);
        SortedSet<ProductImage> images = productEntity.getImages();
        System.out.println("images = " + images);
        SortedSet<ProductImage> images2 = productEntity.getImages();
        System.out.println("images2 = " + images2);
    }

    @Test
    @Transactional(readOnly = true)
    void testReadQuery() {
        //given
        Long pno = 1L;
        //when
        Optional<ProductEntity> result = productRepository.getProduct(pno);
        ProductEntity productEntity = result.get();
        //then
        System.out.println("productEntity = " + productEntity);
    }

    @Test
    @Transactional
    @Commit
    void testUpdate() {
        //given
        Long pno = 3L;
        Optional<ProductEntity> productResult = productRepository.getProduct(pno);
        ProductEntity product = productResult.get();
        //when
        product.changeTitle("변경된 상품");
        product.changePrice(30000);
        //then
        product.addImage("짱구3.jpg");
        product.addImage("짱구4.jpg");

        SortedSet<ProductImage> images = product.getImages();
        for (ProductImage image : images) {
            System.out.println("image = " + image);
        }
    }

    @Test
    @Transactional(readOnly = true)
    void testReadDTO() {
        //given
        Long pno = 1L;
        //when
        Optional<ProductDTO> productDTOResult = productRepository.getProductDTO(pno);
        ProductDTO productDTO = productDTOResult.get();
        //then
        System.out.println("productDTO = " + productDTO);
    }

    @Test
    @Transactional(readOnly = true)
    void testReadListDTO() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(5, 10);
        Sort sort = Sort.by("pno").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);

        Page<ProductListDTO> list = productRepository.list(pageable);
        for (ProductListDTO productListDTO : list) {
            System.out.println("productListDTO = " + productListDTO);
        }
    }

    @Test
    @Transactional(readOnly = true)
    void testReadBatchListEntity() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(5, 10);
        Sort sort = Sort.by("pno").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);
        System.out.println("pageable.getPageNumber() = " + pageable.getPageNumber());
        Page<ProductEntity> result = productRepository.entityListWithAllImagesByBatch(pageable);
        for (ProductEntity entity : result) {
            System.out.println("entity = " + entity);
        }
    }
}