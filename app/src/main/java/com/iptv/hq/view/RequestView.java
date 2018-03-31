package com.iptv.hq.view;


import com.iptv.hq.bean.Bean;

import retrofit2.Call;

public interface RequestView extends IView {
    void onResponse(Call call, Bean bean, int id);

    void onFailure(Call call, Throwable throwable, int id);
}
