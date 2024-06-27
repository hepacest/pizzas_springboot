package com.platzi.pizzeriaDominos.service.DTO;

import lombok.Data;

@Data
public class UpdatePizzaPriceDTO {

    private Integer pizzaId;
    private double newPrice;

}
