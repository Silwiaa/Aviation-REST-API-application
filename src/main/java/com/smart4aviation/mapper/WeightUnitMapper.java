package com.smart4aviation.mapper;

import com.smart4aviation.service.DbCargoEntityService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class WeightUnitMapper {
    public double mapToLb(double totalWeightKg) {
       return totalWeightKg / 0.45359237;
    }

    public double mapToKg (double totalWeightLb) {
        return totalWeightLb / 2.20462262;
    }
}
