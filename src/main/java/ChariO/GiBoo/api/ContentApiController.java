package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Contents;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ContentApiController {


    @Data
    static class ContentDto{
        private String c_title;
        private String c_contents;
        private String c_image;
        private String c_url;

        @DateTimeFormat(pattern = "MM/dd/yyyy")
        private Date c_pub_date;

        public ContentDto(Contents content){
            this.c_title = content.getC_title();
            this.c_contents = content.getC_contents();
            this.c_image = content.getC_image();
            this.c_pub_date = content.getC_pub_date();
            this.c_url = content.getC_url();
        }
    }
}
