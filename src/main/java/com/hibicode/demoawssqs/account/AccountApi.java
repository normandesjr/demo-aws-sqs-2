package com.hibicode.demoawssqs.account;

import com.hibicode.demoawssqs.account.request.AccountRequest;
import com.hibicode.demoawssqs.account.response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:8882", name = "accountClient")
@RequestMapping("/accounts")
public interface AccountApi {

    @GetMapping("/{id}")
    AccountResponse getAmount(@PathVariable("id") Integer id);

    @PostMapping
    void save(@RequestBody AccountRequest accountRequest);

}
