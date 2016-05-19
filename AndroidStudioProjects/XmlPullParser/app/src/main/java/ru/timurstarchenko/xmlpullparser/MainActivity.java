package ru.timurstarchenko.xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp="";

        try {
            XmlPullParser xpp = prepareXpp();
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT){
                switch (xpp.getEventType()){
                    //начало документа
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "Start document");
                        break;
                    //начало тега
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "START_TAG: name= " + xpp.getName()
                                + ", depth = " + xpp.getDepth()
                                + ", attrCount = " + xpp.getAttributeCount());
                        tmp = "";
                        for(int i = 0; i< xpp.getAttributeCount(); i++){
                            tmp = tmp + xpp.getAttributeName(i)+ " = " +
                                    xpp.getAttributeValue(i) + ", " ;
                        }
                        if(!TextUtils.isEmpty(tmp)){
                            Log.d(TAG, "Attributes: " + tmp);
                        }
                        break;
                    //конец кода
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "END_TAG: name = " + xpp.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.d(TAG, "text = " + xpp.getText());
                        break;
                    default:
                        break;
                    //следующий элемент
                }
                xpp.next();
            }
        Log.d(TAG, "END_DOCUMENT");
        }catch (XmlPullParserException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    XmlPullParser prepareXpp(){
        return getResources().getXml(R.xml.data);
    };
}
