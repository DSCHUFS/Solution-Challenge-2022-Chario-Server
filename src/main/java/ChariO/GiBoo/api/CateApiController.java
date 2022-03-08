package ChariO.GiBoo.api;

import ChariO.GiBoo.service.FacCateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static ChariO.GiBoo.dto.FacCateDtos.*;

@RestController
@RequiredArgsConstructor
public class CateApiController {

    private final FacCateService facCateService;

    @Operation(summary = "Category만 조회", description = "카테고리의 아이디와 카테고리 이름만 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/categories", produces = "application/json;charset=UTF-8")
    public GetCateResponse categories(){
        return new GetCateResponse(facCateService.findCateAll());
    }
}
