package ChariO.GiBoo.dto;
import ChariO.GiBoo.domain.Category;
import lombok.Data;

public class CateDtos {

    @Data
    public static class CateDto{
        private Long id;
        private String name;

        public CateDto(Category category) {
            this.id = category.getId();
            this.name = category.getCate_name();
        }
    }
}
