/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swj.utils;

import java.util.List;
/**
 * 分页算法
 * @author 寿炜剑
 */
public class Paging {
    /**首页*/
    public static int PAGE_FIRST = 1;
    /**上一页*/
    public static int PAGE_PREVIOUS = 2;
    /**下一页*/
    public static int PAGE_NEXT = 3;
    /**最后一页*/
    public static int PAGE_LAST = 4;
    /**跳页*/
    public static int PAGE_JUMP = 5;
    
    /**
     * @描述：构造方法
     * @param list 需要分页的内容
     * @param pageSize 每页大小
     */
    public Paging(List<String> list,int pageSize){
        this.pageSize = pageSize;
        this.list = list;
        //reset();
    }
    public Paging(List<String> list){
        this.list = list;
        //reset();
    }
    
    private int pageSize =100;//默认每页100行
    public int getPageSize(){
        return pageSize;
    }
    /**设置每页行数*/
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

//    private int rowWords=40;//默认每行字数
    private int cPage=1;//当前页
    private int totalPage=1;//总页数
    /**
     * @描述：获取最大页
     * @return
     */
    public int getMaxPage(){
        return totalPage;
    }
    /**
     * @描述：获取当前页
     * @return
     */
    public int getCurrentPage(){
        return cPage;
    }
    private List<String> list;//内容
//    private int startNum=0;
//    private int endNum=0;
    public List<String> getData(){
        return list;
    }
    
    /**设置每行字数*/
//    public void setRowWords(int rowWords){
//        this.rowWords = rowWords;
//    }
    
    /**重置相关数据*/
    private void reset(){
        totalPage = list.size()%pageSize==0?list.size()/pageSize:list.size()/pageSize+1;//计算总页数
    }

    /**
     * @描述：寻找当前页
     * 该方法主要用于根据指定内容进行跳页，line是代表文本在整个文档中第几行，通过行号计算出这一行应该属于第几页
     * line可以和内容进行对应
     * @param line
     * @return
     */
    public int findCpage(int line){
        reset();//重置最大页数
        int startNum,endNum=0;
        for(int i=1;i<=totalPage;i++){
            startNum = (i-1)*pageSize;
            endNum = (i-1)*pageSize+pageSize;
//            System.out.println(startNum+"   "+endNum+"   "+(line>startNum&&line<endNum));;
            if(line>=startNum&&line<endNum){
                return i;
            }
        }
        return 1;

    }

    /**
     * @描述：通过类型加载分页数据
     * @param type
     * @return
     */
    public String loading(int type){
        if(list==null||list.size()<1)return null;
        return loading(type,0);
    }
    /**
     * @描述：通过类型加载分页数据
     * @param type 
     * @param page  该参数仅在跳转页面时有用，非跳页可置位0
     * @return
     */
    public String loading(int type,int page){
        if(list==null||list.size()<1)return null;
        reset();//重置最大页数
        resetCpage(type, page);//重置当前页           
        int startNum = (cPage-1)*pageSize;
        int endNum = (cPage-1)*pageSize+pageSize;
        return findData(startNum, endNum);       
    }
//    /**
//     * @描述：通过指定开始和结尾加载数据
//     * @param start 从文档的第几行开始
//     * @param end   从文档的第几行结束
//     * @return
//     */
//    public String loading(int cpage,int start,int end){
//        if(list==null||list.size()<1)return null;       
//        reset();//重置最大页数
//        resetCpage(PAGE_JUMP, cpage);//重置当前页
//        return findData(start, end);
//    }
   

    /**
     * @描述：从列表中获取数据
     * @param startNum
     * @param endNum
     * @return
     */
    private String findData(int startNum,int endNum){
        StringBuilder content = new StringBuilder();
        for(int i=startNum;i<endNum;i++){
            if(i>=list.size())break;
            content.append(list.get(i));
        }
        return content.toString();
    }

    /**
     * @描述：重置当前页
     * @param type
     * @param page
     */
    private void resetCpage(int type,int page){
        if(type==PAGE_FIRST){//首页
            cPage = 1;
        }else if(type==PAGE_PREVIOUS){//上一页
            cPage = cPage-1;
            if(cPage<=1)cPage=1;
        }else if(type==PAGE_NEXT){//下一页
            cPage = cPage+1;
            if(cPage>=totalPage)cPage = totalPage;
        }else if(type==PAGE_LAST){//尾页
            cPage = totalPage;
        }else if(type==PAGE_JUMP){//跳页
            if(page<=1)page=1;
            if(page>=totalPage)page=totalPage;
            cPage = page;
        }
    }
    
    

}
