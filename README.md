# CommonAdapter

##Overview
**CommonAdapter**是一个通过继承BaseAdapter来增强和简化Android开发者在构建一个ListAdapter(创建ItemView等许多过程)的一个通用Adapter。
通过使用CommonAdapter,你可以轻松做到绑定视图和数据。一般我们是这样使用Adapter:
    
            public View getView(int position, View convertView, ViewGroup parent) {
                System.out.println("getView " + position + " " + convertView);
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item1, null);
                    holder = new ViewHolder();
                    holder.textView = (TextView)convertView.findViewById(R.id.text);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder)convertView.getTag();
                }
                holder.textView.setText(mData.get(position));
                return convertView;
            }
但是这段代码如果业务量多的话,就会导致getView方法中代码量也变得庞大,而且你的项目中可能出现多次,这样就会造成冗余,所以这样的方式实在不可取。
OK,我们需要优化的点如下:
1.__ViewHolder__
    使用一个对象来缓存Item中的控件对象,然后通过View.setTag的方式来存储在视图上,实际上是为了增加访问效率,减少不必要的内存开销。
    然而这种方式还是会造成冗余代码的问题,于是在CommonAdapter中使用了一个ViewHolder的类来控制对View的操作,
   
            private final SparseArray<View> mViews;
            
            public <T extends View> T getView(int viewId) {
                View view = mViews.get(viewId);
                if (view == null) {
                    view = mConvertView.findViewById(viewId);
                    mViews.put(viewId, view);
                }
                return (T) view;
            }
 
这里只贴出部分代码,详细代码请查看源码,并且使用SparseArray<View>来缓存访问过的视图,关于SparseArray如何提高访问效率,
请参考 [http://stackoverflow.com/questions/25560629/sparsearray-vs-hashmap]()
    
2.**convertView**的缓存
对于ItemView的缓存其实也是一个不断重复的过程,设计模式的思想是就是提取变化的部分,封装不变的部分。
变的部分就是Item的布局以及视图的内容,那么就出现了下面的封装:
    
    public abstract void convertView(ViewHolder helper, T item, int position);
    
    public abstract int getItemLayoutId(); 
这大概就是**CommonAdapter**的主要的抽象方法了,下面我们看看如何使用。

##ScreenShot
![](https://github.com/liuzhanta/CommonAdapter/blob/master/screen_shot.gif)

##Usage Code
我们通过继承CommonAdapter抽象类,来实现几个主要的方法:
        
        public class MyAdapter extends CommonAdapter<SimpleModel> {
    
        public MyAdapter(Context mContext) {
            super(mContext);
        }
    
        @Override
        public void convertView(ViewHolder helper, SimpleModel item, int position) {
            //set title
            helper.setText(R.id.textView,item.getTitle());
    
            //set image
            helper.setImageResource(R.id.imageView,item.getResId());
        }
    
        @Override
        public int getItemLayoutId() {
            return R.layout.adapter_item_simple;
        }
    }

我们只需要关注convertView & getItemLayoutId这两个方法就可以。
*   convertView中实现我们的绑定视图和数据的代码,我们可以通过ViewHolder来轻松设置控件的属性等。
*   getItemLayoutId返回我们绑定的视图的原始布局资源Id。

然后我们再在Activity中继续使用它:
            
            mlistview = (ListView) findViewById(R.id.listview);
    
            for (int i = 0; i < 30; i++) {
                mData.add(new SimpleModel("title---" + i, R.drawable.duckling));
            }
    
            myAdapter = new MyAdapter(this);
            mlistview.setAdapter(myAdapter);
            myAdapter.setData(mData);

是不是非常简单,快来尝试一下吧!~



Developed by
------------
Name: ZTerry Liu  
E-mail: tata1989y@gmail.com  
Subject: CommonAdapter 

