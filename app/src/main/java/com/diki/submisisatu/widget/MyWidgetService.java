package com.diki.submisisatu.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetViewFactory(getApplicationContext());
    }
}
