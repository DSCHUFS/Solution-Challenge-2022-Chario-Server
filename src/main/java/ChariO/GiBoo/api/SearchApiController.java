package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.dto.SearchDtos;
import ChariO.GiBoo.service.FacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static ChariO.GiBoo.dto.SearchDtos.*;

@RestController
@RequiredArgsConstructor
public class SearchApiController {

    private final FacService facService;

    @GetMapping(value = "/api/search", produces = "application/json;charset=UTF-8")
    public SearchGetResponse search(@RequestBody SearchGetRequest request){

        Facility facility = facService.findOneByName(request.getKeyword());
        return new SearchGetResponse(facility);
    }
}
