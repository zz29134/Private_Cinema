package com.mp.pc_library.lib_event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 创建人 Zhangzhe
 * 日期   2016/11/25
 * 说明
 */
public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
