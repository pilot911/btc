package com.example.common.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PromoController {

    ResponseEntity<?> createPromoSetting();

    ResponseEntity<?> updatePromoSetting();
}
