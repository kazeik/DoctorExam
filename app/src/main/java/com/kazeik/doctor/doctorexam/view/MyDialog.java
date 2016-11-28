package com.kazeik.doctor.doctorexam.view;
/**
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016  11:48
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016  11:48
 */

/**
 * @author kazeik.chen , QQ:77132995,email:kazeik@163.com 2016  11:48
 */

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;


/**
 * 自定义dialog
 * @author sfshine
 *
 */
public class MyDialog {
    Context context;
    Dialogcallback dialogcallback;
    Dialog dialog;
    TextView tv_dialog_title;
    TextView tv_dialog_body;
    TextView tv_dialog_sure;

    /**
     * init the dialog
     * @return
     */
    public MyDialog(Context con) {
        this.context = con;
        dialog = new Dialog(context, R.style.dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.layout_dialog);
        tv_dialog_title = (TextView) dialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_body = (TextView) dialog.findViewById(R.id.tv_dialog_text);
        tv_dialog_sure = (TextView) dialog.findViewById(R.id.tv_dialog_sure);
        tv_dialog_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != dialogcallback) {
                    dialogcallback.dialogdo("");
                }
            }
        });
    }

    /**
     * 设定一个interfack接口,使mydialog可以處理activity定義的事情
     * @author sfshine
     *
     */
    public interface Dialogcallback {
        public void dialogdo(String string);
    }

    public void setDialogCallback(Dialogcallback dialogcallback) {
        this.dialogcallback = dialogcallback;
    }

    /**
     * @category Set The Content of the TextView
     * */
    public void setContent(String content) {
        tv_dialog_body.setText(content);
    }

    public void setTitle(String title) {
        tv_dialog_title.setText(title);
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        dialog.hide();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}