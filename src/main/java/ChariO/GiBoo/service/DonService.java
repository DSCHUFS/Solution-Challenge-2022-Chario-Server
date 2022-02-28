package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.Donation;
import ChariO.GiBoo.repository.DonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DonService {

    private final DonRepository donRepository;

    @Transactional()
    public Donation createOne(Long u_id, String f_name, int price, String don_date){
        return donRepository.create(u_id, f_name, price, don_date);
    }

    @Transactional(readOnly = true)
    public List<Donation> userDonateAll(Long id) {
        return donRepository.userDonateAll(id);
    }

    @Transactional()
    public Donation updateOne(Long u_id, String f_name, int price, String don_date){
        return donRepository.update(u_id, f_name, price, don_date);
    }

    @Transactional()
    public void deleteOne(Long u_id, String f_name) {
        Donation donation = donRepository.findByUserFac(u_id,f_name);
        donRepository.delete(donation);
    }
}
