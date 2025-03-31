package gimeast.restfulapiex2.products.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gimeast.restfulapiex2.products.dto.ProductDTO;
import gimeast.restfulapiex2.products.dto.ProductListDTO;
import gimeast.restfulapiex2.products.entity.ProductEntity;
import gimeast.restfulapiex2.products.entity.QProductEntity;
import gimeast.restfulapiex2.products.entity.QProductImage;
import gimeast.restfulapiex2.products.repository.ProductSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class ProductSearchImpl implements ProductSearch {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductListDTO> list(Pageable pageable) {
        QProductEntity product = QProductEntity.productEntity;
        QProductImage productImage = QProductImage.productImage;

        List<ProductListDTO> fetch = queryFactory
                .select(Projections.bean(ProductListDTO.class,
                        product.pno,
                        product.pname,
                        product.price,
                        product.writer,
                        productImage.fileName.as("productImage"))
                )
                .from(product)
                .leftJoin(product.images, productImage)
                .on(productImage.idx.eq(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable, product))
                .fetch();

        Optional<Long> count = Optional.ofNullable(queryFactory
                .select(product.count())
                .from(product)
                .fetchOne());

        return new PageImpl<>(fetch, pageable, count.orElse(0L));
    }

    /**
     * 값 타입 컬렉션까지 모두 조회하는 경우 배치로 페이징 가능
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductEntity> entityListWithAllImagesByBatch(Pageable pageable) {
        QProductEntity product = QProductEntity.productEntity;
        QProductImage image = QProductImage.productImage;

        List<ProductEntity> fetch = queryFactory
                .select(product)
                .distinct()
                .from(product)
                .leftJoin(product.images, image)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable, product))
                .fetch();

        Optional<Long> count = Optional.ofNullable(queryFactory
                .select(product.count())
                .from(product)
                .fetchOne());

        return new PageImpl<>(fetch, pageable, count.orElse(0L));
    }

    private OrderSpecifier<?>[] getOrderSpecifier(Pageable pageable, QProductEntity product) {
        if (!pageable.getSort().isEmpty()) {
            List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

            pageable.getSort().stream().forEach(order -> {
                Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                String property = order.getProperty();

                PathBuilder pathBuilder = new PathBuilder(product.getType(), product.getMetadata().getName());
                orderSpecifiers.add(new OrderSpecifier(direction, pathBuilder.get(property)));
            });

            return orderSpecifiers.toArray(new OrderSpecifier[0]);
        }

        // 기본 정렬 조건 (필요한 경우)
        return new OrderSpecifier[] {new OrderSpecifier(Order.DESC, product.pno)};
    }

}
