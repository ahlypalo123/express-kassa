package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.Check;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.service.CheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    private final CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @GetMapping
    public List<Check> getCheckHistory(Merchant merchant, @RequestParam OrderColumn orderColumn) {
        return checkService.getCheckHistory(merchant, orderColumn);
    }

    @PostMapping
    public void saveCheck(@RequestBody Check check, Merchant merchant) {
        checkService.saveCheck(check, merchant);
    }
}
