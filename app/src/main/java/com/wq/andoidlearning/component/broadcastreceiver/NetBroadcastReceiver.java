package com.wq.andoidlearning.component.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.wq.andoidlearning.component.service.ServiceBean;

import org.simple.eventbus.EventBus;

public class NetBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("网络状态发生变化");
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            StringBuilder stringBuilder = new StringBuilder();
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                stringBuilder.append("WIFI已连接,移动数据已连接\n");
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                stringBuilder.append("WIFI已连接,移动数据已断开\n");
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                stringBuilder.append("WIFI已断开,移动数据已连接\n");
            } else {
                stringBuilder.append("WIFI已断开,移动数据已断开\n");
            }
            EventBus.getDefault().post(new ServiceBean(stringBuilder.toString()));
        } else {
            //这里的就不写了，前面有写，大同小异
            System.out.println("API level 大于21");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            if(networks.length==0){
                EventBus.getDefault().post(new ServiceBean("无网络"));
            }
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            EventBus.getDefault().post(new ServiceBean(sb.toString()));
        }
    }


}