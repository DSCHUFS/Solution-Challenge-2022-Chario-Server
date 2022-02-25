package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Donation;
import ChariO.GiBoo.service.DonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.DonDtos.*;

@RestController
@RequiredArgsConstructor
public class DonApiController {

    private final DonService donService;

    /**
     * Id로 특정 사용자의 기부 목록 및 정보 GET
     */
    @Operation(summary = "user's get donation list", description = "사용자 한명의 기부 목록 및 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/donation", produces = "application/json;charset=UTF-8")
    public DonResult donationsAll( @RequestHeader("Authorization") Long u_id){
        List<Donation> donsByUserId = donService.userDonateAll(u_id);
        String status = "탐색 완료";
        List<DonDto> collect = donsByUserId.stream()
                .map(d -> new DonDto(d))
                .collect(Collectors.toList());
        Long total_price = collect.stream()
                .map(x -> x.getU_m_price())
                .collect(Collectors.summingLong(Integer::longValue));
        return new DonResult(collect.size(), total_price, collect, status);
    }
}
