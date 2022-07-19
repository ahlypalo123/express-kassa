package com.taviak.expresskassa.service;

import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.MerchantDetails;
import com.taviak.expresskassa.entity.Shift;
import com.taviak.expresskassa.exception.ApiException;
import com.taviak.expresskassa.repository.MerchantRepository;
import com.taviak.expresskassa.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.taviak.expresskassa.constants.ErrorCode.YOU_DONT_HAVE_SHIFT;
import static com.taviak.expresskassa.constants.ErrorCode.YOU_HAVE_OPENED_SHIFT;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final MerchantRepository merchantRepository;

    public ShiftService(ShiftRepository shiftRepository, MerchantRepository merchantRepository) {
        this.shiftRepository = shiftRepository;
        this.merchantRepository = merchantRepository;
    }

    public Shift openShift(String employeeName, Merchant merchant) {
        MerchantDetails details = merchant.getDetails();
        if (details == null) {
            details = new MerchantDetails();
            merchant.setDetails(details);
        }
        if (details.getShift() != null) {
            throw new ApiException("You have an opened shift", YOU_HAVE_OPENED_SHIFT);
        }
        Shift shift = new Shift();
        shift.setStartDate(new Date());
        shift.setEmployeeName(employeeName);
        details.setShift(shift);
        merchantRepository.save(merchant);
        return shift;
    }

    public void closeShift(Merchant merchant) {
        MerchantDetails details = merchant.getDetails();
        if (details.getShift() == null) {
            throw new ApiException("You don't have an opened shift", YOU_DONT_HAVE_SHIFT);
        }
        Shift current = details.getShift();
        current.setEndDate(new Date());
        shiftRepository.save(current);
        details.setShift(null);
        merchantRepository.save(merchant);
    }
}
