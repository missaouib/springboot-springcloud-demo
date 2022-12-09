package com.ruyuan.cloud.excel.listener;

import cn.afterturn.easypoi.cache.manager.POICacheManager;
import com.ruyuan.cloud.excel.cover.RuyuanFileLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring容器启动监听器 启动时候，将自定义的文件加载器设置到poi缓存管理器
 *
 * @author zhonghuashishan
 */
@Component
@ConditionalOnProperty(prefix = "ruyuan.easy.poi", name = "enable", matchIfMissing = true)
public class ExcelListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        POICacheManager.setFileLoader(new RuyuanFileLoader());
    }

}