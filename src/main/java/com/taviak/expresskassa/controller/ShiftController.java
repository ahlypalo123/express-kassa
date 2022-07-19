package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.constants.Action;
import com.taviak.expresskassa.constants.HttpHeaders;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.entity.Shift;
import com.taviak.expresskassa.service.ShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shift")
@Api(tags = SwaggerConfig.TAG_SHIFT)
public class ShiftController {

    private static final String KEY_EMPLOYEE_NAME = "employee_name";

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping(headers = HttpHeaders.ACTION + "=" + Action.OPEN_SHIFT)
    @ApiOperation("Открытие смены")
    public Shift openShift(@RequestBody Map<String, String> body, Merchant merchant) {
        return shiftService.openShift(body.get(KEY_EMPLOYEE_NAME), merchant);
    }

    @PostMapping(headers = HttpHeaders.ACTION + "=" + Action.CLOSE_SHIFT)
    @ApiOperation("Закрытие смены")
    public void closeShift(Merchant merchant) {
        shiftService.closeShift(merchant);
    }

    @GetMapping
    @ApiOperation("Получение текущей смены")
    public Shift getCurrentShift(Merchant merchant) {
        return merchant.getDetails().getShift();
    }
}
