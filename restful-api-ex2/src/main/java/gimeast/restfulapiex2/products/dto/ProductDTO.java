package gimeast.restfulapiex2.products.dto;

import gimeast.restfulapiex2.products.entity.ProductEntity;
import gimeast.restfulapiex2.products.entity.ProductImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.SortedSet;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long pno;
    private String pname;
    private int price;
    private String content;
    private String writer;
    private List<String> imageList;

    public ProductDTO(ProductEntity productEntity) {
        this.pno = productEntity.getPno();
        this.pname = productEntity.getPname();
        this.price = productEntity.getPrice();
        this.content = productEntity.getContent();
        this.writer = productEntity.getWriter();
        this.imageList = productEntity.getImages().stream()
                .map(ProductImage::getFileName)
                .toList();
    }
}
