package ChariO.GiBoo.dto;
import ChariO.GiBoo.domain.Donation;

import ChariO.GiBoo.repository.DonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    /**
     * DonPost
     */
    @Data
    public static class DonPostRequest {
        @NotEmpty private String f_name;
        @NotNull private int donationPrice;
        @NotEmpty private String donationDate;
    }

    @Data
    public static class DonPostResponse {
        private Donation donation;
        private String status;

        public DonPostResponse(Donation donation, String status) {
            this.donation = donation;
            this.status = status;
        }
    }

    /**
     * DonPut
     */
    @Data
    public static class DonPutRequest {
        @NotEmpty private String f_name;
                  private int donationprice;
                  private String donationDate;
    }

    @Data
    public static class DonPutResponse{
        private String status;

        public DonPutResponse(String status) {
            this.status = status;
        }
    }

    /**
     * DonDelete
     */
    @Data
    public static class DonDeleteRequest {
        private String f_name;
    }

    @Data
    public static class DonDeleteResponse {
        private String status;

        public DonDeleteResponse(String status) {
            this.status = status;
        }
    }

}
