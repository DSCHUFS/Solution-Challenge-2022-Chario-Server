package ChariO.GiBoo.dto;

import ChariO.GiBoo.domain.Contents;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConDtos {

    @Data
    public static class ContentDto {
        private Long f_id;
        private Long c_id;
        private String title;
        private String body;
        private String image;
        private String url;

        @DateTimeFormat(pattern = "MM/dd/yyyy")
        private Date pub_date;

        public ContentDto(Contents content) {
            this.f_id = content.getFacility().getId();
            this.c_id = content.getId();
            this.title = content.getC_title();
            this.body = content.getC_contents();
            this.image = content.getC_image();
            this.pub_date = content.getC_pub_date();
            this.url = content.getC_url();
        }
    }
}
