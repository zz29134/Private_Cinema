package com.mp.private_cinema.event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/25
 * 说明
 */

public class StartParentEvent {
    public SupportFragment targetFragment;

    public StartParentEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
