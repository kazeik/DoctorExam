package com.kazeik.doctor.doctorexam.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kazeik.doctor.doctorexam.R;
import com.kazeik.doctor.doctorexam.bean.MockExamBean;
import com.kazeik.doctor.doctorexam.utils.AppUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazeik.chen on 2016/4/20 0020 13:39.
 * email:kazeik@163.com ,QQ:77132995
 */
public class MockBodyAdapter extends MyBaseAdapter<MockExamBean.Entity501> {
    HashMap<Integer, String> choiceHash;
    HashMap<String, String> answHash;
    boolean checkAnswer;

    public MockBodyAdapter(Context cont) {
        super(cont);
        answHash = new HashMap<>();
        choiceHash = new HashMap<>();
    }

    public void setCheckAnswer(boolean checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public HashMap<Integer, String> getChoiceHash() {
        return choiceHash;
    }

    public HashMap<String, String> getAnswHash() {
        return answHash;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder = null;
//        if (null == arg1) {
        arg1 = inflater.inflate(R.layout.layout_mock_info, arg2, false);
        viewHolder = new ViewHolder(arg1);
//            arg1.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) arg1.getTag();
//        }

        final MockExamBean.Entity501 entity501 = getSrcData().get(arg0);
        viewHolder.tvTitleName.setText(Html.fromHtml((arg0 + 1) + "." + entity501.info.caption));
        viewHolder.rbItem1.setText(Html.fromHtml(entity501.option.get(0).sorts + "." + entity501.option.get(0).text));
        viewHolder.rbItem2.setText(Html.fromHtml(entity501.option.get(1).sorts + "." + entity501.option.get(1).text));
        viewHolder.rbItem3.setText(Html.fromHtml(entity501.option.get(2).sorts + "." + entity501.option.get(2).text));
        viewHolder.rbItem4.setText(Html.fromHtml(entity501.option.get(3).sorts + "." + entity501.option.get(3).text));
        viewHolder.rbItem5.setText(Html.fromHtml(entity501.option.get(4).sorts + "." + entity501.option.get(4).text));
//        AppUtils.logs(getClass(),arg0+" | "+ entity501.option.get(3).text +" | "+ entity501.option.get(4).text);
        if (checkAnswer) {
            viewHolder.rbItem1.setButtonDrawable(R.drawable.a);
            viewHolder.rbItem2.setButtonDrawable(R.drawable.b);
            viewHolder.rbItem3.setButtonDrawable(R.drawable.c);
            viewHolder.rbItem4.setButtonDrawable(R.drawable.d);
            viewHolder.rbItem5.setButtonDrawable(R.drawable.e);

            if (TextUtils.isEmpty(entity501.info.remark))
                viewHolder.tvMark.setVisibility(View.GONE);
            else {
                viewHolder.tvMark.setVisibility(View.VISIBLE);
                viewHolder.tvMark.setText(Html.fromHtml(entity501.info.remark));
            }
//            AppUtils.logs(getClass(),arg0+" = "+ entity501.info.remark);
            if (entity501.info.right_answer.equals("A")) {
                viewHolder.rbItem1.setButtonDrawable(R.drawable.y);
                if (!entity501.info.user_answer.equals("A")) {
                    if (entity501.info.user_answer.equals("E")) {
                        viewHolder.rbItem5.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("B")) {
                        viewHolder.rbItem2.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("C")) {
                        viewHolder.rbItem3.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("D")) {
                        viewHolder.rbItem4.setButtonDrawable(R.drawable.error);
                    }
                } else {
                    viewHolder.rbItem1.setButtonDrawable(R.drawable.y);
                }
            } else if (entity501.info.right_answer.equals("B")) {
                viewHolder.rbItem2.setButtonDrawable(R.drawable.y);
                if (!entity501.info.user_answer.equals("B")) {
                    if (entity501.info.user_answer.equals("A")) {
                        viewHolder.rbItem1.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("E")) {
                        viewHolder.rbItem5.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("C")) {
                        viewHolder.rbItem3.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("D")) {
                        viewHolder.rbItem4.setButtonDrawable(R.drawable.error);
                    }
                } else {
                    viewHolder.rbItem2.setButtonDrawable(R.drawable.y);
                }
            } else if (entity501.info.right_answer.equals("C")) {
                viewHolder.rbItem3.setButtonDrawable(R.drawable.y);
                if (!entity501.info.user_answer.equals("C")) {
                    if (entity501.info.user_answer.equals("A")) {
                        viewHolder.rbItem1.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("B")) {
                        viewHolder.rbItem2.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("D")) {
                        viewHolder.rbItem4.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("E")) {
                        viewHolder.rbItem5.setButtonDrawable(R.drawable.error);
                    }
                } else {
                    viewHolder.rbItem3.setButtonDrawable(R.drawable.y);
                }
            } else if (entity501.info.right_answer.equals("D")) {
                viewHolder.rbItem4.setButtonDrawable(R.drawable.y);
                if (!entity501.info.user_answer.equals("D")) {
                    if (entity501.info.user_answer.equals("A")) {
                        viewHolder.rbItem1.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("B")) {
                        viewHolder.rbItem2.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("C")) {
                        viewHolder.rbItem3.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("E")) {
                        viewHolder.rbItem5.setButtonDrawable(R.drawable.error);
                    }
                } else {
                    viewHolder.rbItem4.setButtonDrawable(R.drawable.y);
                }
            } else if (entity501.info.right_answer.equals("E")) {
                viewHolder.rbItem5.setButtonDrawable(R.drawable.y);
                if (!entity501.info.user_answer.equals("E")) {
                    if (entity501.info.user_answer.equals("A")) {
                        viewHolder.rbItem1.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("B")) {
                        viewHolder.rbItem2.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("C")) {
                        viewHolder.rbItem3.setButtonDrawable(R.drawable.error);
                    } else if (entity501.info.user_answer.equals("D")) {
                        viewHolder.rbItem4.setButtonDrawable(R.drawable.error);
                    }
                } else {
                    viewHolder.rbItem5.setButtonDrawable(R.drawable.y);
                }
            }
        } else {
            Object obj = choiceHash.get(arg0);
            if (obj != null) {
                if (obj.toString().equals("A")) {
                    viewHolder.rbItem1.setChecked(true);
                    viewHolder.rbItem2.setChecked(false);
                    viewHolder.rbItem3.setChecked(false);
                    viewHolder.rbItem4.setChecked(false);
                    viewHolder.rbItem5.setChecked(false);
                } else if (obj.toString().equals("B")) {
                    viewHolder.rbItem1.setChecked(false);
                    viewHolder.rbItem2.setChecked(true);
                    viewHolder.rbItem3.setChecked(false);
                    viewHolder.rbItem4.setChecked(false);
                    viewHolder.rbItem5.setChecked(false);
                } else if (obj.toString().equals("C")) {
                    viewHolder.rbItem1.setChecked(false);
                    viewHolder.rbItem2.setChecked(false);
                    viewHolder.rbItem3.setChecked(true);
                    viewHolder.rbItem4.setChecked(false);
                    viewHolder.rbItem5.setChecked(false);
                } else if (obj.toString().equals("D")) {
                    viewHolder.rbItem1.setChecked(false);
                    viewHolder.rbItem2.setChecked(false);
                    viewHolder.rbItem3.setChecked(false);
                    viewHolder.rbItem4.setChecked(true);
                    viewHolder.rbItem5.setChecked(false);
                } else if (obj.toString().equals("E")) {
                    viewHolder.rbItem1.setChecked(false);
                    viewHolder.rbItem2.setChecked(false);
                    viewHolder.rbItem3.setChecked(false);
                    viewHolder.rbItem4.setChecked(false);
                    viewHolder.rbItem5.setChecked(true);
                }
            } else {
                viewHolder.rbItem1.setChecked(false);
                viewHolder.rbItem2.setChecked(false);
                viewHolder.rbItem3.setChecked(false);
                viewHolder.rbItem4.setChecked(false);
                viewHolder.rbItem5.setChecked(false);
            }

            viewHolder.rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    String questionId = entity501.info.id;
                    String optionId = "";

                    String item = "";
                    switch (i) {
                        case R.id.rb_item1:
                            item = "A";
                            optionId = entity501.option.get(0).id;
                            break;
                        case R.id.rb_item2:
                            item = "B";
                            optionId = entity501.option.get(1).id;
                            break;
                        case R.id.rb_item3:
                            item = "C";
                            optionId = entity501.option.get(2).id;
                            break;
                        case R.id.rb_item4:
                            item = "D";
                            optionId = entity501.option.get(3).id;
                            break;
                        case R.id.rb_item5:
                            item = "E";
                            optionId = entity501.option.get(4).id;
                            break;
                    }
                    choiceHash.put(arg0, item);
                    answHash.put(questionId, optionId);
//                    notifyDataSetChanged();
                }
            });
        }

        return arg1;
    }

    static class ViewHolder {
        @Bind(R.id.tv_titleName)
        TextView tvTitleName;
        @Bind(R.id.rb_item1)
        RadioButton rbItem1;
        @Bind(R.id.rb_item2)
        RadioButton rbItem2;
        @Bind(R.id.rb_item3)
        RadioButton rbItem3;
        @Bind(R.id.rb_item4)
        RadioButton rbItem4;
        @Bind(R.id.rb_item5)
        RadioButton rbItem5;
        @Bind(R.id.rg_group)
        RadioGroup rgGroup;
        @Bind(R.id.tv_mark)
        TextView tvMark;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
