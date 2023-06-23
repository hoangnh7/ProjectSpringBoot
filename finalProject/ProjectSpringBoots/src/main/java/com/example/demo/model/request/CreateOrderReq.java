package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderReq {
    @NotNull(message = "Sản phẩm trống")
    @NotEmpty(message = "Sản phẩm trống")
    @JsonProperty("product_id")
    private String productId;

    @Min(value = 35)
    @Max(value = 42)
    private int size;

    @NotNull(message = "Họ tên trống")
    @NotEmpty(message = "Họ tên trống")

    @JsonProperty("receiver_name")
    private String receiverName;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Điện thoại không hợp lệ")

    @JsonProperty("receiver_phone")
    private String receiverPhone;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")

    @JsonProperty("receiver_address")
    private String receiverAddress;


    @JsonProperty("total_price")
    private long totalPrice;
    private String note;
    @JsonProperty("product_price")
    private long productPrice;

}
