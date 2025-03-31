package gimeast.restfulapiex2.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;        // 데이터 목록
    private int currentPage;        // 현재 페이지
    private int pageSize;           // 페이지당 아이템 수
    private long totalItems;        // 전체 아이템 개수
    private int totalPages;         // 전체 페이지 수
}
