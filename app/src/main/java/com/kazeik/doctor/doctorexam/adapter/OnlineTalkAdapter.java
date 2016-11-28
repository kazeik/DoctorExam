package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.ChatItemBean;
import com.kazeik.doctor.doctorexam.utils.ApiUtils;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

/**
 * Created by kazeik.chen on 2016/4/21 0021 16:05.
 * email:kazeik@163.com ,QQ:77132995
 */
public class OnlineTalkAdapter extends MyBaseAdapter<ChatItemBean> {
//    public final int IMVT_COM_MSG = 1;
//    public final int IMVT_TO_MSG = 2;
public static interface IMsgViewType

{

    int IMVT_COM_MSG = 0;

    int IMVT_TO_MSG = 1;

}
    public OnlineTalkAdapter(Context cont) {
        super(cont);
    }

    //获取项的类型
    public int getItemViewType(int position) {
        ChatItemBean entity = getSrcData().get(position);
        if (entity.send) {
            return IMsgViewType.IMVT_COM_MSG;
        }else{
            return IMsgViewType.IMVT_TO_MSG;
        }

    }

    //获取项的类型数
    public int getViewTypeCount() {
        return 2;
    }

    //获取View
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatItemBean entity = getSrcData().get(position);
        boolean isComMsg = entity.send;

        ViewHolder viewHolder = null;
        if (convertView == null) {
            if (isComMsg) {
                //如果是自己发出的消息，则显示的是右气泡
                convertView = inflater.inflate(R.layout.chatting_item_msg_text_right, null);
            } else {
                //如果是对方发来的消息，则显示的是左气泡
                convertView = inflater.inflate(R.layout.chatting_item_msg_text_left, null);
            }

            viewHolder = new ViewHolder();
            viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.isComMsg = isComMsg;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(entity.mtime.length() ==10){
            entity.mtime = entity.mtime+"000";
        }else{
            entity.mtime = entity.mtime+"";
        }
        viewHolder.tvSendTime.setText(entity.mtime);
        viewHolder.tvContent.setText(entity.messages);

        return convertView;
    }

    //通过ViewHolder显示项的内容
    static class ViewHolder {
        public TextView tvSendTime;
        public TextView tvContent;
        public boolean isComMsg ;
    }
}
