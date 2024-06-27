package com.platzi.pizzeriaDominos.persistence.entity;

import com.platzi.pizzeriaDominos.persistence.audit.AuditPizzaEntity;
import com.platzi.pizzeriaDominos.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table (name="pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaEntity.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_pizza", nullable = false)
    private Integer idPizza;
    @Column (nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column (nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @Column (columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column (columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column (nullable = false, columnDefinition = "TINYINT")
    private Boolean available;


    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }
}
