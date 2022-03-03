package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Donation;
import ChariO.GiBoo.service.DonService;
import ChariO.GiBoo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ChariO.GiBoo.dto.DonDtos.*;

@RestController
@RequiredArgsConstructor
public class DonApiController {

    private final DonService donService;
    private final UserService userService;

    /**
     * @Header u_id
     * @return Donation, status
     */
    @Operation(summary = "사용자의 기부 정보 등록", description = "특정 사용자의 기부 정보 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/api/donation", produces = "application/json; charset=UTF-8")
    public DonPostResponse donPost(@RequestHeader("Authorization") String u_uuid,
                                    @Valid @RequestBody DonPostRequest request){
        Long u_id = userService.findByUuid(u_uuid);
        Donation donation = donService.createOne(u_id, request.getF_name(), request.getDonationPrice(), request.getDonationDate());
        String status = "등록이 완료되었습니다.";
        return new DonPostResponse(donation, status);
    }

    /**
     * @Header u_id
     * @return status, info
     */
    @Operation(summary = "사용자의 전체 기부 목록 조회", description = "사용자 한명의 기부 목록 및 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/api/donation", produces = "application/json; charset=UTF-8")
    public DonResult donationsAll(@RequestHeader("Authorization") String u_uuid){
        Long u_id = userService.findByUuid(u_uuid);
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

    /**
     * @Header u_id
     * @return status
     */
    @Operation(summary = "사용자의 기부 정보 변경", description = "특정 사용자의 특정 기관 기부 정보 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(value= "/api/donation", produces="application/json; charset=UTF-8")
    public DonPutResponse donPut(@RequestHeader("Authorization") String u_uuid,
                                  @Valid @RequestBody DonPutRequest request){
        Long u_id = userService.findByUuid(u_uuid);
        Donation don = donService.updateOne(u_id, request.getF_name(), request.getDonationprice(), request.getDonationDate());
        String status = "변경 사항이 저장되었습니다.";
        return new DonPutResponse(status);
    }

    /**
     * @Header u_id
     * @return status
     */
    @Operation(summary = "사용자의 기부 정보 삭제", description = "특정 사용자의 특정 기관 기부 정보 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping(value= "/api/donation", produces="application/json; charset=UTF-8")
    public DonDeleteResponse donDelete(@RequestHeader("Authorization") String u_uuid,
                                        @Valid @RequestBody DonDeleteRequest request){
        Long u_id = userService.findByUuid(u_uuid);
        donService.deleteOne(u_id, request.getF_name());
        String status = "삭제되었습니다.";
        return new DonDeleteResponse(status);
    }
}
