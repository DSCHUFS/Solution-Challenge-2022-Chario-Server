package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Donation;
import ChariO.GiBoo.repository.DonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonService {

    private final DonRepository donRepository;

    public List<Donation> userDonateAll(Long id) {
        return donRepository.userDonateAll(id);
    }
}
