package com.platzi.pizzeriaDominos.persistence.audit;

import com.platzi.pizzeriaDominos.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;
/* This audit only works for functions that go through the life cycle of the entities, not for statements with native query. */
public class AuditPizzaEntity {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad (PizzaEntity entity){
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist (PizzaEntity entity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete (PizzaEntity entity){
        System.out.println(entity.toString());
    }

}
