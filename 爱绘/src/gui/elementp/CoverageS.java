package gui.elementp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//公开类:图层管理器
public class CoverageS {

    //属性:图层列表
    private List<Coverage> coveragesList =new ArrayList<>();
    private int index=-1;

    //方法:get
    public List<Coverage> getCoveragesList() {
        return coveragesList;
    }

    //方法:返回显示在最上层的图层
    public Coverage getElementMangement(){
        if(index==-1)return null;
        return coveragesList.get(index);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //方法:添加图层
    public void add(){
        coveragesList.add(new Coverage());
        index= coveragesList.size()-1;
    }

    //方法：设置可见性
    public void setVia(int x){
        if(x<coveragesList.size()){
            coveragesList.get(x).setVisiable(!coveragesList.get(x).isVisiable());
        }
        if(x==index) {
            index--;
            while (index != -1 && !coveragesList.get(index).isVisiable()) {
                index--;
            }
        }
    }

    //方法:删除图层
    public void delete(int x){
        if(x==index) {
            index--;
            while (index != -1 && !coveragesList.get(index).isVisiable()) {
                index--;
            }
        }
        coveragesList.remove(x);
    }

    //方法:交换图层
    public void exchange(int x,int y){
        Coverage t= coveragesList.get(x);
        coveragesList.set(x, coveragesList.get(y));
        coveragesList.set(y,t);
    }

    //方法:展示所有的图层
    public void show(Graphics g){
        for(Coverage e: coveragesList){
            e.show(g);
        }
    }

}
