package nongsa.agoto.MaintoOther.information.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import nongsa.agoto.R;

/**
 * Created by gunmin on 2017-04-18.
 */
public class fieldAdapter extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<String> m_TITLE;
    private ArrayList<String> m_DESCRIPTION;
    private ArrayList<String> m_TELEPHONE;
    private ArrayList<String> m_ADDRESS;
    private ArrayList<String> m_ROADADRESS;
    // 생성자
    public fieldAdapter() {
        m_TITLE = new ArrayList<String>();
        m_DESCRIPTION = new ArrayList<String>();
        m_TELEPHONE = new ArrayList<String>();
        m_ADDRESS = new ArrayList<String>();
        m_ROADADRESS = new ArrayList<String>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_TITLE.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_TITLE.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(final int position, View convertView,final  ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.field_item, parent, false);

            TextView title = (TextView)convertView.findViewById(R.id.field_title);
            TextView description = (TextView)convertView.findViewById(R.id.field_description);
            TextView address = (TextView)convertView.findViewById(R.id.field_address);

            title.setText(m_TITLE.get(position));
            if(m_DESCRIPTION.get(position).equals("")){
                description.setText("부동산 설명 : 토지 매매 임대 등 ");
            }else {
                description.setText("부동산 설명 : "+m_DESCRIPTION.get(position));
            }
            address.setText("주소: "+ m_ADDRESS.get(position));

            // 버튼을 터치 했을 때 이벤트 발생

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력



                }
            });

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    return true;
                }
            });
        }

        return convertView;

    }


    // 외부에서 아이템 추가 요청 시 사용
    public void add(String _msg) {
        m_TITLE.add(_msg);
    }
    public void addDESCRIPTION(String _msg) {
        m_DESCRIPTION.add(_msg);
    }
    public void addTELEPHONE(String _msg) {
        m_TELEPHONE.add(_msg);
    }
    public void addADDRESS(String _msg) {
        m_ADDRESS.add(_msg);
    }
    public void addROADADDRESS(String _msg) {
        m_ROADADRESS.add(_msg);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_TITLE.remove(_position);
    }

}