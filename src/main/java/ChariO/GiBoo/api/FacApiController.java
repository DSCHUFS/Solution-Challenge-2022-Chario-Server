package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Contents;
import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.dto.FacDtos;
import ChariO.GiBoo.service.ConService;
import ChariO.GiBoo.service.FacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.api.ConApiController.*;
import static ChariO.GiBoo.dto.FacDtos.*;

@RestController
@RequiredArgsConstructor
public class FacApiController {

    private final FacService facService;
    private final ConService contentService;

    /**
     * Swagger 명세
     */
    @Operation(summary = "facility list", description = "기관리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
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
     Swagger 명세
     **/
    @Operation(summary = "facility detail", description = "기관 상세")
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
