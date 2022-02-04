package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.service.FacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FacApiController {

    private final FacService facService;

    @Operation(summary = "facility list", description = "기관리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/api/facilities")
    public  Result facilities(){
        List<Facility> findFacs = facService.findFacs();
        List<FacDto> collect = findFacs.stream()
                .map(f -> new FacDto(f.getF_name(), f.getF_intro(), f.getF_minimal(), f.getF_home(), f.getF_ars(), f.getF_phone(), f.getF_pay(), f.getF_logo()))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class FacDto {
        private String f_name;
        private String f_intro;
        private int f_minimal;
        private String f_home;
        private String f_ars;
        private String f_phone;
        private String f_pay;
        private String f_logo;
    }
}
