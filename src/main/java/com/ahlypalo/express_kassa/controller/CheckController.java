package com.ahlypalo.express_kassa.controller;

import com.ahlypalo.express_kassa.constants.OrderColumn;
import com.ahlypalo.express_kassa.entity.Check;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.repository.CheckRepository;
import com.ahlypalo.express_kassa.service.CheckService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckController {

    private final CheckService checkService;
    private final CheckRepository checkRepository;

    public CheckController(CheckService checkService, CheckRepository checkRepository) {
        this.checkService = checkService;
        this.checkRepository = checkRepository;
    }

    @GetMapping
    public List<Check> getCheckHistory(Merchant merchant, @RequestParam OrderColumn orderColumn) {
        return checkService.getCheckHistory(merchant, orderColumn);
    }

    @PostMapping
    public Check saveCheck(@RequestBody Check check, Merchant merchant) {
        return checkService.createCheck(check, merchant);
    }

    @PutMapping
    public Check updateCheck(@RequestBody Check check, Merchant merchant) {
        return checkService.updateCheck(check);
    }

    @DeleteMapping
    public void deleteCheck(@RequestParam Long id) {
        checkRepository.deleteById(id);
    }
}
