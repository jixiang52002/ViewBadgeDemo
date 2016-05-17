package viewbadge.langyi.viewbadgedemo;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;

public class MainActivity extends TabActivity {

    private static final String[] DATA = Cheeses.sCheeseStrings;

    Button btnPosition;
    Button btnColour;
    Button btnAnim1;
    Button btnAnim2;
    Button btnCustom;
    Button btnClick;
    Button btnTab;
    Button btnIncrement;

    ListView listDemo;

    BadgeView badge1;
    BadgeView badge2;
    BadgeView badge3;
    BadgeView badge4;
    BadgeView badge5;
    BadgeView badge6;
    BadgeView badge7;
    BadgeView badge8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("demos")
                .setIndicator("Badge Demos")
                .setContent(R.id.tab1));

        tabHost.addTab(tabHost.newTabSpec("adapter")
                .setIndicator("List Adapter")
                .setContent(R.id.tab2));

        tabHost.addTab(tabHost.newTabSpec("tests")
                .setIndicator("Layout Tests")
                .setContent(R.id.tab3));

        // *** default badge（默认的badge） ***

        View target = findViewById(R.id.default_target);
        BadgeView badge = new BadgeView(this, target);
        badge.setText("1");
        badge.show();

        // *** set position(设置所在的位置) ***

        btnPosition = (Button) findViewById(R.id.position_target);
        badge1 = new BadgeView(this, btnPosition);
        badge1.setText("12");
        badge1.setBadgePosition(BadgeView.POSITION_CENTER);
        btnPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge1.toggle();
            }
        });

        // *** badge/text size & colour 更改badge的文字大小和颜色***

        btnColour = (Button) findViewById(R.id.colour_target);
        badge2 = new BadgeView(this, btnColour);
        badge2.setText("New!");
        badge2.setTextColor(Color.BLUE);
        badge2.setBadgeBackgroundColor(Color.YELLOW);
        badge2.setTextSize(12);
        btnColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge2.toggle();
            }
        });

        // *** default animation 为badge设置默认动画***

        btnAnim1 = (Button) findViewById(R.id.anim1_target);
        badge3 = new BadgeView(this, btnAnim1);
        badge3.setText("84");
        btnAnim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge3.toggle(true);
            }
        });

        // *** custom animation 为badge设置本地动画***

        btnAnim2 = (Button) findViewById(R.id.anim2_target);
        badge4 = new BadgeView(this, btnAnim2);
        badge4.setText("123");
        badge4.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
        badge4.setBadgeMargin(15, 10);
        badge4.setBadgeBackgroundColor(Color.parseColor("#A4C639"));
        btnAnim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
                anim.setInterpolator(new BounceInterpolator());
                anim.setDuration(1000);
                badge4.toggle(anim, null);
            }
        });

        // *** custom background 为badge设置本地背景***

        btnCustom = (Button) findViewById(R.id.custom_target);
        badge5 = new BadgeView(this, btnCustom);
        badge5.setText("37");
        badge5.setBackgroundResource(R.drawable.badge_ifaux);
        badge5.setTextSize(16);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge5.toggle(true);
            }
        });

        // *** clickable badge 为badge添加点击事件***

        btnClick = (Button) findViewById(R.id.click_target);
        badge6 = new BadgeView(this, btnClick);
        badge6.setText("click me");
        badge6.setBadgeBackgroundColor(Color.BLUE);
        badge6.setTextSize(16);
        badge6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clicked badge", Toast.LENGTH_SHORT).show();
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge6.toggle();
            }
        });

        // *** tab 桌面图标效果，部分手机支持***

        TabWidget tabs = (TabWidget) findViewById(android.R.id.tabs);

        btnTab = (Button) findViewById(R.id.tab_btn);
        badge7 = new BadgeView(this, tabs, 0);
        badge7.setText("5");
        btnTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge7.toggle();
            }
        });

        // *** increment （点击增加badge计数）***

        btnIncrement = (Button) findViewById(R.id.increment_target);
        badge8 = new BadgeView(this, btnIncrement);
        badge8.setText("0");
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (badge8.isShown()) {
                    badge8.increment(1);
                } else {
                    badge8.show();
                }
            }
        });

        // *** list adapter （list的适配器）****

        listDemo = (ListView) findViewById(R.id.tab2);
        listDemo.setAdapter(new BadgeAdapter(this));

    }

    @Override
    protected void onResume() {
        super.onResume();

        BadgeView badge;
        View target;

        // *** test linear layout container （测试LinearLayout 容器添加Badge的效果）***

        target = findViewById(R.id.linear_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test relative layout container （测试RelativeLayout 容器添加Badge的效果）***

        target = findViewById(R.id.relative_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test frame layout container （测试FrameLayout 容器添加Badge的效果）***

        target = findViewById(R.id.frame_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test table layout container （测试TableLayout 容器 添加Badge的效果）***

        target = findViewById(R.id.table_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test linear layout （测试LinearLayout 添加Badge的效果）***

        target = findViewById(R.id.linear_group_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test relative layout（测试RelativeLayout添加badge的效果） ***

        target = findViewById(R.id.relative_group_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test frame layout ***

        target = findViewById(R.id.frame_group_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

        // *** test table layout (测试TableLayout添加badge的效果) ***

        target = findViewById(R.id.tablerow_group_target);
        badge = new BadgeView(this, target);
        badge.setText("OK");
        badge.show();

    }

    private static class BadgeAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Context mContext;
        private static final int droidGreen = Color.parseColor("#A4C639");

        public BadgeAdapter(MainActivity context) {
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        public int getCount() {
            return DATA.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(android.R.layout.simple_list_item_2, null);
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(android.R.id.text1);
                holder.badge = new BadgeView(mContext, holder.text);
                holder.badge.setBadgeBackgroundColor(droidGreen);
                holder.badge.setTextColor(Color.BLACK);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(DATA[position]);

            if (position % 3 == 0) {
                holder.badge.setText(String.valueOf(position));
                holder.badge.show();
            } else {
                holder.badge.hide();
            }


            return convertView;
        }

        static class ViewHolder {
            TextView text;
            BadgeView badge;
        }
    }

}
