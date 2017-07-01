package io.keepcoding.madridshops.domain.managers.cache;

public interface ClearCacheManager {
    void execute(final Runnable completion);
}
