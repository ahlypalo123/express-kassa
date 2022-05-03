package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.MerchantDetails;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.MerchantDetailsRepository;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import com.ahlypalo.express_kassa.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final MerchantDetailsRepository merchantDetailsRepository;
    private final MerchantRepository merchantRepository;

    public ShiftService(ShiftRepository shiftRepository, MerchantDetailsRepository merchantDetailsRepository,
                        MerchantRepository merchantRepository) {
        this.shiftRepository = shiftRepository;
        this.merchantDetailsRepository = merchantDetailsRepository;
        this.merchantRepository = merchantRepository;
    }

    public Shift openShift(String employeeName, Merchant merchant) {
        MerchantDetails details = merchant.getDetails();
        if (details == null) {
            details = new MerchantDetails();
        }
        if (details.getShift() != null) {
            throw new ApiException("You have an opened shift");
        }
        Shift shift = new Shift();
        shift.setStartDate(new Date());
        shift.setEmployeeName(employeeName);
        Long id = shiftRepository.save(shift).getId();
        shift.setId(id);
        details.setShift(shift);
        details = merchantDetailsRepository.save(details);
        if (merchant.getDetails() == null) {
            merchant.setDetails(details);
            merchantRepository.save(merchant);
        }
        return shift;
    }

    public void closeShift(Merchant merchant) {
        MerchantDetails details = merchant.getDetails();
        if (details.getShift() == null) {
            throw new ApiException("You have no opened shift");
        }
        Shift current = details.getShift();
        current.setEndDate(new Date());
        shiftRepository.save(current);
        details.setShift(null);
        merchantDetailsRepository.save(details);
    }
}
