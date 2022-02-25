package ChariO.GiBoo.dto;
import ChariO.GiBoo.domain.Donation;

import ChariO.GiBoo.repository.DonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

public class DonDtos {

    @Data
    @AllArgsConstructor
    public static class DonResult<T> {
        private int fac_count;
        private Long total_price;
        private T data;
        private String status;
    }

    @Data
    public static class DonDto{
        private Long f_id;
        private String f_name;
        private String f_logo;
        private int u_m_price;
        private String don_date;


        public DonDto(Donation d){
            this.f_id = d.getFacility().getId();
            this.f_name = d.getFacility().getF_name();
            this.f_logo = d.getFacility().getF_logo();
            this.u_m_price = d.getDonationPrice();
            this.don_date = d.getDonationDate();

        }
    }
}
