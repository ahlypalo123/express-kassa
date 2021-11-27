package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void openShift(String employeeName, Merchant merchant) {
        if (getCurrentShift(merchant).isPresent()) {
            throw new ApiException("You have an opened shift");
        }
        Shift shift = new Shift();
        shift.setStartDate(new Date());
        shift.setMerchant(merchant);
        shift.setEmployeeName(employeeName);
        shiftRepository.save(shift);
    }

    public void closeShift(Merchant merchant) {
        Shift current = getCurrentShift(merchant)
                .orElseThrow(() -> new ApiException("You have no opened shift"));
        current.setEndDate(new Date());
        shiftRepository.save(current);
    }

    public Optional<Shift> getCurrentShift(Merchant merchant) {
        Date now = new Date();
        return shiftRepository.findByStartDateBeforeAndEndDateNullAndMerchant(now, merchant);
    }
}
