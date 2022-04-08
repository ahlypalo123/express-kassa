package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import com.ahlypalo.express_kassa.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final MerchantRepository merchantRepository;

    public ShiftService(ShiftRepository shiftRepository, MerchantRepository merchantRepository) {
        this.shiftRepository = shiftRepository;
        this.merchantRepository = merchantRepository;
    }

    public Shift openShift(String employeeName, Merchant merchant) {
        if (merchant.getShift() != null) {
            throw new ApiException("You have an opened shift");
        }
        Shift shift = new Shift();
        shift.setStartDate(new Date());
        shift.setEmployeeName(employeeName);
        Long id = shiftRepository.save(shift).getId();
        shift.setId(id);
        merchant.setShift(shift);
        merchantRepository.save(merchant);
        return shift;
    }

    public void closeShift(Merchant merchant) {
        if (merchant.getShift() == null) {
            throw new ApiException("You have no opened shift");
        }
        Shift current = merchant.getShift();
        current.setEndDate(new Date());
        shiftRepository.save(current);
        merchant.setShift(null);
        merchantRepository.save(merchant);
    }
}
