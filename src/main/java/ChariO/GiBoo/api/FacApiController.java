package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.domain.FacilityCategory;
import ChariO.GiBoo.service.ConService;
import ChariO.GiBoo.service.FacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.ConDtos.*;
import static ChariO.GiBoo.dto.FacDtos.*;
import static ChariO.GiBoo.dto.FacCateDtos.*;

@RestController
@RequiredArgsConstructor
public class FacApiController {

    private final FacService facService;
    private final ConService contentService;

    /**
     * Swagger 명세
     */
    @Operation(summary = "전체 기관 리스트", description = "전체 기관 리스트의 수(count), 각 기관의 이름, 설명, 최소 금액, 메인 화면 url, ars 번호, 전화번호, 결제 url, 로고 이미지 url")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/facilities", produces = "application/json;charset=UTF-8")
    public Result facilities(){
        List<Facility> findFacs = facService.findFacs();
        List<FacDto> collect = findFacs.stream()
                .map(f -> new FacDto(f))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    /**
     * Swagger 명세
     */
    @Operation(summary = "카테고리별 기관 리스트", description = "기관 리스트의 수(count), 각 기관의 id, 이름, 설명, 최소 금액, 메인 화면 url, ars 번호, 전화번호, 결제 url, 로고 이미지 url")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/category/{category_id}/facilities", produces = "application/json;charset=UTF-8")
    public Result facilitiesByCategory(@PathVariable("category_id") Long category_id){
        List<FacilityCategory> facsByCategory = facService.findFacsByCategory(category_id);
        List<FacByCateDto> collect = facsByCategory.stream()
                .map(fc -> new FacByCateDto(fc))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    /**
     Swagger 명세
     **/
    @Operation(summary = "단일 기관 상세 페이지 조회", description = "기관 상세")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/facility/{id}", produces = "application/json;charset=UTF-8")
    public DetailFacResponse findFacility(@PathVariable("id") Long id){
        Facility facility = facService.findOne(id);
        List<ContentDto> collect = contentService.findByFac(id).stream()
                .map(c -> new ContentDto(c))
                .collect(Collectors.toList());
        return new DetailFacResponse(new FacDto(facility), collect);
    }


}
