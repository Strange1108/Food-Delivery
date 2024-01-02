package com.Mct.food.delivery.platform.Model;

import com.Mct.food.delivery.platform.Model.Enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Order.class,property="orderId")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private LocalDateTime orderCreationTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "fk_userId")
    User  user;

    @ManyToOne
    @JoinColumn(name = "fk_foodId")
    Food food;

}
