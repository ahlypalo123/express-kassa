package com.taviak.expresskassa.controller;

import com.taviak.expresskassa.config.SwaggerConfig;
import com.taviak.expresskassa.constants.OrderColumn;
import com.taviak.expresskassa.entity.Check;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.repository.CheckRepository;
import com.taviak.expresskassa.service.CheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/check")
@Api(tags = SwaggerConfig.TAG_CHECK)
public class CheckController {

    private final CheckService checkService;
    private final CheckRepository checkRepository;

    public CheckController(CheckService checkService, CheckRepository checkRepository) {
        this.checkService = checkService;
        this.checkRepository = checkRepository;
    }

    @GetMapping
    @ApiOperation("Получение истории чеков")
    public List<Check> getCheckHistory(Merchant merchant, @RequestParam OrderColumn orderColumn) {
        return checkService.getCheckHistory(merchant, orderColumn);
    }

    @PostMapping
    @ApiOperation("Инициализация")
    public Check saveCheck(@RequestBody Check check, Merchant merchant) {
        return checkService.createCheck(check, merchant);
    }

    @PutMapping
    @ApiOperation("Обновление")
    public Check updateCheck(@RequestBody Check check, Merchant merchant) {
        return checkService.updateCheck(check, merchant);
    }

    @DeleteMapping
    @ApiOperation("Удаление")
    public void deleteCheck(@RequestParam Long id) {
        checkRepository.deleteById(id);
    }
}
