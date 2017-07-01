package io.keepcoding.madridshops.domain.managers.cache;

import io.keepcoding.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheManager {
    void execute(Shops shops, Runnable completion);
}
