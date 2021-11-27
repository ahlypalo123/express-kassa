package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.Check;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.CheckRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final ShiftService shiftService;

    public CheckService(CheckRepository checkRepository, ShiftService shiftService) {
        this.checkRepository = checkRepository;
        this.shiftService = shiftService;
    }

    public void saveCheck(Check check, Merchant merchant) {
        Shift shift = shiftService.getCurrentShift(merchant)
                .orElseThrow(() -> new ApiException("You have no opened shift"));

        check.setMerchantDetails(merchant.getDetails());
        check.setDate(new Date());
        check.setShift(shift);

        checkRepository.save(check);
    }

    public List<Check> getCheckHistory(Merchant merchant, OrderColumn orderColumn) {
        return checkRepository.getAllByMerchant(merchant, Sort.by(orderColumn.getName()));
    }
}
