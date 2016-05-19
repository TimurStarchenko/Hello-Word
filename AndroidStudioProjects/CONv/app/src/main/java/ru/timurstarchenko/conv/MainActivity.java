package ru.timurstarchenko.conv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCurrencyClick(View v) {
        TextView tv = (TextView) findViewById(R.id.textView);

        try{
 /*
  определяем URL сервиса
  готовим API, позволяющий выполнять разбор документа
  загружаем в парсер полученный ответ и вызываем метод parse
  */
            URL url = new URL("https://privat24.privatbank.ua/p24/accountorder?oper=prp&PUREXML&apicour&country=ua");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
 /*получаем агрегатный узел с дочерними узлами с атрибутами, хранящими значения валют;
 в ответе всего два узла, мы возьмем первый, а при необходимости тут вполне можно запустить цикл с nodeList.getLength
*/

            NodeList nodeList = doc.getElementsByTagName("exchangerate");
            Node node = nodeList.item(0);
            // опускаемся на узел ниже и получаем список его атрибутов
            NamedNodeMap attributes = node.getFirstChild().getAttributes();
            //получаем значение атрибут buy
            Node currencyAttribEUR  = attributes.getNamedItem("buy");
            // ... и его значение
            String currencyValueEUR = currencyAttribEUR.getNodeValue();

            // аналогично поступаем с датой, чтобы иметь представление о актуальности
            Node dateCurrency       = attributes.getNamedItem("date");
            String dateCurrencyStr  = dateCurrency.getNodeValue();
            // и выводим информацию
            tv.setText("Курс евро на "+dateCurrencyStr+":"+currencyValueEUR+ "коп");

        }
        catch (Exception e) {
            tv.setText("Не удалось выполнить операцию");
        };

    }
}
