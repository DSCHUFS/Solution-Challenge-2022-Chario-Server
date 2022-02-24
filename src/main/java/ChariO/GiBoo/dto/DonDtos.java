package ChariO.GiBoo.dto;
import ChariO.GiBoo.domain.Donation;

import ChariO.GiBoo.repository.DonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DonDtos {

    @Data
    @AllArgsConstructor
    public static class DonResult<T> {
        private int count;
        private T data;
        private String status;
    }

    @Data
    public static class DonDto{
        private Long id;
        private int u_m_price;
        private String don_date;
        private Long f_id;

        public DonDto(Donation d){
            this.id = d.getId();
            this.u_m_price = d.getDonationPrice();
            this.don_date = d.getDonationDate();
            this.f_id = d.getFacility().getId();
        }
    }
}
