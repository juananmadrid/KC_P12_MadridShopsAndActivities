package io.keepcoding.madridshops.domain.interactors;

import io.keepcoding.madridshops.domain.model.Shops;

public interface SaveAllShopsIntoCacheInteractor {
    void execute(Shops shops, Runnable completion);
}
