package gimeast.restfulapiex2.products.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "tbl_products")
@Getter
@ToString(exclude = "images")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private String content;

    private String writer;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ElementCollection(fetch = FetchType.LAZY) //@ElementCollection = 컬렉션 요소 추가시 전체컬렉션을 교체하는 방식 사용(간편하나 성능에 좋지않음=> 해결방법: 독립적인 엔티티로 관리)
    @CollectionTable(name = "tbl_product_images", joinColumns = @JoinColumn(name = "pno"))
    @Builder.Default
    @BatchSize(size = 30)
    private SortedSet<ProductImage> images = new TreeSet<>();

    public void addImage(String fileName) {
        ProductImage productImage = ProductImage.builder()
                .fileName(fileName)
                .idx(images.size())
                .build();
        images.add(productImage);
    }

    public void clearImages() {
        images.clear();
    }

    public void changeTitle(String title) {
        this.pname = title;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    private void changeContent(String content) {
        this.content = content;
    }

}
