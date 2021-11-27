package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.constants.Action;
import com.ahlypalo.express_kassa.constants.HttpHeaders;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.entity.Shift;
import com.ahlypalo.express_kassa.service.ShiftService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shift")
public class ShiftController {

    private static final String KEY_EMPLOYEE_NAME = "employee_name";

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping(headers = HttpHeaders.ACTION + "=" + Action.OPEN_SHIFT)
    public void openShift(@RequestBody Map<String, String> body, Merchant merchant) {
        shiftService.openShift(body.get(KEY_EMPLOYEE_NAME), merchant);
    }

    @PostMapping(headers = HttpHeaders.ACTION + "=" + Action.CLOSE_SHIFT)
    public void closeShift(Merchant merchant) {
        shiftService.closeShift(merchant);
    }

    @GetMapping
    public Shift getCurrentShift(Merchant merchant) {
        return shiftService.getCurrentShift(merchant).orElse(null);
    }
}
