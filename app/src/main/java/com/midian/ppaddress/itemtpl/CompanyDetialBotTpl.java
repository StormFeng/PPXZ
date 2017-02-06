package com.midian.ppaddress.itemtpl;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CompanyDetailBean;
import midian.baselib.view.BaseTpl;

public class CompanyDetialBotTpl extends BaseTpl<CompanyDetailBean> implements View.OnClickListener{

    private TextView tv_question,tv_content,tv_member,tv_rate,tv_createTime;

    public CompanyDetialBotTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompanyDetialBotTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_question=findView(R.id.tv_question);
        tv_content=findView(R.id.tv_answer);
        tv_member=findView(R.id.tv_member);
        tv_rate=findView(R.id.tv_rate);
        tv_createTime=findView(R.id.tv_createtime);
    }

    private void initString(String pNumber,TextView textView,int start,int end) {
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 1 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if(i<start || i>=pNumber.length()-end){
                    sb.append(c);
                }else{
                    sb.append('*');
                }
            }
            textView.setText(sb.toString());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_company_evaluate;
    }

    @Override
    public void setBean(CompanyDetailBean bean, int position) {
        if(bean!=null) {
            if (bean.getItemViewType() == 1) {
                tv_question.setText("问题:" + bean.botBean.getQuestion());
                tv_content.setText(bean.botBean.getContent());
                String str = bean.botBean.getMember();
                if (str.length() <= 2) {
                    initString(str, tv_member, 1, 0);
                } else
                    initString(str, tv_member, 1, 1);
//            tv_member.setText(bean.botBean.get(position-1).getMember());
                tv_createTime.setText(bean.botBean.getCreateTime());
                if ("0".equals(bean.botBean.getRate())) {
                    tv_rate.setText("无");
                    tv_rate.setBackgroundColor(0xff9B9B9B);
                }
                if ("1".equals(bean.botBean.getRate())) {
                    tv_rate.setText("好评");
                    tv_rate.setBackgroundColor(0xFFF77F7F);
                }
                if ("2".equals(bean.botBean.getRate())) {
                    tv_rate.setText("中评");
                    tv_rate.setBackgroundColor(0xFFF49A00);
                }
                if ("3".equals(bean.botBean.getRate())) {
                    tv_rate.setText("差评");
                    tv_rate.setBackgroundColor(0xFF9B9B9B);
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
    }
}
