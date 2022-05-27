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

import static com.ahlypalo.express_kassa.constants.ErrorCode.YOU_DONT_HAVE_SHIFT;
import static com.ahlypalo.express_kassa.constants.ErrorCode.YOU_HAVE_OPENED_SHIFT;

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
            merchant.setDetails(details);
            merchantRepository.save(merchant);
        }
        if (details.getShift() != null) {
            throw new ApiException("You have an opened shift", YOU_HAVE_OPENED_SHIFT);
        }
        Shift shift = new Shift();
        shift.setStartDate(new Date());
        shift.setEmployeeName(employeeName);
        details.setShift(shift);
        merchantDetailsRepository.save(details);
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
        merchantDetailsRepository.save(details);
    }
}
