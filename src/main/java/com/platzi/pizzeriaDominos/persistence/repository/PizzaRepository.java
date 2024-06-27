package com.platzi.pizzeriaDominos.persistence.repository;

import com.platzi.pizzeriaDominos.persistence.entity.PizzaEntity;
import com.platzi.pizzeriaDominos.service.DTO.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice ();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase (String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase (String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase (String description);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc (double price);
    Integer countByVeganTrue();

    /* Option 1
    @Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza ", nativeQuery = true)
    void updatePrice (@Param("idPizza") Integer idPizza, @Param("newPrice") double newPrice);
    */

    @Query(value = "UPDATE pizza SET price = :#{#pizzaPriceDTO.newPrice} " +
            "WHERE id_pizza = :#{#pizzaPriceDTO.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice (@Param("pizzaPriceDTO") UpdatePizzaPriceDTO pizzaPriceDTO);
}
