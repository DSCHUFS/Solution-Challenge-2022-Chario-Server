package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Contents;
import ChariO.GiBoo.service.ConService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static ChariO.GiBoo.dto.ConDtos.*;

@RestController
@RequiredArgsConstructor
public class ConApiController {

    private final ConService conService;

    @Operation(summary = "컨텐츠 단일 조회", description = "컨텐츠의 title, body, image_url, url, pub_date 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/content/{id}/", produces = "application/json;charset=UTF-8")
    public ContentDto oneContent(@PathVariable("id") Long con_id) {
        Contents content = conService.findOne(con_id);
        return new ContentDto(content);
    }
}
