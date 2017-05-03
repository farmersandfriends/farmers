package nongsa.agoto.MaintoOther.vacant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import nongsa.agoto.MaintoOther.information.adapter.fieldAdapter;
import nongsa.agoto.R;

public class field extends AppCompatActivity {
    private fieldAdapter m_Adapter;
    ListView field_listing;
    Button field_button;
    EditText field_edit;
    protected void onCreate(Bundle savedInstanceState) {
        m_Adapter = new fieldAdapter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        final ArrayList<String> LIST_TITLE = new ArrayList<String>();
        final ArrayList<String> LIST_DESCRIPTION = new ArrayList<String>();
        final ArrayList<String> LIST_TELEPHONE = new ArrayList<String>();
        final ArrayList<String> LIST_ADDRESS = new ArrayList<String>();
        final ArrayList<String> LIST_ROADADRESS = new ArrayList<String>();
        field_button = (Button)findViewById(R.id.termbutton);
        field_edit = (EditText)findViewById(R.id.termedit);
        field_listing = (ListView)findViewById(R.id.field_list) ;

        //java코드로 특정 url에 요청보내고 응답받기
        //기본 자바 API를 활용한 방법



        field_button.setOnClickListener(new View.OnClickListener(){ // 주요정보- 기술정보
            public void onClick(View v) {
                m_Adapter = new fieldAdapter();
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            String clientID = "iPGdgKzi83KtPts92cZ3"; //네이버 개발자 센터에서 발급받은 clientID입력
                            String clientSecret = "5Y9N77599E";        //네이버 개발자 센터에서 발급받은 clientSecret입력
                            String query = field_edit.getText().toString()+" 부동산";
                            query = URLEncoder.encode(query.toString(),"utf-8");
                            URL url = new URL("https://openapi.naver.com/v1/search/local?query="+query); //API 기본정보의 요청 url을 복사해오고 필수인 query를 적어줍니당!
                            String thing = "";
                            URLConnection urlConn = url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체

                            urlConn.setRequestProperty("X-Naver-Client-ID", clientID);
                            urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                            int number = 0;
                            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                            String msg = null;
                            for(int i = 0 ;i <6;i++){
                                msg = br.readLine();
                                if(msg.startsWith("\""+"display"+"\":")){
                                    String split[] =  msg.split("\""+"display"+"\": ");
                                    number = Integer.parseInt(split[1].replace(",",""));
                                    System.out.println(Integer.parseInt(split[1].replace(",","")));
                                }

                            }
                            if(number == 0){

                            }
                            else {
                                while ((msg = br.readLine()) != null) {
                                    System.out.println(msg);
                                    if (msg.startsWith("\"" + "title" + "\":")) {
                                        String split[] = msg.split("\"" + "title" + "\": ");
                                        thing = split[1].replace(",", "");
                                        thing = thing.replace("\"", "");
                                        thing = thing.replace("<b>", " ");
                                        thing = thing.replace("</b>", " ");
                                        System.out.println(thing);
                                        LIST_TITLE.add(thing);
                                        m_Adapter.add(thing);
                                    }
                                    else if (msg.startsWith("\"" + "description" + "\":")) {
                                        String split[] = msg.split("\"" + "description" + "\": ");
                                        thing = split[1].replace(",", "");
                                        thing = thing.replace("\"", "");
                                        thing = thing.replace("<b>", " ");
                                        thing = thing.replace("</b>", " ");
                                        System.out.println(thing);
                                        LIST_DESCRIPTION.add(thing);
                                        m_Adapter.addDESCRIPTION(thing);
                                    }
                                    else if (msg.startsWith("\"" + "telephone" + "\":")) {
                                        String split[] = msg.split("\"" + "telephone" + "\": ");
                                        thing = split[1].replace(",", "");
                                        thing = thing.replace("\"", "");
                                        System.out.println(thing);
                                        LIST_TELEPHONE.add(thing);
                                        m_Adapter.addTELEPHONE(thing);
                                    }
                                    else if (msg.startsWith("\"" + "address" + "\":")) {
                                        String split[] = msg.split("\"" + "address" + "\": ");
                                        thing = split[1].replace(",", "");
                                        thing = thing.replace("\"", "");
                                        System.out.println(thing);
                                        LIST_ADDRESS.add(thing);
                                        m_Adapter.addADDRESS(thing);

                                    }
                                    else if (msg.startsWith("\"" + "roadAddress" + "\":")) {
                                        String split[] = msg.split("\"" + "roadAddress" + "\": ");
                                        thing = split[1].replace(",", "");
                                        thing = thing.replace("\"", "");
                                        System.out.println(thing);
                                        LIST_ROADADRESS.add(thing);
                                        m_Adapter.addROADADDRESS(thing);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try{
                    thread.join();
                    m_Adapter.notifyDataSetChanged();
                    field_listing.setAdapter(m_Adapter) ;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
}


