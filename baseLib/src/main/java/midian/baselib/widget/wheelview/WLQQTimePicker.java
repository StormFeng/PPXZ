package midian.baselib.widget.wheelview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.baselib.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class WLQQTimePicker extends LinearLayout {
    public static final String PICKED_TIME_EXT = "picked_time";
    private static final int UPDATE_TITLE_MSG = 0x111;
    private static final int UPDATE_WHEEL = 0x112;
    private static final int UPDATE_UpdateDay_MSG = 0x113;
    private final int START_YEAR = 2016;
    private final int END_YEAR = 2100;
    private TextView mPickerTitle;
    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private WheelView mWheelHour;
    private WheelView mWheelMinute;
    private TextView mCancelBtn;
    private TextView mConfirmBtn;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int nYear;
    private int nMonth;
    private int nDay;
    private int nHour;
    private int nMinute;

    private Calendar mCalendar;
    private int mDefaultDayWhellIndex = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TITLE_MSG: {
                    updateTitle();
                }
                break;
                case UPDATE_WHEEL: {
                    updateWheel();
                }
                break;
                case UPDATE_UpdateDay_MSG: {
                    updateDay(mMonth);
                }
                break;
            }

        }
    };
    private WheelView.OnSelectListener mYearListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int year, String text) {
            mYear = START_YEAR + year;
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            Log.d("wqf","mYear"+mYear+">>>nYear"+nYear);
        }

        @Override
        public void selecting(int id, String text) {
        }
    };
    private WheelView.OnSelectListener mMonthListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int month, String text) {
//            自由设置时间
//            mMonth = month;
//            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
//            mHandler.sendEmptyMessage(UPDATE_UpdateDay_MSG);


//            限制只能选择大于当前的时间，下同
//            Log.d("wqf","MMNOTH：："+mMonth+">>>month:::"+month);
            if(mYear>nYear){
                mMonth = month;
            }
            else{
                if(nMonth<month){
                    mMonth = month;
                }else{
                    mMonth = nMonth;
                    mWheelMinute.setDefault(nMinute);
                    mWheelHour.setDefault(nHour);
                    mWheelDay.setDefault(nDay-1);
                    mWheelMonth.setDefault(nMonth);
                }
            }
            Log.d("wqf","mDay"+mDay+">>>nDay"+nDay);
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            mHandler.sendEmptyMessage(UPDATE_UpdateDay_MSG);
        }

        @Override
        public void selecting(int id, String text) {
        }
    };
    private WheelView.OnSelectListener mDayListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int day, String text) {
//            mDay = day + 1;
//            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            if(mMonth>nMonth || mYear>nYear){
                mDay = day + 1;
           }
            else{
                if(nDay<day+1){
                    mDay = day + 1;
                }else{
                    mDay = nDay;
                    mWheelMinute.setDefault(nMinute);
                    mWheelHour.setDefault(nHour);
                    mWheelDay.setDefault(nDay-1);
                }
            }
            Log.d("wqf","mDay"+mDay+">>>nDay"+nDay);
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        }

        @Override
        public void selecting(int day, String text) {
        }
    };
    private WheelView.OnSelectListener mHourListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int hour, String text) {
//            mHour = hour;
//            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            if(mDay>nDay || mMonth>nMonth || mYear>nYear){
                mHour = hour;
            }else{
                if(nHour<hour){
                    mHour = hour;
                }else{
                    mHour = nHour;
                    mWheelHour.setDefault(nHour);
                    mWheelMinute.setDefault(nMinute);
                }
            }
            Log.d("wqf","mHour"+mHour+">>>nHour"+nHour);
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
//            mPickerTitle.setText(mWheelYear.getSelectedText()+"年"+mWheelMonth.getSelectedText()+"月"+mWheelDay.getSelectedText()+"日 "
//                +mWheelHour.getSelectedText()+":"+mWheelMinute.getSelectedText());
        }

        @Override
        public void selecting(int day, String text) {
        }
    };
    private WheelView.OnSelectListener mMinuteListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int minute, String text) {
//            mMinute = minute;
//            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            if(mHour>nHour || mDay>nDay || mMonth>nMonth || mYear >nYear){
                mMinute = minute;
            }else{
                if(nMinute<minute){
                    mMinute = minute;
                }else{
                    mWheelMinute.setDefault(nMinute);
                    mMinute = nMinute;
                }
            }
            Log.d("wqf","mMinute"+mMinute+">>>nMinute"+nMinute);
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        }

        @Override
        public void selecting(int day, String text) {
        }
    };
    private Context mContext;

    public WLQQTimePicker(Context context) {
        this(context, null);
    }

    public WLQQTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContext =  getContext();
        LayoutInflater.from(mContext).inflate(R.layout.time_picker, this);
        mPickerTitle = (TextView) findViewById(R.id.picker_title);
        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);
        mWheelDay = (WheelView) findViewById(R.id.day);
        mWheelHour = (WheelView) findViewById(R.id.hour);
        mWheelMinute = (WheelView) findViewById(R.id.minute);
        mCancelBtn = (TextView) findViewById(R.id.cancel);
        mConfirmBtn = (TextView) findViewById(R.id.confirm);
        mWheelYear.setOnSelectListener(mYearListener);
        mWheelMonth.setOnSelectListener(mMonthListener);
        mWheelDay.setOnSelectListener(mDayListener);
        mWheelHour.setOnSelectListener(mHourListener);
        mWheelMinute.setOnSelectListener(mMinuteListener);
        mConfirmBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(WLQQTimePicker.class.getSimpleName(), mContext.getString(R.string.picker_title, mYear, mMonth + 1, mDay, mHour,mMinute));
//                mCalendar.set(Calendar.YEAR, mYear);
//                mCalendar.set(Calendar.MONTH, mMonth);
//                mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
//                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
//                Intent intent = new Intent();
//                intent.putExtra(PICKED_TIME_EXT, mCalendar.getTimeInMillis());
//                mContext.setResult(WuliuQQConstants.RESULT_CODE_TIME_PICKER, intent);
//                mContext.finish();
            }
        });
        mCancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                mContext.finish();
            }
        });
    }

//    private void updateDay(int month) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, month);
//        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        mWheelDay.resetData(getDayData(maxDay));
//        if (mDay > maxDay) {
//            mWheelDay.setDefault(mDefaultDayWhellIndex);
//            mDay = mDefaultDayWhellIndex + 1;
//        } else {
//            mWheelDay.setDefault(mDay - 1);
//        }
//    }

    private void updateDay(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        mWheelDay.resetData(getDayData(maxDay));
        if (nDay > maxDay) {
            mWheelDay.setDefault(mDefaultDayWhellIndex);
            nDay = mDefaultDayWhellIndex + 1;
        } else {
            mWheelDay.setDefault(nDay - 1);
        }
    }

    /**
     * set WLQQTimePicker date
     * @param date
     */
    public void setDate(long date) {
        mCalendar = Calendar.getInstance(Locale.CHINA);
        mCalendar.setTimeInMillis(date);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        nYear = mCalendar.get(Calendar.YEAR);
        nMonth = mCalendar.get(Calendar.MONTH);
        nDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        nHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        nMinute = mCalendar.get(Calendar.MINUTE);

        Log.d("wqf","mYear::"+mYear+"<<mMonth::"+mMonth+"<<mDay::"+mDay+"<<mHour::"+mHour+"<<mMinute::"+mMinute);
        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData(mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
        mWheelHour.setData(getHourData());
        mWheelMinute.setData(getMinuteData());

        mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        mHandler.sendEmptyMessage(UPDATE_WHEEL);
    }

    private void updateTitle() {
        String h=mHour+"";
        String m=mMinute+"";
        if(mHour<10){
            h="0"+h;
        }
        if(mMinute<10){
            m="0"+m;
        }
        mPickerTitle.setText(mContext.getString(R.string.picker_title, mYear, mMonth + 1, mDay, h,m));
    }

    private void updateWheel() {
        mWheelYear.setDefault(mYear - START_YEAR);
        mWheelMonth.setDefault(mMonth);
        mWheelDay.setDefault(mDay - 1);
        mWheelHour.setDefault(mHour);
        mWheelMinute.setDefault(mMinute);
    }

    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = START_YEAR; i <= nYear; i++) {
            list.add(i + "年");
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            list.add(i + "月");
        }
        return list;
    }

    private ArrayList<String> getDayData(int endDay) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= endDay; i++) {
            list.add(i + "日");
        }
        return list;
    }

    private ArrayList<String> getHourData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            if(i<10){
                list.add("0"+i);
            }else {
                list.add(i + "");
            }
        }
        return list;
    }
    private ArrayList<String> getMinuteData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            if(i<10){
                list.add("0"+i);
            }else {
                list.add(i + "");
            }
        }
        return list;
    }
}