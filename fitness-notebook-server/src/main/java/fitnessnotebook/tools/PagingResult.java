package fitnessnotebook.tools;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagingResult<T> {

    public PagingResult(Page<T> page) {
        this.pageInfo = new PageInfo(
            page.getNumber() + 1, page.getSize(), page.getTotalElements());
        this.result = page.getContent();
    }
    private PageInfo pageInfo;
    private List<T> result;

    public static class PageInfo {
        public PageInfo(Integer page, Integer size, Long total) {
            this.page = page;
            this.size = size;
            this.total = total;
        }

        private Integer page;
        private Integer size;
        private Long total;

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public Long getTotal() {
            return total;
        }
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<T> getResult() {
        return result;
    }
}