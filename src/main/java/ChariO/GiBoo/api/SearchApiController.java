package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.dto.FacDtos;
import ChariO.GiBoo.dto.SearchDtos;
import ChariO.GiBoo.dto.UserDtos;
import ChariO.GiBoo.service.FacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.FacDtos.*;
import static ChariO.GiBoo.dto.SearchDtos.*;

@RestController
@RequiredArgsConstructor
public class SearchApiController {

    @Autowired
    private final FacService facService;

    @Operation(summary = "검색 기능", description = "기관의 부분적인 이름만 입력해도 검색 가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
    public SearchGetResponse2 search(@Param("facName") String facName){

        List<Facility> facility1 = facService.findOneByNameLike(facName);
        List<FacDto> collect = facility1.stream()
                .map(f -> new FacDto(f))
                .collect(Collectors.toList());
        return new SearchGetResponse2(collect.size(), collect);
    }
}
