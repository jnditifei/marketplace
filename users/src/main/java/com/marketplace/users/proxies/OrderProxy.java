package com.marketplace.users.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "marketplace-order", url = "localhost:8083")
public interface OrderProxy {
}
