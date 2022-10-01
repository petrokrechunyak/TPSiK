package subject.lab_4;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;

@ToString
@Getter
@Setter
public class Delivery {
    public static long _id = 0;

    private Long id;
    private String phone;
    private String address;
    private String courier;
    private Boolean fragile;
    private Boolean delete = false;

    public Delivery(String phone, String address, String courier, boolean fragile) {
        this.phone = phone;
        this.address = address;
        this.courier = courier;
        this.fragile = fragile;
    }

    public Delivery(Long id, String phone, String address, String courier, Boolean fragile) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.courier = courier;
        this.fragile = fragile;
    }

}
