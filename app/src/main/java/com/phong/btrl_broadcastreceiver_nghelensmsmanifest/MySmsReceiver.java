package com.phong.btrl_broadcastreceiver_nghelensmsmanifest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySmsReceiver extends BroadcastReceiver {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object []arrMessage = (Object[]) bundle.get("pdus");
        for (int i = 0; i < arrMessage.length; i++){
            byte []bytes = (byte[]) arrMessage[i];
            SmsMessage message = SmsMessage.createFromPdu(bytes);
            //Lấy nội dung:
            String body = message.getMessageBody();
            //Lấy SĐT:
            String phone = message.getDisplayOriginatingAddress();
            //Lấy thời gian:
            Date date = new Date(message.getTimestampMillis());
            String sDate = sdf.format(date);
            //Show lên:
            Toast.makeText(context,"Thông báo: \n" + phone +"\n" + body + "\n" + sDate,Toast.LENGTH_LONG).show();
        }
    }
}
