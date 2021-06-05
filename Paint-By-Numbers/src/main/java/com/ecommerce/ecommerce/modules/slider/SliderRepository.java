package com.ecommerce.ecommerce.modules.slider;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SliderRepository extends JpaRepository<Slider, UUID> {
}