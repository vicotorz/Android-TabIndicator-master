package com.kevin.tabindicator.samples;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell on 2016/5/21.
 */
public class listdemo extends Fragment {


    Context context = null;

    String[] items = {"武汉银可可琴行有限责任公司", "武汉音乐种子琴行文化艺术发展有限责任公司", "湖北大武汉文化传媒有限公司"};
    int[][] logos = new int[][]{
            {R.drawable.telephone,R.drawable.text,R.drawable.website,R.drawable.location,R.drawable.text},
            {R.drawable.telephone,R.drawable.text,R.drawable.website,R.drawable.location,R.drawable.text},
            {R.drawable.telephone,R.drawable.text,R.drawable.website,R.drawable.location,R.drawable.text}
    };//存放图片资源
    String[][] arms = new String[][]{
            {"电话：027-88856586", "邮箱：whykk2003@yahoo.com.cn", "网址：yinkeke.cpm.cn", "地址：武昌区彭刘杨路228号金榜名苑1栋1层4号",
                    "武汉银可可琴行有限责任公司初创于一九九一年底，在自身的成长和发展过程中逐步确立了独特的经营理念。多年来，公司本着诚信务实的态度，稳健开拓的精神，逐渐形成公司合理的运作机制和湖北省内良好的市场网络，已将起初一个较小的经营实体，发展成为湖北省最大的乐器营销公司，现有多个营业网点及专卖店，在兼营批发的同时，占据着省内最大的乐器零售商地位。公司不断巩固武汉市场，并进一步拓展湖北省内的市场空间，武汉银可可琴行有限责任公司是湖北省内最大的乐器营销商。公司不仅在省内有广泛影响，在国内也声誉卓著。我公司实力雄厚，是全球顶级品牌施坦威钢琴代理商，并在雅马哈、珠江钢琴的销售中，位居中国前三、是华中地区雅马哈、珠江钢琴最大经销商。"},
            {"电话：86341419", "邮箱: musicseed@126.com", "网址: musicseed.cn", "地址: 武汉市青山区冶金大道2号",
                    " 武汉音乐种子琴行文化艺术发展有限责任公司前身是成立于1993年的音乐种子艺术中心，是集艺术教育、演出策划、乐器批发与零售为一体的全国连锁文化产业公司。音乐种子的目标是——建立一个拥有50家以上分店（分校）的全国性连锁艺术培训与乐器销售企业。下辖企业有音乐种子琴行、金种子艺术学校。音乐种子在武汉以有五家分店（校），公司计划在2014年前在湖北建设至少15家分店（校）。\n" +
                            "　　音乐种子琴行已拥有电吉它、电贝司、木吉它、架子鼓、钢琴、弦乐、民乐、管乐、专业音乐书籍音像资料，二手乐器等专业专场，经营品种做到了全系列、多品版、多款式。在乐器品种丰富的同时，音乐种子推出的乐器以旧换新、出租、维修、调律、乐器定做等完善优质的售后服务也深受顾客好评。\n"+
                            "　　除乐器销售之外，音乐种子琴行也在不遗余力的推动着音乐艺术的普及与传播，曾先后主办协办了“音乐种子民族音乐会”、“音乐种子校园音乐巡回演奏会”、“盛中国小提琴专场音乐会”等大型音乐会。另处，音乐种子的周、月、年音乐会也为学员成绩展示提供了广阔的舞台。\n" +
                            "　　音乐种子艺术中心成立于1993年，是目前从事非学历制音乐教育机构中规模最大、学员最多，开办时间最长的专业艺术培训机构。金种子艺术学校2000年并入音乐种子后将欧美先进的“TES”循环教学法引入华中地区，并成功在多所学校中推广开设了“TES”班（团）。在音乐、舞蹈、美术、书法、棋牌、处国语等多个专业上，逐渐成为华中地区最具规模的专业学校之一。\n"
            },
            {"电话: 027-88567156", "邮箱: 275819787@qq.com", "网址：暂无", "地址: 武汉市武昌区东湖路181号（老黄鹂路65号）",
                    "湖北大武汉文化传媒有限公司（以下简称大武汉传媒）是湖北日报楚天传媒（集团）有限责任公司旗下全资子公司，成立于2012年，注册资本1000万元。公司致力于打造武汉城市文化高端平台，文化品牌输出平台，文化项目投融资平台。目前拥有3刊1书2网1中心，1个参股互联网投资公司，以及多种文化衍生品、文化活动、文化项目等资源。\n" +
                            "\n" +
                            "3刊包括：《大武汉》主刊，《大武汉》地铁刊（与武汉地铁集团合作），《大武汉》企业专刊。1书指《大武汉美食指南》（单行本）。2网指《大武汉》官方网站微博微信平台、网络营销平台。1中心是新媒体中心。1 个参股公司是湖北文旅众创投资管理有限公司。文化衍生品包括大武汉纪念U盘、大武汉美食地图IPHONE版、大武汉城市明信片等。\n"
            },
    };

    public ExpandableListView listview;
    public ExpandableListAdapter adapter;

    public static listdemo newInstance(String strArg) {
        listdemo fragment = new listdemo();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //自己定义一个获得文字信息的方法
        //TabPageIndicatorExActivity e=new  TabPageIndicatorExActivity();
        View view = inflater.inflate(R.layout.list_company, container, false);
        listview = (ExpandableListView) view.findViewById(R.id.listView);
        adapter = new BaseExpandableListAdapter() {

            TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, 800);//设置文字的展示宽度--之前的简介问题就出现在这
                TextView textView = new TextView(context);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(10);
                textView.setTextColor(Color.BLACK);
                return textView;
            }

            @Override
            public int getGroupCount() {
                return items.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return items[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            //A.设置一级标题
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(context);
                //ll.setOrientation();
                ImageView logo = new ImageView(context);
                //logo.setImageResource(logos[groupPosition]);
                //logo.setPadding(50, 0, 0, 0);
                //ll.addView(logo);
                TextView textView = getTextView();
                textView.setTextColor(Color.BLACK);

                textView.setTextSize(18);
                //textView.setFakeBoldText(true);
                //getGroup中返回item中存储的字段
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);

                return ll;
            }

            //B.对子列表进行构建
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(
                        context);
                //ll.setOrientation(0);
                ImageView generallogo = new ImageView(
                        context);
                generallogo
                        .setImageResource(logos[groupPosition][childPosition]);
                //generallogo.setY(20);
                ll.addView(generallogo);
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition)
                        .toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        //setListAdapter(adapter);//!!使用继承listFragment 可以使用这个特有方法
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "点击", Toast.LENGTH_LONG);
            }
        });
        return view;
    }
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//    }


}

