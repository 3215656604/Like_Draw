package gui.elementp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//公开类:元素管理器，图层
public class Coverage {

    //属性:元素列表，索引，可见性
    private List<Element> elementList;
    private int index;
    private int n=0;
    private boolean visiable;

    public List<Element> getElementList() {
        return elementList;
    }

    public boolean isVisiable() {
        return visiable;
    }

    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
    }

    //构造方法
    public Coverage(){
        elementList=new ArrayList<>();
        index=-1;
        int n=0;
        visiable=true;
    }

    //方法:有下一个
    public boolean haveNext(){
        if(index==n-1)return false;
        return true;
    }

    //方法：交换元素
    public void exchange(int a,int b){
        if(a<=index&&b<=index&&a>=0&&b>=0) {
            Element tmp = elementList.get(a);
            elementList.set(a, elementList.get(b));
            elementList.set(b, tmp);
        }
    }

    //方法:有上一个
    public boolean hasEsc(){
        if(index==-1)return false;
        return true;
    }

    //方法:重做
    public void next(){
        if(!haveNext())return;
        index++;
    }

    //方法:回退
    public void esc(){
        if(!hasEsc())return;
        index--;
    }

    //方法:新建元素
    public void add(Element element){
        if(index==n-1){
            index++;
            n++;
            elementList.add(index,element);
        }else {
            index++;
            n=index+1;
            elementList.add(index,element);
        }
    }

    //方法:删除所有元素
    public void deleteAll(){
        index=-1;
        n=0;
        elementList.removeAll(elementList);
    }

    //方法:展示元素管理器中的元素
    public void show(Graphics g){
        if(visiable)
            for(int j=0;j<=index;j++){
                elementList.get(j).show(g);
            }
    }

}
